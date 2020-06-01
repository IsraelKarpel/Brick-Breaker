/**
the class creats by how much an item will move in the screen, by coardinates.
or by angle and speed, return the new point after the movement
*/
public class Velocity {
    private double dx;
    private double dy;

    /**
    * Construct a velocity given his parameters.
    * @param dxi the x coordinate of the velocity
    * @param dyi the y coordinate of the velocity
    */
   public Velocity(double dxi, double dyi) {
       this.dx = dxi;
       this.dy = dyi;
   }

   /**
   takes the speed and the angle of the vector and show the change as
   x ad y coardinates.
   *@param angle thr angle we want the vector to move in
   *@param speed the speed we want the vector to move in
   *@return new point that we want the item to move to
   */
   public static Velocity fromAngleAndSpeed(double angle, double speed) {
      double dx;
      double dy;
      dx = (Math.sin(Math.toRadians(angle))) * speed;
      dy = -(Math.cos(Math.toRadians(angle))) * speed;
      return new Velocity(dx, dy);
   }
   /**
   Take a point with position (x,y) and return a new point.
   with position (x+dx, y+dy)
   * @param p the original point
   * @return a the new point
   */
   public Point applyToPoint(Point p) {
       double x;
       double y;
       x = p.getX() + this.dx;
       y = p.getY() + this.dy;
       Point a = new Point(x, y);
       return a;
   }

   /**
   change the direction of the x coardinates if the ball gets to his border.
   */
   public void changeXDirection() {
       this.dx = -dx;
   }

    /**
   change the direction of the y coardinates if the ball gets to his border.
   */
   public void changeYDirection() {
       this.dy = -dy;
   }

   /**
   take the ball a little bit back from the coliiion point.
   * @param p the real collision point
   * @return the point after the change.
   */
   public Point littleBack(Point p) {
        Velocity v;
        v = new Velocity(-1 * (this.dx), -1 * (this.dy));
        return v.applyToPoint(new Point(p.getX(), p.getY()));
    }

   /**
   change the velocity of the ball when hit the first fifth of the padel.
   * @return the new velocity.
   */

   public Velocity changeAreaOne() {
        double speed = Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
        this.dx = (Math.sin(Math.toRadians(300)) * speed);
        this.dy = -(Math.cos(Math.toRadians(60)) * speed);
        return new Velocity(dx, dy);
    }

   /**
   change the velocity of the ball when hit the second fifth of the padel.
   * @return the new velocity.
   */
   public Velocity changeAreaTwo() {
        double speed = Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
        this.dx = (Math.sin(Math.toRadians(330)) * speed);
        this.dy = -(Math.cos(Math.toRadians(30)) * speed);
        return new Velocity(dx, dy);
    }

   /**
   change the velocity of the ball when hit the forth fifth of the padel.
   * @return the new velocity.
   */
   public Velocity changeAreaFour() {
        double speed = Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
        this.dx = (Math.sin(Math.toRadians(30)) * speed);
        this.dy = -(Math.cos(Math.toRadians(30)) * speed);
        return new Velocity(dx, dy);
    }

   /**
   change the velocity of the ball when hit the fifth fifth of the padel.
   * @return the new velocity.
   */
   public Velocity changeAreaFive() {
        double speed = Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
        this.dx = (Math.sin(Math.toRadians(60)) * speed);
        this.dy = -(Math.cos(Math.toRadians(60)) * speed);
        return new Velocity(dx, dy);
    }
}