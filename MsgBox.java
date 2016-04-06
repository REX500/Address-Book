

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Filip on 02-03-2016.
 */
public class MsgBox {
    public void display(String title, String msg){
        Stage window = new Stage();
        window.setTitle(title);

        Button ok = new Button("OK");

        Label check = new Label(msg);

        ok.setOnAction(e -> window.close());

        VBox box = new VBox(10);
        box.setSpacing(8);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(check,ok);

        Scene scene = new Scene(box, 200,100);
        window.setScene(scene);
        window.show();
    }
}
