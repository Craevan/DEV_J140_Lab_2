package ru.avalon.j140_2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.avalon.j140_2.auth.User;
import ru.avalon.j140_2.repo.Repository;
import ru.avalon.j140_2.stage.PersonStage;

import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(15);
        root.setVgap(15);
        root.setPadding(new Insets(25, 25, 25, 25));

        Label welcome = new Label("Welcome");
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        root.add(welcome, 0, 0, 2, 1);

        Label labelUserName = new Label("User Name");
        root.add(labelUserName, 0, 1);

        TextField userNameField = new TextField();
        root.add(userNameField, 1, 1);

        Label labelPassword = new Label("Password");
        root.add(labelPassword, 0, 2);

        PasswordField passwordField = new PasswordField();
        root.add(passwordField, 1, 2);

        Button button = new Button("Sign in");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(button);
        root.add(hBox, 1, 4);

        Text actionText = new Text();
        root.add(actionText, 1, 6);

        button.setOnAction(ActionEvent -> {
            List<User> users = new Repository().getUsers();
            for (User user : users) {
                if (userNameField.getText().equals(user.getLogin())
                        && passwordField.getText().equals(user.getPassword())) {
                    new PersonStage().show();
                } else {
                    actionText.setFill(Color.RED);
                    actionText.setText("Invalid login or password");
                }
            }
        });

        Scene scene = new Scene(root, 400, 350, Color.GAINSBORO);

        stage.setTitle("JFX-DBConnector");
        stage.setScene(scene);
        stage.show();
    }
}
