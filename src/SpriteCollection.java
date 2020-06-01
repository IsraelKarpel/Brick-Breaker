import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
the class holds the collection of our sprites.
*/
    public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<>();

    /**
    * add the sprite to the list.
    @param s the current Sprite.
    */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
    * remove the sprite to the list.
    @param s the current Sprite.
    */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

   /**
    * call timePassed() on all sprites.
    */
    public void notifyAllTimePassed() {
        List<Sprite> newSprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : newSprites) {
            s.timePassed();
        }
    }

   /**
    * call drawOn(d) on all sprites.
    * @param d the drawface
    */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);
        }
    }
}