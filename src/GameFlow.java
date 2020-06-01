import java.io.File;
import java.util.List;
import biuoop.GUI;
import biuoop.KeyboardSensor;
/**
* the class runs the game every time.
**/
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter livesCounter;
    private Counter score;
    private GUI gui;

    public static final int LIVES_NUMBER = 2;
    public static final int SCORE = 0;
    public static final int TABLE_SIZE = 6;
/**
* constructors.
* @param ar animationRunner.
* @param ks keyboard.
* @param g the gui.
**/
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI g) {
        this.livesCounter = new Counter(LIVES_NUMBER);
        this.score = new Counter(SCORE);
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = g;
    }

    /**
    * runs the menu and the levels.
    *@param lev the levels set.
    **/
    public void runLevels(List<LevelChar> lev) {
            File file = new File("highscore.txt");
            HighScoresTable t = new HighScoresTable(TABLE_SIZE);
            t = HighScoresTable.loadFromFile(file);
            t.setOrder();
            HighScoresTable tr = t;

            Task<Void> end = new Task<Void>() {
                @Override
                public Void run() {
                    System.exit(0);
                    return null;
                }

                @Override
                public void setKs(KeyboardSensor keyboardSensori) {
                    // TODO Auto-generated method stub
                }
            };



            while (true) {
                HighScoresAnimation highScore = new HighScoresAnimation(t);
                KeyPressStoppableAnimation  keyr = new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, highScore);
                Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(this.keyboardSensor);
                menu.addSelection("h", "Arkanoid:\n Hi scores", new ShowHiScoresTask(this.animationRunner, keyr));
                menu.addSelection("q", "quit", end);
                Menu<Task<Void>> menuSub = new MenuAnimation<Task<Void>>(this.keyboardSensor);
                for (int i = 0; i < lev.size(); i++) {
                Task<Void> a = new RunTime<Void>(lev.get(i).getInfo(), animationRunner ,
                this.gui, this.livesCounter, this.score, file, tr);
                a.setKs(this.keyboardSensor);
                menuSub.addSelection(lev.get(i).getLetter(), lev.get(i).getDescription(), a);
                }
                menu.addSubMenu("s", "start", menuSub);
                this.animationRunner.run(menu);
                // wait for user selection
                Task<Void> task = menu.getStatus();
                task.run();
                this.score = new Counter(SCORE);
                this.livesCounter = new Counter(LIVES_NUMBER);
            }
        }
}