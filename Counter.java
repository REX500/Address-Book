import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Filip on 17-03-2016.
 */
public class Counter extends Application {
    public static void main(String[] args){
        launch(args);
    }
    int i;
    @Override
    public void start(Stage primary){
        primary.setTitle("Counter");
        VBox box =new VBox(10);

        box.setPadding(new Insets(50,10,10,60));

        Label label = new Label();
        label.setStyle("-fx-font-size: 20pt");

        i = 0;

        Button button = new Button("PLUS ONE");

        button.setOnAction(e-> {
            i++;
            label.setText(String.valueOf(i));
        });
        box.setMargin(label, new Insets(0,0,0,25));
        box.getChildren().addAll(label, button);

        Scene s = new Scene(box, 200,170);
        primary.setScene(s);
        primary.show();
    }
}
