package astf2.nlp.twitteranalyse;

/**
 * Created by alistair on 16/09/2016.
 */

public class Parameter {
    String name;
    String value;
    boolean separated;

    Parameter(String name, String value, boolean separated) {
        this.name = name;
        this.value = value;
        this.separated = separated;
    }
}
