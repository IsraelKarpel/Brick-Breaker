import java.awt.Color;
import java.awt.Image;
import java.util.Map;
/**
* the interface holds the things a block ahould has.
**/
public interface BlockCreator {
    /**
    * creates the ball.
    * @param xpos thw x positinos of the block.
    * @param ypos thw y positinos of the block.
    * @return Block.
    **/
    Block create(int xpos, int ypos);
    /**
    * @return the number hits of the block.
    **/
    int getHit();
    /**
    * @return the map of the colors.
    **/
    Map<Integer, Color> getKColor();
    /**
    * @return the map of the images.
    **/
    Map<Integer, Image> getKImage();
    /**
    * @return the height of the block.
    **/
    int getHeight();
    /**
    * @return the width of the block.
    **/
    int getWidth();
    /**
    * @return the strock of the block.
    **/
    Color getStroke();
}