import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.io.Reader;

/**
the main class of all the game.
*/
public class Ass7Game {
    public static final int TABLE_SIZE = 6;
    /**
    * the main game.
    *@param args from the user.
    **/

    public static void main(String[] args) {
        String s;
        if (args.length == 0) {
            s = "level_sets.txt";
        } else {
            s = args[0];
        }
        GUI gui = new GUI("title", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(gui);
        GameFlow game = new GameFlow(ar, ks, gui);
        List<LevelChar> setter = new ArrayList<LevelChar>();
        LevelSetReader l = new LevelSetReader();
        InputStream is = null;
        Reader reader = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
            reader = new InputStreamReader(is);
                setter = l.fromReader(reader);
                } finally {
                        if (is != null) { // Exception might have happened at constructor
                            try {
                                is.close(); // closes FileInputStream too
                            } catch (IOException e) {
                                System.out.println("Failed closing the file!");
                                }
                                }
                            }
            game.runLevels(setter);
        }
}