package loginPage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Login extends Application {
    @Override
    public void start(Stage stage){
        LoginPane body = new LoginPane(stage);
        Label info = new Label("Login page");
        info.setFont(Font.font("Consolas", FontWeight.BOLD, 16));
        Label register = new Label("Register here.");

        VBox root = new VBox(5);
        root.setPadding(new Insets(5));
        root.getChildren().addAll(
                info,
                body.getBody(),
                body.getLogin(),
                register);
        root.setAlignment(Pos.CENTER);
        Scene loginScene =  new Scene(root,300,200);
        stage.setScene(loginScene);
        stage.setTitle("Login Page");

        register.setOnMouseClicked(event -> {
            RegPane regPane = new RegPane(stage,loginScene);
            VBox reg = new VBox(5);
            reg.setPadding(new Insets(5));
            info.setText("Registration page");
            reg.getChildren().addAll(
                    info,
                    regPane.getBody(),
                    regPane.getRegister()
                    );
            reg.setAlignment(Pos.CENTER);
            Scene registrationScene =  new Scene(reg,300,200);
            stage.setScene(registrationScene);
            stage.setTitle("Registration Page");

        });

        stage.show();
    }
}
