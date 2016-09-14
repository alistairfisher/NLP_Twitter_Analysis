package astf2.nlp.twitteranalyse;

import java.util.ArrayList;

/**
 * Created by alistair on 13/09/2016.
 */
public class JSONParser {
    //currently just produce a list of strings corresponding to text

    public static ArrayList<String> messagesFromJSON(String JSON) {
        String[] splitJSON = JSON.split(",");
        ArrayList<String> result = new ArrayList<String>(); //reduce down to just the text components of the JSON
        for (String s : splitJSON) {
            if (s.startsWith("\"text\"")) {
                result.add(s);
            }
        }
        for (String s : result) {
            s = extractMessage(s);
            System.out.println(s);
        }
        System.out.printf("%d results", (result.size()));
        return result;
    }

    //extract the message from the attribute/value pair in the JSON
    private static String extractMessage(String pair) {
        String[] split = pair.split(":", 2);
        String unprocessedmessage = split[1];
        //need some final preprocessing: removing leading ' "' and trailing ".
        return (unprocessedmessage.substring(1, unprocessedmessage.length() - 1));
    }

}
