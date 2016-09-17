package astf2.nlp.twitteranalyse;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by alistair on 13/09/2016.
 */


public class GUI extends Application {
    /*
    Parameters:
        query (required)
        location
        before
        until
        language
        from
        to
        mentioning
        filters (checkboxes)
        attitude (positive,negative,question,checkboxes)
     */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Twitter Search - astf2");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10, 25, 25, 25));

        Text sceneTitle = new Text("Twitter Search");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 1, 0, 2, 1);

        Label queryLabel = new Label("Query*:");
        grid.add(queryLabel, 0, 1);

        TextField queryField = new TextField();
        grid.add(queryField, 1, 1);

        Label beginDateText = new Label("Tweets After:");
        grid.add(beginDateText, 0, 2);
        DatePicker beginDatePicker = new DatePicker();
        grid.add(beginDatePicker, 1, 2);

        Label finishDateText = new Label("Tweets Before:");
        grid.add(finishDateText, 0, 3);
        DatePicker finishDatePicker = new DatePicker();
        grid.add(finishDatePicker, 1, 3);

        Label city = new Label("City:");
        //grid.add(city,0,2);

        final ComboBox comboBox = new ComboBox();
        comboBox.setValue("Unspecified");
        //grid.add(comboBox,1,2);

        Button btn = new Button("Search");
        grid.add(btn, 1, 4);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String queryString = queryField.getCharacters().toString();
                LocalDate beginDate = beginDatePicker.getValue();
                if (beginDate == null) {
                    System.out.println("null");
                }
                LocalDate finishDate = finishDatePicker.getValue();
                Query query = new Query(queryString);
                query.addStartDate(beginDate);
                query.addEndDate(finishDate);
                String results = TwitterPull.search(query);
                System.out.println(results);
                JSONParser.messagesFromJSON(results);
            }
        });

        Scene scene = new Scene(grid, 500, 275);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(GUI.class.getResource("GUI.css").toExternalForm());
        primaryStage.show();
    }
}
