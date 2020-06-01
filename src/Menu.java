/**
* represent menu of options.
* @param <T>
*/
public interface Menu<T> extends Animation {
    /**
    * add new option to the menu.
    * @param key the letter to press tp choose this option.
    * @param message the description.
    * @param returnVal what will happen.
    **/
    void addSelection(String key, String message, T returnVal);
        /**
        * return what should happen.
        * @return the statue.
        **/
        T getStatus();
        /**
        * add new menu option to the menu.
        * @param key the letter to press tp choose this option.
        * @param message the description.
        * @param subMenu thw menu to represent.
        **/
        void addSubMenu(String key, String message, Menu<T> subMenu);
        /**
        * @return whethe the menu should stop.
        **/
            boolean getStop();
        /**
        * @return String the key.
        **/
        String getKey();
}