import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
* The class shows a game over screenshot.
**/
public class GameOverAnimation implements Animation {
    private boolean stop;
    private Counter score;
    /**
    * Constructors.
    * @param k the keyboard
    * @param scores the playes scores.
    **/
    public GameOverAnimation(KeyboardSensor k, Counter scores) {
        this.stop = false;
        this.score = scores;
        }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + Integer.toString(this.score.getValue()), 32);
        }
    @Override
        public boolean shouldStop() {
        return this.stop;
        }
    }