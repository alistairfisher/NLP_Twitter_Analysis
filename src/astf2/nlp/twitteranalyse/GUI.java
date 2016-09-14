package astf2.nlp.twitteranalyse;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        grid.setPadding(new Insets(10, 25, 25, 25));

        Text sceneTitle = new Text("Twitter Search");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 1, 0, 2, 1);

        Label query = new Label("Query:");
        grid.add(query, 0, 1);

        TextField queryField = new TextField();
        grid.add(queryField, 1, 1);

        Button btn = new Button("Search");
        grid.add(btn, 1, 4);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String results = TwitterPull.search(queryField.getCharacters().toString());
                JSONParser.messagesFromJSON(results);
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(GUI.class.getResource("GUI.css").toExternalForm());
        primaryStage.show();
    }
}
