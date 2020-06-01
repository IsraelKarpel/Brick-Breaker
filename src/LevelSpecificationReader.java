import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
/**
* reads teh text and devide it to levels.
**/
public class LevelSpecificationReader {
    /**
    * reads the levels and combine them to tone set.
    * @param reader to read the text.
    * @return List of livels set.
    **/
    public List<LevelInformation> fromReader(java.io.Reader reader) {
    List<LevelInformation> info = new ArrayList<LevelInformation>();
    String[] arr, arr2, arr3;
    char[] liner;
    int ballsNumber;
    String line;
    BufferedReader is = new BufferedReader(reader); // wrapper that reads ahead
        try {
            while ((line = is.readLine()) != null) {
                if (line.startsWith("START_LEVEL")) {
                LevelSome level = new LevelSome();
                List<Velocity> velo = new ArrayList<Velocity>();
                List<char[]> table = new ArrayList<char[]>();
                while (!(line.startsWith("END_LEVEL"))) {
                    line = is.readLine();

            if (line.startsWith("level_name")) {
                arr = line.split(":");
                level.setLevelName(arr[1]);
            }
            if (line.startsWith("ball_velocities")) {
                arr = line.split(":");
                arr2 = arr[1].split(" ");
                ballsNumber = arr2.length;
                for (int i = 0; i < arr2.length; i++) {
                    arr3 = arr2[i].split(",");
                    Velocity v = Velocity.fromAngleAndSpeed(Double.parseDouble(arr3[0]), Double.parseDouble(arr3[1]));
                    velo.add(v);
                }
                level.setVelocities(velo);
                level.setBallsNumber(ballsNumber);
            }
            if (line.startsWith("background")) {
                arr = line.split("\\(");
                if (arr[1].startsWith("RGB")) {
                    arr2 = arr[2].split(",");
                    arr3 = arr2[2].split("\\)");
                    Color c = new Color(Integer.parseInt(arr2[0]),
                            Integer.parseInt(arr2[1]), Integer.parseInt(arr3[0]));
                    level.setColor(c);
                } else {
                    if (arr[0].startsWith("background:color")) {
                        arr2 = arr[1].split("\\)");
                        FromStringToColor change = new FromStringToColor(arr2[0]);
                        Color c = change.stringToColor();
                        level.setColor(c);
                    } else {
                        arr3 = arr[1].split("\\)");
                        Image img = null;
                        try {
                            img = ImageIO.read(new File(arr3[0]));
                            level.setImage(img);
                        } catch (IOException e) {
                            System.out.println("Something went wrong while img!");
                        }
                    }
                }
            }
            if (line.startsWith("paddle_speed")) {
                arr = line.split(":");
                level.setPaddleSpeed(Integer.parseInt(arr[1]));
            }
            if (line.startsWith("block_definitions")) {
                arr = line.split(":");
                level.setBlockDefination(arr[1]);
            }
            if (line.startsWith("paddle_width")) {
                arr = line.split(":");
                level.setPaddleWidth(Integer.parseInt(arr[1]));
            }
            if (line.startsWith("blocks_start_x")) {
                arr = line.split(":");
                level.setBlockStartX(Integer.parseInt(arr[1]));
            }
            if (line.startsWith("blocks_start_y")) {
                arr = line.split(":");
                level.setBlockStartY(Integer.parseInt(arr[1]));
            }
            if (line.startsWith("row_height")) {
                arr = line.split(":");
                level.setRowHieght(Integer.parseInt(arr[1]));
            }
            if (line.startsWith("num_blocks")) {
                arr = line.split(":");
                level.setnumBlock(Integer.parseInt(arr[1]));
            }
            if (line.startsWith("START_BLOCKS")) {
                line = is.readLine();
                    while (!(line.startsWith("END_BLOCKS"))) {
                    liner = line.toCharArray();
                    table.add(liner);
                    line = is.readLine();
                    }
                    level.setLayout(table);
            }
        }
                info.add(level);
                }
            }
            return info;
            } catch (IOException e) {
                    System.out.println("Something went wrong while reading!");
                } finally {
                        if (is != null) { // Exception might have happened at constructor
                            try {
                                is.close(); // closes FileInputStream too
                            } catch (IOException e) {
                                System.out.println("Failed closing the file!");
                                }
                                }
                            }
        return null;
    }
}