package controller;

import database.DatabaseConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SettingsController {
    @FXML private PasswordField newPasswordField;

    @FXML
    public void handleChangePassword() {
        String newPass = newPasswordField.getText();
        if (!newPass.isEmpty()) {
            MongoCollection<Document> users = DatabaseConnection.getDatabase().getCollection("users");
            users.updateOne(new Document("username", "admin"), Updates.set("password", newPass));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Lozinka uspješno promijenjena!");
            alert.show();
        }
    }

    @FXML
    public void handleDeleteAccount() {

        System.out.println("Nalog obrisan.");
    }

    @FXML
    public void handleBack() {
        newPasswordField.getScene().getWindow().hide();
    }
}