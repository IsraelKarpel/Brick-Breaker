import java.awt.Color;
/**
* convert from string to color.
**/
public class FromStringToColor {
    private String s;
/**
* constructor.
* @param sa the string we awnt to convert.
*/
    public FromStringToColor(String sa) {
        this.s = sa;
    }
/**
* the convertion.
* @return color.
*/
    public Color stringToColor() {
        if ((s.equals("BLACK")) || (s.equals("black"))) {
            return Color.black;
        }
        if ((s.equals("BLUE")) || (s.equals("blue"))) {
            return Color.blue;
        }
        if ((s.equals("YELLOW")) || (s.equals("yellow"))) {
            return Color.yellow;
        }
        if ((s.equals("RED")) || (s.equals("red"))) {
            return Color.red;
        }
        if ((s.equals("PINK")) || (s.equals("pink"))) {
            return Color.pink;
        }
        if ((s.equals("ORANGE")) || (s.equals("orange"))) {
            return Color.orange;
        }
        if ((s.equals("MAGENTA")) || (s.equals("magenta"))) {
            return Color.magenta;
        }
        if ((s.equals("BLACK")) || (s.equals("black"))) {
            return Color.black;
        }
        if ((s.equals("WHITE")) || (s.equals("white"))) {
            return Color.white;
        }
        if ((s.equals("GRAY")) || (s.equals("gray"))) {
            return Color.gray;
        }
        if ((s.equals("CYAN")) || (s.equals("cyan"))) {
            return Color.cyan;
        }
        if ((s.equals("GREEN")) || (s.equals("green"))) {
            return Color.green;
        }
        if ((s.equals("LIGHTGRAY")) || (s.equals("lightGray"))) {
            return Color.lightGray;
        }
        if ((s.equals("DARKGRAY")) || (s.equals("darkGray"))) {
            return Color.darkGray;
        }
    return null;
    }
}