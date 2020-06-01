import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
* Wrap the screens.
**/
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keySensor;
    private String str;
    private Animation animate;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
    * Constructors.
    * @param sensor the keyboard.
    * @param key the string to pass in order to move forward
    * @param animation the animation.
    **/
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keySensor = sensor;
        this.str = key;
        this.animate = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
        }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animate.doOneFrame(d);
        if (this.keySensor.isPressed(this.str)) {
            if (isAlreadyPressed) {
                return;
            } else {
                stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
    return this.stop;
    }
}