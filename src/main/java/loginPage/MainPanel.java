package loginPage;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainPanel extends VBox {
    private final VBox body;
    public MainPanel(Stage stage){
        body = new VBox(10);
        final Button exit = new Button("Exit");
        final Text text = new Text("You are successfully entered the system\n Congratulations!!!");
        body.setAlignment(Pos.CENTER);
        text.setFont(Font.font("Consolas", FontWeight.BOLD,15));
        body.getChildren().addAll(
                text,
                exit
        );
                exit.setOnMouseClicked(event -> stage.close());
    }
    public void goToMain(Stage stage){
        Scene scene = new Scene(this.body,350,100);
        stage.setTitle("Main Program");
        stage.setScene(scene);
        stage.show();
    }
}
