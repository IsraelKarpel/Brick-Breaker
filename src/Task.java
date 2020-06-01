import biuoop.KeyboardSensor;
/**
* the interface creats the Task for the Menu to run.
* @param <T>
**/
public interface Task<T> {
    /**
    * runs the task.
    * @return T task.
    **/
        T run();
    /**
    * set Keyboard.
    * @param keyboardSensor the keyboard.
    **/
        void setKs(KeyboardSensor keyboardSensor);
    }