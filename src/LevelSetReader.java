import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;
import java.io.InputStream;
/**
the class reads the level set from text..
**/
public class LevelSetReader {
        /**
        * reads the text, saves it as levelChar and returns all of them.
        * @param reader reads the text.
        * @return list of levelChar.
        **/
        public List<LevelChar> fromReader(java.io.Reader reader) {
        List<LevelInformation> info = new ArrayList<LevelInformation>();
        LevelChar ch;
        String line;
        String[] arr;
        List<String> letter = new ArrayList<String>();
        List<String> descript = new ArrayList<String>();
        List<String> addres = new ArrayList<String>();
        BufferedReader is = new BufferedReader(reader); // wrapper that reads ahead
        try {
            while ((line = is.readLine()) != null) {
                arr = line.split(":");
                letter.add(arr[0]);
                descript.add(arr[1]);
                line = is.readLine();
                addres.add(line);
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
        List<LevelChar> setter = new ArrayList<LevelChar>();
        LevelChar c;
        InputStream isr = null;
        LevelSpecificationReader l = new LevelSpecificationReader();
        Reader readers = null;
            try {
            for (int i = 0; i < letter.size(); i++) {
            isr =  ClassLoader.getSystemClassLoader().getResourceAsStream(addres.get(i));
            readers = new InputStreamReader(isr);
            info = l.fromReader(readers);
            c = new LevelChar(letter.get(i), descript.get(i), info);
            setter.add(c);
            }
        } finally {
                if (isr != null) { // Exception might have happened at constructor
                    try {
                        isr.close(); // closes FileInputStream too
                    } catch (IOException e) {
                        System.out.println("Failed closing the file!");
                        }
                        }
                    }
        return setter;
    }
}