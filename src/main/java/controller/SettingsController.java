package controller;

import com.mongodb.client.MongoCollection;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import org.bson.Document;

public class SettingsController {

    @FXML private PasswordField passwordField;
    private MongoCollection<Document> usersCollection;

    @FXML
    public void initialize() {
        usersCollection = DatabaseConnection.getDatabase().getCollection("users");
    }

    @FXML
    public void handleChangePassword() {
        String newPass = passwordField.getText();
        if (newPass != null && !newPass.isEmpty()) {
            usersCollection.updateOne(
                    new Document("username", "admin"),
                    new Document("$set", new Document("password", newPass))
            );
            passwordField.clear();
        }
    }

    @FXML
    public void toggleTheme() {
        Parent root = passwordField.getScene().getRoot();
        String currentStyle = root.getStyle();
        if (currentStyle.contains("#1a1a1a")) {
            root.setStyle("-fx-background-color: #2c3e50;");
        } else {
            root.setStyle("-fx-background-color: #1a1a1a;");
        }
    }

    @FXML
    public void handleBack() {
        passwordField.getScene().getWindow().hide();
    }
}