import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Image;
import java.util.Map;
import java.util.HashMap;

/**
* the class creats all the data aboout the game, and runs it.
*/
public class GameLevel implements Animation {
   private SpriteCollection sprites;
   private GameEnvironment environment;
   private Counter blockCounter;
   private Counter ballCounter;
   private Counter score;
   private Counter livesCounter;
   private AnimationRunner runner;
   private boolean running;
   private KeyboardSensor keyboard;
   private LevelInformation information;
   private GUI gui;

   //public static final int SCORE = 0;
   //public static final int LIVES_NUMBER = 4;

    /**
    * constructures. list of the spriet and of the collidables.
    * @param informat the infornation of the level.
    * @param keyb the keyboard.
    * @param run the animation that should be in the level.
    * @param scor the score of the player.
    * @param lives the number if lives left.
    * @param g the GUI we draw on.
    */
   public GameLevel(LevelInformation informat, KeyboardSensor keyb,
        AnimationRunner run, Counter scor, Counter lives, GUI g) {
    this.information = informat;
    this.sprites = new SpriteCollection();
    this.environment = new GameEnvironment();
    this.blockCounter = new Counter(this.information.numberOfBlocksToRemove());
    this.ballCounter = new Counter(this.information.numberOfBalls());
    this.score = scor;
    this.livesCounter = lives;
    this.runner = run;
    this.runner.setInformation(this.information);
    this.runner.setGame(this);
    this.keyboard = keyb;
    this.gui = g;
    this.runner.setGui(this.gui);
    }

    /**
    * add colidable to the list.
    @param c the newcollidable
    */
    public void addCollidable(Collidable c) {
    environment.addCollidable(c);
    }

    /**
        * add sprite to the list.
        @param s the new sprite.
        */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
        * remove collidable from the game.
        @param c the collidable.
        */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
        * remove sprite from the game.
        @param s the sprite.
        */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
    * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
    */
    public void initialize() {
        sprites.addSprite(this.information.getBackground());
            //add blocks to the screen,every group of ball in different loop
            BlockRemover blockRemove = new BlockRemover(this, this.blockCounter);
            BallRemover ballRemove = new BallRemover(this, this.ballCounter);
            ScoreTrackingListener track = new ScoreTrackingListener(this.score);
            ScoreIndicator indicateScore = new ScoreIndicator(
                    new Rectangle(new Point(200, 25), 200, 25), this.information.levelName());
            indicateScore.addToGame(this);
            indicateScore.setCounter(this.score);
            LivesIndicator indicateLive = new LivesIndicator(
                    new Rectangle(new Point(0, 25), 200, 25));
            indicateLive.addToGame(this);
            indicateLive.setCounter(this.livesCounter);
            List<Block> blocks = this.information.blocks();
            // add the limits of the boarder
            Map<Integer, Color> c = new  HashMap<Integer, Color>();
            c.put(0, Color.gray);
            Map<Integer, Image> m = new  HashMap<Integer, Image>();
            m.put(0, null);
            // add the limits of the boarder
            Block b = new Block(new Rectangle(new Point(25, 600), 750, 1));
            b.setKColor(c);
            b.setKImage(m);
            b.setNumber(0);
            b.addHitListener(ballRemove);
            blocks.add(b);
            Block b1 = new Block(new Rectangle(new Point(0, 50), 25, 550));
            b1.setKColor(c);
            b1.setKImage(m);
            b1.setNumber(0);
            blocks.add(b1);
            Block b2 = new Block(new Rectangle(new Point(0, 25), 800, 25));
            b2.setKColor(c);
            b2.setKImage(m);
            b2.setNumber(0);
            blocks.add(b2);
            Block b3 = new Block(new Rectangle(new Point(775, 50), 25, 550));
            b3.setKColor(c);
            b3.setKImage(m);
            b3.setNumber(0);
            blocks.add(b3);
            for (int i = 0; i < blocks.size(); i++) {
                blocks.get(i).addToGame(this);
                blocks.get(i).addHitListener(blockRemove);
                blocks.get(i).addHitListener(track);
            }
            Paddle paddle = new Paddle((new Point(this.information.paddleStartX(), 575)),
                    this.information.paddleWidth(), 10);
            paddle.setSpeed(this.information.paddleSpeed());
            paddle.addToGame(this);
            paddle.addKeyboard(keyboard);
        }
    /**
    * Notify when we should stop the level.
    * @return bollean true or false
    **/
    public boolean shouldStop() {
        //when ending the block gives 100 points
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            return this.running;
        }
        //when the balls are gone we end this turn
        if (this.ballCounter.getValue() == 0) {
            this.livesCounter.setCounter(this.livesCounter.getValue() - 1);
            return this.running;
        }
        return !this.running;
    }
    /**
    * Doing one frame of the level.
    * @param d the drawsurface we draw.
    **/
    public void doOneFrame(DrawSurface d) {
        KeyPressStoppableAnimation key = new KeyPressStoppableAnimation(
            this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard));
        if (this.keyboard.isPressed("p")) {
            this.runner.run(key);
            }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        }

    /**
    * Play one turn of the game.
    */
    public void playOneTurn() {
        createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        }
    /**
    * Creates the balls every time they dissaper.
    **/
    public void createBallsOnTopOfPaddle() {
        List<Ball> balls = new ArrayList<Ball>();
        List<Velocity> velocitis = this.information.initialBallVelocities();
        int number = this.information.numberOfBalls();
        for (int i = 0; i < number; i++) {
        Ball ball = new Ball(400, 570, 5, java.awt.Color.WHITE);
        ball.setVelocity(velocitis.get(i));
        ball.setEnvironment(this.environment);
        ball.addToGame(this);
        balls.add(ball);
        }
    }
    /**
    * Gets how much blocks left.
    * @return how much blocks left.
    **/
    public int getBlockCounter() {
        return blockCounter.getValue();
    }

    /**
    * Tells how many balls should be in the game.
    * @param number of the balls
    **/
    public void setBallCounter(int number) {
        this.ballCounter.setCounter(number);
    }

    /**
    * Tells the score of the player.
    * @return the score of the player
    **/
    public Counter getScore() {
        return this.score;
    }
    /**
    * Tells how many lives left.
    * @return how much lives left
    **/
    public int getLives() {
        return livesCounter.getValue();
    }
}