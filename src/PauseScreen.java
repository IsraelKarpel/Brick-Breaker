import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
* the class shows stamemnt when we pause hte game.
**/
public class PauseScreen implements Animation {
        private boolean stop;

        /**
        * Constructor.
        * @param k the keyboard.
        **/
        public PauseScreen(KeyboardSensor k) {
            this.stop = false;
        }
        @Override
        public void doOneFrame(DrawSurface d) {
            d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        }

        @Override
        public boolean shouldStop() {
        return this.stop;
        }
    }