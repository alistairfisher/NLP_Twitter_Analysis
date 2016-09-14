package astf2.nlp.twitteranalyse;

import java.io.*;

/**
 * Created by alistair on 13/09/2016.
 */
public class Utility {

    static void writeStringToFile(String string, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(string);
        } catch (IOException e) {
            System.out.printf("File %s not found\n", filename);
            e.printStackTrace();
        }

    }
}
