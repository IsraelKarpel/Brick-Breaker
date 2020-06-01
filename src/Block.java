import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.util.Map;
/**
the class create a block which is an obstacle in a shape of rectangle.
**/

public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private int number;
    private Map<Integer, Color> fillKColor;
    private Map<Integer, Image> fillKImage;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Color stroke = null;

    /**
        * Construct a Ball given his parameters.
        * @param rect the rectangle that the block is.
        **/
   public Block(Rectangle rect) {
        this.rect = rect;
    }

    /**
        * set number to the block.
        * @param num to the ball.
    */
    public void setNumber(int num) {
        this.number = num;
    }
    /**
     * @return the number that is in the ball.
     */
    public int getNumber() {
        return this.number;
    }
    /**
        * set nmap of colors.
        * @param m the map.
    */
    public void setKColor(Map<Integer, Color> m) {
        this.fillKColor = m;
    }
    /**
        * set nmap of images.
        * @param m the map.
    */
    public void setKImage(Map<Integer, Image> m) {
        this.fillKImage = m;
    }
    /**
     * @return the width of the ball.
     */
    public int getWidth() {
        return (int) this.rect.getWidth();
    }
    /**
     * @return the map color of the ball.
     */
    public Map<Integer, Color> getKColor() {
        return this.fillKColor;
    }
    /**
     * @return the map images of the ball.
     */
    public Map<Integer, Image> getKImage() {
        return this.fillKImage;
    }
    /**
        * set the stroke.
        * @param c the color.
    */
    public void setStroke(Color c) {
        this.stroke = c;
    }
    /**
        * @return the rectangle that the block is.
        */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notify all of the listeners about a hit event.
     * @param hitter the ball that hits the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
           hl.hitEvent(this, hitter);
        }
     }

    /**
    * change the direction of the ball when it hits collation.
    * @param collisionPoint the point when the collation happened.
    * @param currentVelocity the current velocity of the ball when the
    * collation happened
    * @param hitter the ball that hits.
    * @return the new velocity the ball should go to after the hit.
    */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point cornerUpLeft = this.rect.getUpperLeft();
        Point cornerUpRight = this.rect.getUpperRight();
        Point cornerDownLeft = new Point(this.rect.getUpperLeft().getX(), this.rect.getUpperLeft().getY() + 20);
        Point cornerDownRight = new Point(this.rect.getUpperRight().getX(), this.rect.getUpperRight().getY() + 20);
        if ((collisionPoint.equals(cornerUpLeft)) || (collisionPoint.equals(cornerUpRight))
        || (collisionPoint.equals(cornerDownLeft)) || (collisionPoint.equals(cornerDownRight))) {
        currentVelocity.changeXDirection();
        currentVelocity.changeYDirection();
        setNumber(this.number - 1);
        this.notifyHit(hitter);
        return currentVelocity;
        } else {
        Point tempPoint = new Point(collisionPoint.getX() - 2, collisionPoint.getY());
        if (!this.rect.isPointInRectangle(tempPoint)) {
        currentVelocity.changeXDirection();
        setNumber(this.number - 1);
        this.notifyHit(hitter);
        return currentVelocity;
        } else {
        tempPoint = new Point(collisionPoint.getX() + 2, collisionPoint.getY());
        if (!this.rect.isPointInRectangle(tempPoint)) {
        currentVelocity.changeXDirection();
        setNumber(this.number - 1);
        this.notifyHit(hitter);
        return currentVelocity;
        } else {
        tempPoint = new Point(collisionPoint.getX(), collisionPoint.getY() + 2);
        if (!this.rect.isPointInRectangle(tempPoint)) {
        currentVelocity.changeYDirection();
        setNumber(this.number - 1);
        this.notifyHit(hitter);
        return currentVelocity;
        } else {
        tempPoint = new Point(collisionPoint.getX(), collisionPoint.getY() - 2);
        if (!this.rect.isPointInRectangle(tempPoint)) {
        currentVelocity.changeYDirection();
        setNumber(this.number - 1);
        this.notifyHit(hitter);
        return currentVelocity;
                        }
                    }
                }
            }
        }
        return currentVelocity;
    }

    /**
        * draw the block on the given DrawSurface.
        * @param surface where we drawing the ball
        */
    public void drawOn(DrawSurface surface) {
        if (this.fillKImage.get(this.number) != null) {
            surface.drawImage(this.getCollisionRectangle().getUpperLeft().getX(),
            this.getCollisionRectangle().getUpperLeft().getY(), this.fillKImage.get(this.number));
        } else {
            if (this.fillKColor.get(this.number) != null) {
            surface.setColor(this.fillKColor.get(this.number));
            int x = this.getCollisionRectangle().getUpperLeft().getX();
            int y = this.getCollisionRectangle().getUpperLeft().getY();
            int w = (int) this.getCollisionRectangle().getWidth();
            int h =  (int) this.getCollisionRectangle().getHeight();
            surface.fillRectangle(x, y, w, h);
        } else {
            if (this.fillKImage.get(0) != null) {
                surface.drawImage(this.getCollisionRectangle().getUpperLeft().getX(),
                this.getCollisionRectangle().getUpperLeft().getY(), this.fillKImage.get(0));
        } else {
            if (this.fillKColor.get(0) != null) {
                surface.setColor(this.fillKColor.get(0));
                int x = this.getCollisionRectangle().getUpperLeft().getX();
                int y = this.getCollisionRectangle().getUpperLeft().getY();
                int w = (int) this.getCollisionRectangle().getWidth();
                int h =  (int) this.getCollisionRectangle().getHeight();
                surface.fillRectangle(x, y, w, h);
        }
        }
        }
        }
        if (this.stroke != null) {
            surface.setColor(this.stroke);
            int x = this.getCollisionRectangle().getUpperLeft().getX();
            int y = this.getCollisionRectangle().getUpperLeft().getY();
            int w = (int) this.getCollisionRectangle().getWidth();
            int h =  (int) this.getCollisionRectangle().getHeight();
            surface.drawRectangle(x, y, w, h);
        }
    }
    /**
    the function tells what to do in this time.
    */
    public void timePassed() {

    }

    /**
        * add the current block to the fllow of the game.
        * @param g the game that is running rigth now.
    */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        }

    /**
     * remove the current block from the game.
     * @param g the game that is running right now.
 */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}