/**a BallRemover is in charge of removing balls from the game, as well as keeping count
* of the number of blocks that remain.
**/
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
    * Construct a BallRemover given his parameters.
    * @param gameLevel the game that is running
    * @param removedBalls count how many balls left.
    **/
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
        }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
