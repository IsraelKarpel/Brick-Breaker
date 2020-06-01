/**
 * class that holds the points and the collidables shapes of every collasion.
 *
 */
public class CollisionInfo {
    private Point p;
    private Collidable c;

    /**
        * Construct the information about the collision.
        * @param p the Point when the collision happened.
        * @param c the shape and where the ball collapsed in
        */
    public CollisionInfo(Point p, Collidable c) {
        this.p = p;
        this.c = c;
    }

    /**
    *  the point at which the collision occurs.
    * @return the collision point
    */
    public Point collisionPoint() {
        return this.p;
    }

        /**
        * the collidable object involved in the collision.
        * @return the collision object
        */
        public Collidable collisionObject() {
        return this.c;
    }
}

