import biuoop.KeyboardSensor;
/**
* the class shows the highscore table.
**/
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    /**
    * constructor.
    * @param runner the animation runner.
    * @param highScoresAnimation the animate to display.
    **/
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }
        @Override
        public Void run() {
            this.runner.run(this.highScoresAnimation);
            return null;
        }
        @Override
        public void setKs(KeyboardSensor keyboardSensor) {
        // TODO Auto-generated method stub
    }
}