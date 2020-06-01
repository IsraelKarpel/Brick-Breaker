import java.io.File;
import java.io.IOException;
import java.util.List;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
/**
* the class creatws the Task for the Menu to run.
* @param <T>
**/
public class RunTime<T> implements Task<T> {
    private List<LevelInformation> levels;
    private AnimationRunner a;
    private KeyboardSensor k;
    private GUI g;
    private Counter live;
    private Counter score;
    private File file;
    private HighScoresTable tr;
    public static final int TABLE_SIZE = 6;
    /**
        * Construct a RunTime given his parameters.
        * @param levelss thie info on the levels.
        * @param ar the animation runner.
        * @param g the jui.
        * @param lives the number of lives the player has.
        * @param scores of the game.
        * @param file the file to read the table from.
        * @param tr the table of thr game.
    **/
    public RunTime(List<LevelInformation> levelss, AnimationRunner ar, GUI g,
            Counter lives, Counter scores, File file, HighScoresTable tr) {
        this.levels = levelss;
        this.a = ar;
        this.g = g;
        this.live = lives;
        this.score = scores;
        this.tr = tr;
        this.file = file;
    }

    @Override
    public T run() {
        for (LevelInformation levelInfo : levels) {
                GameLevel level = new GameLevel(levelInfo,
                    k,
                    a,
                    score,
                    live,
                    g
                    );
                level.initialize();
        while (live.getValue() != 0
                && level.getBlockCounter() != 0) {
            level.playOneTurn();
            level.setBallCounter(levelInfo.numberOfBalls());
            if (this.live.getValue() == 0) {
                GameOverAnimation lose = new GameOverAnimation(k, score);
                KeyPressStoppableAnimation key = new KeyPressStoppableAnimation(k, KeyboardSensor.SPACE_KEY, lose);
                a.run(key);
                int rank = tr.getRank(score.getValue());
                if (rank <= TABLE_SIZE) {
                    DialogManager dialog = g.getDialogManager();
                    String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                    ScoreInfo player = new ScoreInfo(name, score.getValue());
                    if (tr.getHighScores().size() == TABLE_SIZE) {
                        tr.remove(TABLE_SIZE - 1);
                        tr.add(player);
                    } else {
                        tr.add(player);
                    }
                    tr.setOrder();
                    try {
                        tr.save(file);
                    } catch (IOException e) {
                    e.printStackTrace();
                    }
                }
                HighScoresAnimation highScore1 = new HighScoresAnimation(tr);
                KeyPressStoppableAnimation keyr1 = new KeyPressStoppableAnimation(k, KeyboardSensor.SPACE_KEY,
                highScore1);
                    a.run(keyr1);
                    return null;
            }
            }
        }

        YouWinAnimation win = new YouWinAnimation(k, score);
            KeyPressStoppableAnimation key = new KeyPressStoppableAnimation(
            k, KeyboardSensor.SPACE_KEY, win);
            a.run(key);
            int rank = tr.getRank(score.getValue());
            if (rank <= TABLE_SIZE) {
                DialogManager dialog = g.getDialogManager();
                String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                ScoreInfo player = new ScoreInfo(name, score.getValue());
                if (tr.getHighScores().size() == TABLE_SIZE) {
                    tr.remove(TABLE_SIZE - 1);
                    tr.add(player);
                } else {
                    tr.add(player);
                }
                tr.setOrder();
            try {
                tr.save(file);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            }
            HighScoresAnimation highScore1 = new HighScoresAnimation(tr);
            KeyPressStoppableAnimation keyr1 = new KeyPressStoppableAnimation(k, KeyboardSensor.SPACE_KEY, highScore1);
            a.run(keyr1);
        return null;
    }

        @Override
    public void setKs(KeyboardSensor keyboardSensor) {
        this.k = keyboardSensor;
    }
}