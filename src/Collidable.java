/**
 * the interface return collation shape and a velocity of the new direction.
 * of the ball.
 */

public interface Collidable {

        /**
        * @return the "collision shape" of the object.
        */
        Rectangle getCollisionRectangle();

        /**
        * Notify the object that we collided with it at collisionPoint with
        * a give velocity.
        * @param collisionPoint the point the collision happened
        * @param currentVelocity the velocity of the bal when the coliision happened
        * @param hitter the ball that hits.
        * @return the new velocity expected after the hit (based on
        * the force the object inflicted on us).
        */
        Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
    }