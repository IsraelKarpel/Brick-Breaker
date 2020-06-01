import java.awt.Color;
import biuoop.DrawSurface;

/**
* the class display the 3 2 1  screen.
**/
public class CountdownAnimation implements Animation {
    private double seconds;
    private int count;
    private SpriteCollection sprites;
    private biuoop.Sleeper sleeper;

    /**
    * constructors.
    * @param numOfSeconds how much seonds will the count be
    * @param countFrom number to count from.
    * @param gameScreen the sprites of the game.
    **/
    public CountdownAnimation(double numOfSeconds,
                                int countFrom,
                                    SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.count = countFrom;
        this.sprites = gameScreen;
        this.sleeper = new biuoop.Sleeper();
        }
    @Override
    public void doOneFrame(DrawSurface d) {
        long startTime = System.currentTimeMillis();
        this.sprites.drawAllOn(d);
        d.setColor(Color.pink);
        d.drawText(350, d.getHeight() / 4, Integer.toString(this.count), 100);
        double millisecondsPerFrame = this.seconds * 1000 / (this.count + 2);
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = (long) millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
                }
            this.count = this.count - 1;
            }
        @Override
        public boolean shouldStop() {
            return this.count < 0;
        }
}