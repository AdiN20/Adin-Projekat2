package controller;

import database.DatabaseConnection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    public void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        try {
            MongoCollection<Document> users = DatabaseConnection.getDatabase().getCollection("users");
            Document found = users.find(new Document("username", user).append("password", pass)).first();

            if (found != null) {

                URL resource = getClass().getResource("/view/main_menu.fxml");

                if (resource == null) {
                    resource = getClass().getResource("/main_menu.fxml");
                }

                if (resource == null) {
                    resource = getClass().getClassLoader().getResource("view/main_menu.fxml");
                }

                if (resource == null) {
                    System.out.println("KRITIČNA GREŠKA: main_menu.fxml nije pronađen!");
                    prikaziAlert("Sistemska greška", "Fajl main_menu.fxml nije pronađen u resursima.");
                    return;
                }

                Parent root = FXMLLoader.load(resource);
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root, 600, 500));
                stage.setTitle("Glavni Meni");
                stage.centerOnScreen();
                stage.show();
            } else {
                prikaziAlert("Greška", "Pogrešno korisničko ime ili lozinka!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            prikaziAlert("Greška", "Došlo je do greške pri učitavanju prozora: " + e.getMessage());
        }
    }

    @FXML
    public void handleRegister() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            prikaziAlert("Greška", "Popunite sva polja!");
            return;
        }

        try {
            MongoCollection<Document> users = DatabaseConnection.getDatabase().getCollection("users");
            if (users.find(new Document("username", user)).first() != null) {
                prikaziAlert("Greška", "Korisnik već postoji!");
                return;
            }

            users.insertOne(new Document("username", user).append("password", pass));
            prikaziAlert("Uspjeh", "Registracija uspješna!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prikaziAlert(String naslov, String sadrzaj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setHeaderText(null);
        alert.setContentText(sadrzaj);
        alert.showAndWait();
    }
}