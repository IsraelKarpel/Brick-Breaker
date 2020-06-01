import java.util.List;

/**
the interface holds the information of the levels.
**/
public interface LevelInformation {
    /**
    * the number of the balls.
    * @return the number of the balls.
    **/
    int numberOfBalls();
    /**
    * The initial velocity of each ball.
    * @return list of the velocitis.
    **/
    List<Velocity> initialBallVelocities();
    /**
    * the speed of the paddle.
    * @return the speed.
    **/
    int paddleSpeed();
    /**
    * the width of the paddle.
    * @return the width.
    **/
    int paddleWidth();
    /**
    * the level name will be displayed at the top of the screen.
    * @return string that holds the name.
    **/
    String levelName();
    /**
    * The backgrund of the level.
    * @return the background.
    **/
    Sprite getBackground();
    /**
    * The Blocks that make up this level, each block contains its size, color and location.
    *@return list of the blocks.
    **/
    List<Block> blocks();
    /**
    * Number of blocks that should be removed before the level is considered to be "cleared".
    * @return the number of the blocks.
    **/
    int numberOfBlocksToRemove();
    /**
    * decide where the paddle shoulf start.
    * @return int the x value of the paddle.
    */
    int paddleStartX();
}