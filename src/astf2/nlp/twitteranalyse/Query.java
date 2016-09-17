package astf2.nlp.twitteranalyse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by alistair on 16/09/2016.
 */
public class Query {

    private String query;

    public String getString() {
        return query;
    }

    private List<Parameter> parameters;

    public List<Parameter> getParameters() {
        return parameters;
    }

    Query(String query) {
        this.query = query;
        this.parameters = new ArrayList<Parameter>();
    }

    void addStartDate(LocalDate date) {
        DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
        String stringDate = date.format(format);
        parameters.add(new Parameter("since", stringDate));
    }

    void addEndDate(LocalDate date) {
        DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
        String stringDate = date.format(format);
        parameters.add(new Parameter("until", stringDate));
    }

    void addFilter(String filter) {
        parameters.add(new Parameter("filter", filter));
    }

}
