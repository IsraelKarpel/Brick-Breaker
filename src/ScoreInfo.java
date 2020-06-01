/**
*holds the score information.
**/
public class ScoreInfo {
    private String playerName;
    private int playerScore;
    /**
    * constrictor.
    * @param name of the player
    * @param score is score.
    **/
    public ScoreInfo(String name, int score) {
        this.playerName = name;
        this.playerScore = score;
        }
    /**
    * @return the name.
    **/
    public String getName() {
        return this.playerName;
        }
    /**
    * @return the score.
    **/
    public int getScore() {
        return this.playerScore;
        }
}
