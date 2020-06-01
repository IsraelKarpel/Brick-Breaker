import biuoop.DrawSurface;

    /**
    the interface the creates the game objects and add them to the screen.
    */
    public interface Sprite {

    /**
    * draw the sprite to the screen.
    @param d the paints of the object
    */
    void drawOn(DrawSurface d);
    /**
    notify the sprite that time has passed.
    */
        void timePassed();
    }