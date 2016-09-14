package astf2.nlp.twitteranalyse;

import jdk.nashorn.api.scripting.JSObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class TwitterPull {

    private static String queryprefix = "https://api.twitter.com/1.1/search/tweets.json?q=";


    //use twitter search API with string
    public static String search(String s) {
        String s2 = s.replace(" ", "%20"); //remove spaces in string
        String s3 = s.replace("@","%40");
        //TODO, deal with other symbols in the query, e.g. #
        String query_string = (queryprefix.concat(s2)).concat("&count=100"); //adjust count to increase number of results
        HttpURLConnection connection;
        /*String auth = authenticate();
        JSONObject json = new JSONObject(auth);*/
        String auth = Keys.auth_token;
        try {
            URL url = new URL(query_string);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestProperty("Authorization", (("Bearer ").concat(auth)));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        /*finally {
            if (connection != null) {
                connection.disconnect();
            }
        }*/
    }

    private static String authenticate() { //fetches authentication token used by search
        String consumerKey = Keys.consumerKey;
        String consumerSecret = Keys.consumerSecret;
        String concatenated = (consumerKey.concat(":")).concat(consumerSecret);
        byte[] bytes = concatenated.getBytes();
        String encoded = Base64.getEncoder().encodeToString(bytes);
        String contents = "grant_type=client_credentials";
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL("https://api.twitter.com/oauth2/token");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestProperty("Authorization", (("Basic ").concat(encoded)));
            connection.setRequestProperty("Content-Length",
                    Integer.toString(contents.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(contents);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
