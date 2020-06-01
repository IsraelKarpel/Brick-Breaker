import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
* animate table that conatins the scores.
**/
public class HighScoresAnimation implements Animation {
    private HighScoresTable table;
        /**
        * constructor.
        * @param scores the table.
        **/
        public HighScoresAnimation(HighScoresTable scores) {
        this.table = scores;
        }

    @Override
    public void doOneFrame(DrawSurface d) {
        List<ScoreInfo> highScores = new ArrayList<ScoreInfo>();
        highScores = this.table.getHighScores();
        for (int i = 0; i < highScores.size(); i++) {
            String name = highScores.get(i).getName();
            int score = highScores.get(i).getScore();
            d.drawText(10, d.getHeight() + ((i * 50) - 560), "Name : " + name + "    Score : " + score, 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}