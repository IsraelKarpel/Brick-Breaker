import biuoop.DrawSurface;

/**
the interface doing the animations.
**/
public interface Animation {
    /**
    * drwaing on the screen one turn.
    * @param d the drwasurface
    **/
    void doOneFrame(DrawSurface d);
    /**
    * return if we should stop this turn.
    * @return true or false.
    **/
    boolean shouldStop();
}