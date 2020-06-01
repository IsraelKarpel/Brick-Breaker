import java.awt.Color;
import biuoop.DrawSurface;

/**
the class creates a rectangle that shows the Score of the player.
**/
public class ScoreIndicator implements Sprite {
    private Rectangle rect;
    private Counter count;
    private String levelName;

    /**
    * Construct the score given his parameters.
    * @param rect the shape of the rectangle
    * @param name the string.
    */
    public ScoreIndicator(Rectangle rect, String name) {
        this.rect = rect;
        this.levelName = name;
    }

    /**
    * set counter to the indicator.
    * @param c the counter we want to change to.
    */
    public void setCounter(Counter c) {
        this.count = c;
    }

    /**
    * add the current block to the fllow of the game.
    * @param gameLevel the game that is running rigth now.
    */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        }

    @Override
    public void drawOn(DrawSurface d) {
        int x = this.rect.getUpperLeft().getX();
        int y = this.rect.getUpperLeft().getY();
        int w = (int) this.rect.getWidth();
        int h =  (int) this.rect.getHeight();
        d.setColor(Color.white);
        d.fillRectangle(x, y - 25, w + 400, h);
        d.fillRectangle(x, y, w, h);
        d.setColor(Color.black);
        d.drawText(x + (w / 2), y , "Score:" + Integer.toString(this.count.getValue()), 20);
        d.drawText(550, 25, this.levelName, 20);
    }

    @Override
    public void timePassed() {
    }
}