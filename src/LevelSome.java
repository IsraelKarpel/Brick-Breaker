import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import java.io.Reader;
/**
* the class hold the information of the levels.
**/
public class LevelSome implements LevelInformation {
    private String levelName;
    private List<Velocity> velocities = new ArrayList<Velocity>();
    private Color color;
    private Image img = null;
    private int paddleSpeed;
    private int paddleWidth;
    private String blockDefination;
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private int numBlocks;
    private int numBalls;
    private List<char[]> layout = new ArrayList<char[]>();

    @Override
    public int numberOfBalls() {
        return this.numBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Sprite() {
                @Override
                public void drawOn(DrawSurface d) {
                    if (img == null) {
                    d.setColor(color);
                    d.fillRectangle(0, 0, 800, 600);
                    } else {
                        d.drawImage(10, 20, img); // draw the image at location 10, 20
                    }
                }

                @Override
                public void timePassed() {
                    // TODO Auto-generated method stub

                }
                };
                return background;
        }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Block block;
        BlocksFromSymbolsFactory b = new BlocksFromSymbolsFactory();
        InputStream is = null;
        Reader reader = null;
        try {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.blockDefination);
                reader = new InputStreamReader(is);
                b = BlocksDefinitionReader.fromReader(reader);
                int x = blocksStartX;
                int y = blocksStartY;
                int w = 0;
                int h = 0;
                for (int j = 0; j < this.layout.size(); j++) {
                for (int i = 0; i < layout.get(j).length; i++) {
                    if (b.isBlockSymbol(String.valueOf(layout.get(j)[i]))) {
                    block = b.getBlock(String.valueOf(layout.get(j)[i]), x + w , y + h);
                    //block.setKColor(b.getBlocks().get(String.valueOf(layout.get(j)[i])).getKColor());
                    //block.setKImage(b.getBlocks().get(String.valueOf(layout.get(j)[i])).getKImage());
                    w = w + block.getWidth();
                    blocks.add(block);
                    } else {
                        w = w + b.getSpaceWidth(String.valueOf(layout.get(j)[i]));
                    }
                }
                h = h + rowHeight;
                w = 0;
                }
                return blocks;
            } finally {
                        if (is != null) { // Exception might have happened at constructor
                            try {
                                is.close(); // closes FileInputStream too
                            } catch (IOException e) {
                                System.out.println("Failed closing the file!");
                                }
                                }
                            }
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numBlocks;
    }

    @Override
    public int paddleStartX() {
        return this.blocksStartX;
    }
    /**
    * set the level name.
    * @param name of the level.
    **/
    public void setLevelName(String name) {
        this.levelName = name;
    }
    /**
    * set the level velocities.
    * @param veloc the velocities of the level.
    **/
    public void setVelocities(List<Velocity> veloc) {
        this.velocities = veloc;
    }
    /**
    * set the level background color.
    * @param c the color background of the level.
    **/
    public void setColor(Color c) {
        this.color = c;
    }
    /**
    * set the level background image.
    * @param im the image background of the level.
    **/
    public void setImage(Image im) {
        this.img = im;
    }
    /**
    * set the level paddle speed.
    * @param s the speed of the paddle.
    **/
    public void setPaddleSpeed(int s) {
        this.paddleSpeed = s;
    }
    /**
    * set the level paddle width.
    * @param w the width of the paddle.
    **/
    public void setPaddleWidth(int w) {
        this.paddleWidth = w;
    }
    /**
    * set the level text address of the blocks.
    * @param s the address.
    **/
    public void setBlockDefination(String s) {
        this.blockDefination = s;
    }
    /**
    * set the x coardinates to start the blocks from.
    * @param x the x coradinates.
    **/
    public void setBlockStartX(int x) {
        this.blocksStartX = x;
    }
    /**
    * set the y coardinates to start the blocks from.
    * @param y the y coradinates.
    **/
    public void setBlockStartY(int y) {
        this.blocksStartY = y;
    }
    /**
    * set the width between tow row of blocks.
    * @param h the height.
    **/
    public void setRowHieght(int h) {
        this.rowHeight = h;
    }
    /**
    * set the number of the blocks.
    * @param n the number.
    **/
    public void setnumBlock(int n) {
        this.numBlocks = n;
    }
/**
    * set the number of the balls.
    * @param ballsNumber the number.
    **/
    public void setBallsNumber(int ballsNumber) {
        this.numBalls = ballsNumber;
    }

    /**
    * set the locations of the blocks.
    * @param t the list.
    **/
        public void setLayout(List t) {
        this.layout = t;
    }
}