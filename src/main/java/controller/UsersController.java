package controller;

import database.DatabaseConnection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class UsersController {
    @FXML private ListView<String> usersListView;

    private MongoCollection<Document> usersCollection;

    @FXML
    public void initialize() {
        usersCollection = DatabaseConnection.getDatabase().getCollection("users");
        ucitajKorisnike();
    }

    @FXML
    public void ucitajKorisnike() {
        usersListView.getItems().clear();

        for (Document doc : usersCollection.find()) {
            String username = doc.getString("username");
            usersListView.getItems().add("Korisnik: " + username);
        }
    }

    @FXML
    public void handleBack() {
        usersListView.getScene().getWindow().hide();
    }
}