package controller;

import database.DatabaseConnection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    public void handleRegister() {
        String user = usernameField.getText();
        String pass = passwordField.getText();


        if (user.isEmpty() || pass.isEmpty()) {
            prikaziAlert("Greška", "Sva polja moraju biti popunjena!");
            return;
        }

        try {

            MongoCollection<Document> users = DatabaseConnection.getDatabase().getCollection("users");


            Document postojeciKorisnik = users.find(new Document("username", user)).first();
            if (postojeciKorisnik != null) {
                prikaziAlert("Greška", "Korisničko ime je već zauzeto!");
                return;
            }


            Document newUser = new Document("username", user).append("password", pass);
            users.insertOne(newUser);

            prikaziAlert("Uspjeh", "Uspješno ste se registrovali!");


            usernameField.clear();
            passwordField.clear();

        } catch (Exception e) {
            prikaziAlert("Baza podataka", "Greška pri povezivanju: " + e.getMessage());
        }
    }

    @FXML
    public void handleLogin() {

        System.out.println("Kliknut login.");
    }

    private void prikaziAlert(String naslov, String sadrzaj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setHeaderText(null);
        alert.setContentText(sadrzaj);
        alert.showAndWait();
    }
}