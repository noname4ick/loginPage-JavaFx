package loginPage;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RegPane extends GridPane {
    private final GridPane body;
    private final TextField username;
    private final TextField password;
    Button register;

    public RegPane(Stage stage, Scene scene) {
        //body
        final Label usernameLabel = new Label("User Name");
        final Label psswdLabel = new Label("Password");
        this.body = new GridPane();
        body.setPadding(new Insets(15));
        body.setVgap(10);
        body.setHgap(10);
        this.username = new TextField();
        this.password = new TextField();
        this.register = new Button("Sign up");
        body.add(usernameLabel, 0, 0);
        body.add(username, 1, 0);
        body.add(psswdLabel, 0, 1);
        body.add(this.password, 1, 1);

        register.setOnMouseClicked(event -> {
            int result = registerUser(this.username.getText(), this.password.getText());

            if (result == 1) {
                showMessage("Success",
                        "Information",
                        "Account created!",
                        Alert.AlertType.INFORMATION);
                stage.setScene(scene);
                stage.setTitle("Login Page");
            } else if (result == 0) {
                showMessage("Error",
                        "Registration Failed",
                        "Username is already taken.",
                        Alert.AlertType.ERROR);
            }else if(result == 2) {
                showMessage("Information",
                        "Registration Failed",
                        "You can't use whitespaces",
                        Alert.AlertType.INFORMATION);
            } else{
                System.out.println("File doesn't exists");
                showMessage("Error",
                        "Can't find file",
                        "Contact the developer.",
                        Alert.AlertType.WARNING);
            }
        });
    }
    public Button getRegister(){
        return this.register;
    }
    public GridPane getBody(){ return this.body;}
    public int registerUser(String username, String password) {
        File file = new File("users.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length >= 1) {
                    if (parts[0].trim().equalsIgnoreCase(username)) {
                        return 0;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something really bad happened here");
        }
        try (FileWriter fw = new FileWriter(file, true)) {
            String entry = username + "|" + password + System.lineSeparator();
            if(entry.contains(" ")){
                return 2;
            }
            fw.write(entry);
            return 1;
        } catch (IOException e) {
            System.out.println("Error happened here as well");
            return -1;
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