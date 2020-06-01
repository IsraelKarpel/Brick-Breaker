import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
/**
* the class freats a bclok.
**/
public class BlockHelper implements BlockCreator {
    private String symbol;
    private int height;
    private int width;
    private int hit;
    private Color stroke;
    private Map<Integer, Color> fillKColor = new HashMap<Integer, Color>();
    private Map<Integer, Image> fillKImage = new HashMap<Integer, Image>();

    @Override
    public Block create(int xpos, int ypos) {
        Block b = new Block(new Rectangle(new Point(xpos, ypos), width, height));
        return b;
    }
    /**
        * set the symbol of the block.
        * @param s the symbol;
    */
    public void setSymbol(String s) {
        this.symbol = s;
    }
    /**
        * get the symbol of the block.
        * @return s the symbol;
    */
    public String getSymbol() {
        return this.symbol;
    }
    @Override
    public int getHeight() {
        return this.height;
    }
    @Override
    public int getWidth() {
        return this.width;
    }
    /**
        * set the height of the block.
        * @param h the height;
    */
    public void setHeight(int h) {
        this.height = h;
    }
    /**
        * set the width of the block.
        * @param h the width;
    */
    public void setWidth(int h) {
        this.width = h;
    }
/**
        * set the numbers hit of the block.
        * @param h the hit;
    */
    public void setHit(int h) {
        this.hit = h;
    }
    /**
        * set the borders of the block.
        * @param c the color border;
    */
    public void setStrok(Color c) {
        this.stroke = c;
    }
    /**
        * set the color of the block.
        * @param n the number in the map;
        * @param c the color;
    */
    public void setFillKColor(int n, Color c) {
        this.fillKColor.put(n, c);
    }
    /**
        * set the image of the block.
        * @param n the number in the map;
        * @param i the image;
    */
    public void setFillKImage(int n, Image i) {
        this.fillKImage.put(n, i);
    }
    @Override
    public int getHit() {
        return this.hit;
    }
    @Override
    public Map<Integer, Color> getKColor() {
        return this.fillKColor;
    }
    @Override
    public Map<Integer, Image> getKImage() {
        return this.fillKImage;
    }
    @Override
    public Color getStroke() {
        return this.stroke;
        }
}