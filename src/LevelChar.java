import java.util.List;
/**
the class saves the paraemer we want to have.
**/
public class LevelChar {
    private List<LevelInformation> info;
    private String let;
    private String desc;
    /**
        * Construct a RunTime given his parameters.
        * @param letter this will load specific level
        * @param descrip the description of the level
        * @param i kist of the levels.
        **/
    public LevelChar(String letter, String descrip, List<LevelInformation> i) {
        this.let = letter;
        this.desc = descrip;
        this.info = i;
    }
    /**
    * returns the letter.
    * @return the letter.
    **/
    public String getLetter() {
        return this.let;
    }
    /**
    * returns the description.
    * @return the description.
    **/
    public String getDescription() {
        return this.desc;
    }
    /**
    * returns the levels.
    * @return the levels.
    **/
    public List<LevelInformation> getInfo() {
        return this.info;
    }
}