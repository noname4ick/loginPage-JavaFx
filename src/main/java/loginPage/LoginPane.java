package loginPage;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginPane extends GridPane {
    GridPane body;
    Label usernameLabel;
    Label psswdLabel;
    TextField username;
    TextField password;
    Button login;

    public LoginPane(Stage stage) {
        //body
        this.body = new GridPane();
        body.setPadding(new Insets(15));
        body.setVgap(10);
        body.setHgap(10);
        usernameLabel = new Label("User Name");
        psswdLabel = new Label("Password");
        username = new TextField();
        password = new TextField();
        login = new Button("Sign in");
        body.add(usernameLabel, 0, 0);
        body.add(username, 1, 0);
        body.add(psswdLabel, 0, 1);
        body.add(password, 1, 1);

        login.setOnMouseClicked(event -> {
            int returned = checkCredentials(
                    username.getText(),
                    password.getText()
            );
            switch (returned) {
                case 1:
                    showMessage(
                            "Login",
                            "Success",
                            "You are successfully signed in to the system",
                            Alert.AlertType.INFORMATION);
                    MainPanel main = new MainPanel(stage);
                    main.goToMain(stage);
                    break;
                case 0:
                    showMessage("Login",
                            "Incorrect Password",
                            "You are entering incorrect password",
                            Alert.AlertType.INFORMATION);
                    break;
                case 2:
                    showMessage("Login",
                            "UserNotFound",
                            "You are not registered before please sign up",
                            Alert.AlertType.INFORMATION);
                    break;
            }
        });
    }
    public GridPane getBody(){
        return this.body;
    }
    public Button getLogin(){
        return this.login;
    }
    public int checkCredentials(String username,
                                String password) {
        File file = new File("users.txt");
        boolean userFound = false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] parts = line.split("\\|");

                if (parts.length >= 2) {
                    String fileUser = parts[0].trim();
                    String filePass = parts[1].trim();

                    if (fileUser.equals(username)) {
                        userFound = true;
                        if (filePass.equals(password)) {
                            return 1;
                        }
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return 2;
        }

        if (userFound) {
            return 0;
        } else {
            return 2;
        }
    }
    public void showMessage(String title,
                            String header,
                            String content,
                            Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
        alert.setResizable(false);
    }
}