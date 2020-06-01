/**
 * updates the counter when hit the blocks.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
        * constructor.
        * @param scoreCounter the number we count.
        */
        public ScoreTrackingListener(Counter scoreCounter) {
            this.currentScore = scoreCounter;
        }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumber() == 0) {
            this.currentScore.increase(15);
            }
            if (beingHit.getNumber() > 0) {
            this.currentScore.increase(5);
            }
        }
}