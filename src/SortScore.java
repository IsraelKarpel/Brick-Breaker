import java.util.Comparator;
/**
* the class compare two scores to know whos bigger.
**/
public class SortScore implements Comparator<ScoreInfo> {

        /**
        * Used for sorting in ascending order of score.
        * @param a the first score.
        * @param b the second score.
        * @return the sub number.
        **/
        public int compare(ScoreInfo a, ScoreInfo b) {
            return a.getScore() - b.getScore();
        }
}