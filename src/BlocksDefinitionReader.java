import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
/**
* creates the different blocks.
**/
public class BlocksDefinitionReader {

    /**
    * makes the blocks.
    * @param reader reads the text.
    * @return lists of the blocks.
    **/
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory block = new BlocksFromSymbolsFactory();
        Map<String, Integer> spacerWidths = new HashMap<String, Integer>();
        Map<String, BlockCreator> blockCreators = new HashMap<String, BlockCreator>();
        String line;
        String[] data, data2, data3,  data4;
        Color storke = null, color = null;
        int height = -1, width = -1, hitPoints = -1;
        Image image = null;
        BufferedReader is = new BufferedReader(reader); // wrapper that reads ahead
        try {
            while ((line = is.readLine()) != null) {
                    if (line.startsWith("default")) {
                        data = line.split(" ");
                        for (int i = 1; i < data.length; i++) {
                            if (data[i].startsWith("height")) {
                                data2 = data[i].split(":");
                                height = Integer.parseInt(data2[1]);
                            }
                            if (data[i].startsWith("width")) {
                                data2 = data[i].split(":");
                                width = Integer.parseInt(data2[1]);
                            }
                            if (data[i].startsWith("fill:")) {
                                data2 = data[i].split("\\(");
                                if (data2[1].startsWith("RGB")) {
                                    data3 = data[2].split(",");
                                    data4 = data3[2].split("\\)");
                                    color = new Color(Integer.parseInt(data3[0]),
                                            Integer.parseInt(data3[1]), Integer.parseInt(data4[1]));
                                } else {
                                    if (data2[0].startsWith("fill:color")) {
                                        data3 = data2[1].split("\\(");
                                        FromStringToColor change = new FromStringToColor(data3[0]);
                                        color = change.stringToColor();
                                    } else {
                                        data3 = data2[1].split("\\)");
                                        Image img = null;
                                        try {
                                            img = ImageIO.read(new File("resources/" + data3[0]));
                                        } catch (IOException e) {
                                            System.out.println("Something went wrong while img!");
                                        }
                                    }
                                }
                            }
                            if (data[i].startsWith("hit")) {
                                data2 = data[i].split(":");
                                hitPoints = Integer.parseInt(data2[1]);
                            }
                            if (data[i].startsWith("stroke:")) {
                                data2 = data[i].split(":");
                                if (data2[1].startsWith("RGB")) {
                                    data3 = data[2].split(",");
                                    data4 = data3[2].split("\\)");
                                    storke = new Color(Integer.parseInt(data3[0]),
                                            Integer.parseInt(data3[1]), Integer.parseInt(data4[1]));
                                } else {
                                    if (data2[1].startsWith("color")) {
                                        data3 = data2[1].split("\\)");
                                        data4 = data3[0].split("\\(");
                                        FromStringToColor change = new FromStringToColor(data4[1]);
                                        storke = change.stringToColor();
                                        }
                                    }
                                }
                            }
                    } else {
                        if (line.startsWith("bdef")) {
                            BlockHelper b = bdef(line);
                            //check the defaults
                            if (height != -1) {
                                b.setHeight(height);
                            }
                            if (width != -1) {
                                b.setWidth(width);
                            }
                            if (hitPoints != -1) {
                                b.setHit(hitPoints);
                            }
                            if (storke != null) {
                                b.setStrok(storke);
                            }
                            if (color != null) {
                                    b.setFillKColor(0, color);
                            if (image != null) {
                                    b.setFillKImage(0, image);
                                }
                        }
                            blockCreators.put(b.getSymbol(), b);
                        } else {
                            if (line.startsWith("sdef")) {
                                data = line.split(" ");
                                String[] symbols = data[1].split(":");
                                String[] widths = data[2].split(":");
                                spacerWidths.put(symbols[1], Integer.parseInt(widths[1]));
                            }
                        }
                    }
            }
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
        block.setSpacers(spacerWidths);
        block.setBlocks(blockCreators);
        return block;
    }
    /**
    * cretes the blocks.
    * @param line the cuurent line from the text.
    * @return block.
    **/
    public static BlockHelper bdef(String line) {
        String[] data, data2, data3, data4;
        int k;
        BlockHelper b = new BlockHelper();
        data = line.split(" ");
        for (int i = 1; i < data.length; i++) {
            if (data[i].startsWith("symbol")) {
                data2 = data[i].split(":");
                b.setSymbol(data2[1]);
            }

            if (data[i].startsWith("height")) {
                data2 = data[i].split(":");
                b.setHeight(Integer.parseInt(data2[1]));
            }
            if (data[i].startsWith("width")) {
                data2 = data[i].split(":");
                b.setWidth(Integer.parseInt(data2[1]));
            }
            if (data[i].startsWith("fill:")) {
                data2 = data[i].split("\\(");
                if (data2[1].startsWith("fill:RGB")) {
                    data3 = data[2].split(",");
                    data4 = data3[2].split("\\)");
                    Color c = new Color(Integer.parseInt(data3[0]),
                            Integer.parseInt(data3[1]), Integer.parseInt(data4[1]));
                    b.setFillKColor(0, c);
                } else {
                    if (data2[0].startsWith("fill:color")) {
                        data3 = data2[1].split("\\(");
                        data4 = data3[0].split("\\)");
                        String chan = data4[0].toLowerCase();
                        FromStringToColor change = new FromStringToColor(chan);
                        Color c = change.stringToColor();
                        b.setFillKColor(0, c);
                    } else {
                        data3 = data2[1].split("\\)");
                        Image img = null;
                        try {
                            img = ImageIO.read(new File(data3[0]));
                            b.setFillKImage(0, img);
                        } catch (IOException e) {
                            System.out.println("Something went wrong while img!");
                        }
                    }
                }
            }
            if (data[i].startsWith("fill-")) {
                k = Integer.parseInt(data[i].substring(5, 6));
                data2 = data[i].split("\\(");
                if (data2[1].startsWith("RGB")) {
                    data3 = data2[2].split(",");
                    data4 = data3[2].split("\\)");
                    Color c = new Color(Integer.parseInt(data3[0]),
                            Integer.parseInt(data3[1]), Integer.parseInt(data4[0]));
                    b.setFillKColor(k, c);
                } else {
                    if (data2[0].endsWith("color")) {
                        data3 = data2[1].split("\\)");
                        //data4 = data3[0].split("\\)");
                        String chan = data3[0].toLowerCase();
                        FromStringToColor change = new FromStringToColor(chan);
                        Color c = change.stringToColor();
                        b.setFillKColor(k, c);
                    } else {
                        data3 = data2[1].split("\\)");
                        Image img = null;
                        try {
                            img = ImageIO.read(new File(data3[0]));
                            b.setFillKImage(k, img);
                        } catch (IOException e) {
                        System.out.println("Something went wrong while img!");
                        }
                    }
                }
            }
            if (data[i].startsWith("hit")) {
                data2 = data[i].split(":");
                b.setHit(Integer.parseInt(data2[1]));
            }
            if (data[i].startsWith("stroke:")) {
                data2 = data[i].split("");
                if (data2[1].startsWith("RGB")) {
                    data3 = data[2].split(",");
                    data4 = data3[2].split(")");
                    Color s = new Color(Integer.parseInt(data3[0]),
                            Integer.parseInt(data3[1]), Integer.parseInt(data4[1]));
                    b.setStrok(s);
                } else {
                    if (data2[0].startsWith("fill:color")) {
                        data3 = data2[1].split(")");
                        String chan = data3[0].toLowerCase();
                        FromStringToColor change = new FromStringToColor(chan);
                        Color c = change.stringToColor();
                        b.setStrok(c);
                        }
                    }
                }
            }
        return b;
    }
}