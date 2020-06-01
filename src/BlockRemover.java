/**a BlockRemover is in charge of removing blocks from the game, as well as keeping count
* of the number of blocks that remain.
**/
public class BlockRemover implements HitListener {
private GameLevel gameLevel;
private Counter remainingBlocks;

/**
 * Construct a BlockRemover given his parameters.
 * @param gameLevel the game that is running
 * @param removedBlocks count how many blocks left.
 **/
public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
    this.gameLevel = gameLevel;
    this.remainingBlocks = removedBlocks;
    }

@Override
public void hitEvent(Block beingHit, Ball hitter) {
    if (beingHit.getNumber() == 0) {
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);
        }
    }
}
