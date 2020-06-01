import java.util.List;
import java.util.ArrayList;

/**
 * the class know what collision is happened in our game and uses them.
 * in order to calculates the ball position accordingly
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
    * add new collidable to the list od=f the collidables.
    * @param c collidable we want to add
    */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
       }

/**
    * remove the collidable from the list of the collidables.
    * @param c collidable we want to add
    */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

        /**
        * assume an object moving from line.start() to line.end().
        *  If this object will not collide with any of the collidables
        * in this collection, return null. Else, return the information
        * about the closest collision that is going to occur.
        * @param trajectory line that represent the route of the ball
        * in this move
        * @return the information about the closet collision if there is one
        * otherwise null.
        */
        public CollisionInfo getClosestCollision(Line trajectory) {
        //get the first collision point
        Point pMin = trajectory.closestIntersectionToStartOfLine(collidables.get(0).getCollisionRectangle());
        Point p2;
        double dMin;
        if (pMin == null) {
            dMin = -1;
        } else {
        dMin = pMin.distance(trajectory.start());
        }
        double d2;
        Collidable cMin = (collidables.get(0));
        Collidable c2;
        //find the closet point that a collision happened in.
        List<Collidable> newColl = new ArrayList<Collidable>(this.collidables);
        for (int i = 1; i < newColl.size(); i++) {
        p2 = trajectory.closestIntersectionToStartOfLine(
                newColl.get(i).getCollisionRectangle());
        if (p2 == null) {
            d2 = -1;
           } else {
            d2 = p2.distance(trajectory.start());
            }
            c2 = (newColl.get(i));
            if (dMin != -1) {
            if ((d2 < dMin) && (d2 >= 0)) {
            pMin = p2;
            dMin = d2;
            cMin = c2;
                }
            } else {
            pMin = p2;
            dMin = d2;
            cMin = c2;
            }
            }
            if (dMin < 0) {
            return null;
            }
            CollisionInfo coll = new CollisionInfo(pMin, cMin);
            return coll;
        }
    }
