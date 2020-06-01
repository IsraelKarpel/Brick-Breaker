import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
* shows the animations of the menu.
* @param <T>
**/
public class MenuAnimation<T> implements Menu<T>  {
    private List<MenuSelection<T>> names = new ArrayList<MenuSelection<T>>();
    private KeyboardSensor keyboard = null;
    private T statue = null;
    private boolean mkmk = false;
    private boolean stop = false;
    private String letter = null;
    private Menu<T> menuSub = null;

    /**
    * constructor.
    * @param keyb the keyboard.
    **/
    public MenuAnimation(KeyboardSensor keyb) {
        this.keyboard = keyb;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle(0, 0, 800, 600);
        if (!mkmk) {
        d.setColor(Color.green);
        d.drawText(10, d.getHeight() - 400, names.get(0).getMesssage(), 32);
        d.setColor(Color.red);
        d.drawText(10, d.getHeight() - 300, names.get(1).getMesssage(), 32);
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() - 200, names.get(2).getMesssage(), 32);

        for (int i = 0; i < names.size(); i++) {
            if (this.keyboard.isPressed(names.get(i).getKey())) {
                if (!names.get(i).getB()) {
                this.statue = names.get(i).getReturnVal();
                this.letter = names.get(i).getKey();
                this.stop = true;
                return;
                } else {
                    this.mkmk = true;
                    this.menuSub = names.get(i).getMenu();
                }
            }
        }
    } else {
            this.menuSub.doOneFrame(d);
        }
        }

    @Override
    public boolean shouldStop() {
        if (!mkmk) {
        return this.stop;
    } else {
        return this.menuSub.getStop();
        }
    }
    @Override
    public void addSelection(String key, String message, T returnVal) {
        MenuSelection<T> select = new MenuSelection<T>(key, message, returnVal, null, false);
        names.add(select);

    }
    @Override
    public T getStatus() {
        if (!mkmk) {
        return this.statue;
        } else {
            return this.menuSub.getStatus();
        }
    }

    @Override
    public String getKey() {
        return this.letter;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        MenuSelection<T> select = new MenuSelection<T>(key, message, null, subMenu, true);
        names.add(select);
    }

    /**
    * returns if ahoulf stop.
    * @return boolean true or false.
    **/
    public boolean getStop() {
        return this.stop;
    }
}