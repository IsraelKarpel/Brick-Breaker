import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
* the class holds the highscore table.
**/
class HighScoresTable {
    private int tableSize;
    private List<ScoreInfo> highScores = new ArrayList<ScoreInfo>();
        /**
        * Create an empty high-scores table with the specified size.
        * The size means that the table holds up to size top scores.
        * @param size of the table.
        **/
        public HighScoresTable(int size) {
            this.tableSize = size;
        }
        /**
        * Add a high-score.
        * @param score the score of the player.
        **/
        public void add(ScoreInfo score) {
        highScores.add(score);
        }
        /**
        * Remove a high-score.
        * @param num to remove.
        **/
        public void remove(int num) {
            highScores.remove(num);
        }
        /**
        * set the order of the table from the bigger.
        **/
        public void setOrder() {
            Collections.sort(this.highScores, new SortScore());
            Collections.reverse(this.highScores);
        }
        /**
        * Return table size.
        * @return int the size.
        **/
        public int size() {
        return this.tableSize;
        }
        /**
        * Return the current high scores.
        * The list is sorted such that the highest scores come first.
        * @return List of the scores sort.
        **/
        public List<ScoreInfo> getHighScores() {
        //Collections.sort(this.highScores, new SortScore());
        //Collections.reverse(this.highScores);
        return this.highScores;
        }
        /**
        * return the rank of the current score: where will it be on the list if added?
        * Rank 1 means the score will be highest on the list.
        * Rank `size` means the score will be lowest.
        * Rank > `size` means the score is too low and will not be added to the list.
        * @param score the score of the player.
        * @return the number that indicates his rank.
        **/
        public int getRank(int score) {
        if (this.highScores.size() == 0) {
            return 1;
            }
            for (int i = this.highScores.size() - 1; i >= 0; i--) {
            if (score < this.highScores.get(i).getScore()) {
            return i + 2;
                }
            }
            return 1;
        }
        /**
        *    Clears the table.
        **/
        public void clear() {
        this.highScores.clear();
        }
        /**
        * Load table data from file.
        * Current table data is cleared.
        * @param a the file
        * @throws IOException not found
        **/
        public void load(File a) throws IOException {
            this.highScores.clear();
            //FileReader fr = null;
            BufferedReader reader = null;
            try {
            FileReader fr = new FileReader(a);
            reader = new BufferedReader(fr);
            String line;
            String name;
            int score;
            while ((line = reader.readLine()) != null) {
            name = line;
            line = reader.readLine();
            score = Integer.parseInt(line);
            ScoreInfo newi = new ScoreInfo(name, score);
            this.highScores.add(newi);
            }
          //reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                        } catch (IOException e) {
                        System.out.println("Failed closing the file!");
                        }
                }
            }
        }
        /**
        * Save table data to the specified file.
        * @param a the file.
        * @throws IOException not found.
        */
        public void save(File a) throws IOException {
            //this.highScores.clear();
                try {
                FileWriter fw = new FileWriter(a);
                BufferedWriter writer = new BufferedWriter(fw);
                String name;
                int score;
                for (int i = 0; i < this.highScores.size(); i++) {
                    name = highScores.get(i).getName();
                    score = highScores.get(i).getScore();
                    writer.write(name + "\n");
                    writer.write(Integer.toString(score) + "\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                    }
            }
        /**
        *Read a table from file and return it.
            * If the file does not exist, or there is a problem with
        * reading it, an empty table is returned.
        * @param a the file.
        * @return HighScoreTable.
        **/
        public static HighScoresTable loadFromFile(File a) {
            HighScoresTable table = new HighScoresTable(6);
            BufferedReader reader = null;
            FileReader fr = null;
            try {
                    fr = new FileReader(a);
                    reader = new BufferedReader(fr);
                    String line;
                    String name;
                    int score;
                    while ((line = reader.readLine()) != null) {
                        name = line;
                        line = reader.readLine();
                        score = Integer.parseInt(line);
                        ScoreInfo newi = new ScoreInfo(name, score);
                        table.highScores.add(newi);
                    }
                    reader.close();
                } catch (IOException e) {
                    try {
                        table.save(a);
                    }  catch (IOException e1) {
                        System.out.println("Somthing worng");
                    }
                }
            return table;
    }
}