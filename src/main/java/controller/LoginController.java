package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    public void handleLogin() {
        System.out.println("Kliknut Login: " + usernameField.getText());
    }

    @FXML
    public void handleRegister() {
        System.out.println("Kliknut Register: " + usernameField.getText());
    }
}