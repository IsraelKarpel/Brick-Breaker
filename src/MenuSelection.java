/**
* holds the selction options and their information.
* @param <T>
**/
public class MenuSelection<T> {
    private String key1;
    private String message1;
    private T returnVal1;
    private Menu<T> menu;
    private boolean bool;
    /**
    * constructor.
    * @param key the string the user pressed.
    * @param message the descripton.
    * @param returnVal the Task shoulld happen
    * @param m the menu if there is one.
    * @param b boolean.
    **/
    public MenuSelection(String key, String message, T returnVal, Menu<T> m, boolean b) {
        this.key1 = key;
        this.message1 = message;
        this.returnVal1 = returnVal;
        this.bool = b;
        this.menu = m;
    }
    /**
    * @return the key of thies option.
    **/
    public String getKey() {
        return key1;
    }
    /**
    * @return the message of thies option.
    **/
    public String getMesssage() {
        return message1;
    }
    /**
    * @return the task of thies option.
    **/
    public T getReturnVal() {
        return returnVal1;
    }
    /**
    * @return the boolean of thies option.
    **/
    public boolean getB() {
        return this.bool;
    }
    /**
    * @return the menu of thies option.
    **/
    public Menu<T> getMenu() {
        return this.menu;
    }
}