package astf2.nlp.twitteranalyse;

/**
 * Created by alistair on 14/09/2016.
 */
public class City {
    String name;
    String longitude;
    String latitude;
    String radius;

    City(String name, String longitude, String latitude, String radius) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
    }
}
