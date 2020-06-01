import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
the class creates a paddle which can be moved with the keyboard.
*/
public class Paddle implements Sprite, Collidable {
   private biuoop.KeyboardSensor keyboard;
   private Rectangle pad;
   private int speed;

   public static final int MIN_BOARD_SIZE = 25;
   public static final int MAX_BOARD_SIZE = 775;

    /**
    * the cunstructor of the paddle.
    @param p the point.
    @param w thw wisth of the paddle.
    @param h the hieght of the padlle.
    */
   public Paddle(Point p, double w, double h) {
    this.pad = new Rectangle(p, w, h);
    }

   /**
   * set how fast will the pad remove.
   @param s the speed
   */
    public void setSpeed(int s) {
    this.speed = s;
    }

   /**
   * connect between the keboard and the padlle.
   @param keyb the keyboard
   */
    public void addKeyboard(KeyboardSensor keyb) {
    this.keyboard = keyb;
   }

   /**
    * the function tells what to do after one peroid.
    */
    public void timePassed() {
            double x = this.pad.getUpperLeft().getX();
            double y = this.pad.getUpperLeft().getY();
            double w = this.pad.getWidth();
            double h = this.pad.getHeight();
            double r = this.pad.getUpperRight().getX();
            // if pressed left
            if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                if (x - 1 <= MIN_BOARD_SIZE) {
                return;
                }
            Point p = new Point(x - this.speed, y);
            this.pad = new Rectangle(p, w, h);
            }
            //if pressed right
            if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                if (r + 1 >= MAX_BOARD_SIZE) {
                return;
            }
            Point p = new Point(x + this.speed, y);
            this.pad = new Rectangle(p, w, h);
        }
    }

   /**
        * draw the paddle on the given DrawSurface.
        * @param d where we drawing the ball
        */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.orange);
        double x = this.pad.getUpperLeft().getX();
        double y = this.pad.getUpperLeft().getY();
        double w = this.pad.getWidth();
        double h = this.pad.getHeight();
        d.fillRectangle((int) x, (int) y, (int) w, (int) h);
    }

    /**
    * @return our pad.
    */
    public Rectangle getCollisionRectangle() {
        return this.pad;
    }

   /**
    * change the direction of the ball when it hits collation.
    * @param collisionPoint the point when the collation happened.
    * @param currentVelocity the current velocity of the ball when the
    * collation happened
    * @param hitter the ball that hits.
    * @return the new velocity the ball should go to after the hit.
    */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double padX = this.pad.getUpperLeft().getX();
        double fifth = this.pad.getWidth() / 5;
        double areaOne = padX + fifth;
        double areaTwo = padX + (2 * fifth);
        double areaThree = padX + (3 * fifth);
        double areaFour = padX + (4 * fifth);
        //check in wiche fifth the ball hit the padlle
        if (collisionPoint.getX() < areaOne) {
            currentVelocity = currentVelocity.changeAreaOne();
        } else if (collisionPoint.getX() < areaTwo) {
            currentVelocity = currentVelocity.changeAreaTwo();
        } else if (collisionPoint.getX() < areaThree) {
            currentVelocity.changeYDirection();
        } else if (collisionPoint.getX() < areaFour) {
            currentVelocity = currentVelocity.changeAreaFour();
        } else {
            currentVelocity = currentVelocity.changeAreaFive();
        }
        return currentVelocity;
    }

   /**
    * Add this paddle to the game.
    @param g our game
    */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove the paddle from the game.
     * @param g the game that is running right now.
 */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}