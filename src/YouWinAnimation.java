import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
* The class display "you win" screenshot.
**/
public class YouWinAnimation implements Animation {
    private boolean stop;
    private Counter score;
    /**
    * Constructors.
    * @param k the keyboard.
    * @param scores the score of the player.
    **/
    public YouWinAnimation(KeyboardSensor k, Counter scores) {
        this.stop = false;
        this.score = scores;
        }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + Integer.toString(this.score.getValue()), 32);
        }
    @Override
    public boolean shouldStop() {
        return this.stop;
        }
    }
