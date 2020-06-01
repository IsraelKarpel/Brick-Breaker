import biuoop.DrawSurface;
import java.awt.Color;
/**
the class creats ball which has center, radius, color, vector and limits.
the class returns his values, paint it, and move it on the screen
**/

public class Ball implements Sprite {

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment g;

      /**
    * Construct a Ball given his parameters.
    * @param x the x coordinate of the circle center
    * @param y the y coordinate og the circle center
    * @param r the raduis of the ball
    * @param color the coloro the ball
    */
   public Ball(int x, int y, int r, java.awt.Color color) {
       this.center = new Point(x, y);
       this.r = r;
       this.color = color;
       //this.v = v;
   }

   /**
   * set enviroment to the ball.
   * @param game the game enviroment we wantto add to the ball
   */
   public void setEnvironment(GameEnvironment game) {
        this.g = game;
   }


   /**
    * @return the x coordinate of the center
    */
   public int getX() {
       return this.center.getX();
   }

   /**
    * @return the y coordinate of the center
    */
   public int getY() {
       return this.center.getY();
   }

   /**
    * @return the radius of the ball
    */
   public int getSize() {
       return this.r;
   }

   /**
    * @return the color of the ball
    */
   public java.awt.Color getColor() {
       return this.color;
   }

   /**
   * the vector can initialize by a given vector.
   * @param vi given velocity
   */
   public void setVelocity(Velocity vi) {
       this.v = vi;
   }

   /**
   * the vector also can initialized by two points.
   * @param dx given x coardinates
   * @param dy given y coardinates
   */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
    * @return the velocity of the ball
    */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
    checks if the ball reach any obstacle, if so, switch direction, and them call method.
    that will moves the ball one step
    */
    //first check if there is a collision along the way
    public void moveOneStep() {
        Point newPos = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, newPos);
        CollisionInfo coll = this.g.getClosestCollision(trajectory);
        if (coll != null) {
            Point tempP = coll.collisionPoint();
            this.center = this.getVelocity().littleBack(tempP);
            Velocity newV = coll.collisionObject().hit(this, coll.collisionPoint(), this.getVelocity());
            this.v = newV;
            } else {
            newPos = this.getVelocity().applyToPoint(this.center);
            this.center = newPos;
            return;
        }
    }
   /**
    * draw the ball on the given DrawSurface.
    * @param surface where we drawing the ball
    */
   public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.center.getX(), this.center.getY(), this.r);
        surface.setColor(this.color);
        surface.fillCircle(this.center.getX(), this.center.getY(), this.r);
   }

 /**
    * the function tells what to do after one peroid.
    */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
    * add the current block to the fllow of the game.
    * @param game the game that is running rigth now.
    */
    public void addToGame(GameLevel game) {
    game.addSprite(this);
    }

    /**
     * remove the current ball from the game.
     * @param ga the game that is running right now.
 */
    public void removeFromGame(GameLevel ga) {
        ga.removeSprite(this);
    }
}