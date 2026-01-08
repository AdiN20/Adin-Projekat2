package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import javafx.scene.Scene;
import org.bson.Document;

public class DatabaseConnection {

    private static final String URI = "mongodb://localhost:27017";
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient.getDatabase("LifeManagementDB");
    }

    public static void primijeniBoju(Scene scene) {
        try {
            var usersCollection = getDatabase().getCollection("users");
            Document user = usersCollection.find(new Document("username", "admin")).first();


            String boja = "#1a1a1a";

            if (user != null && user.containsKey("themeColor")) {
                boja = user.getString("themeColor");
            }

            scene.getRoot().setStyle("-fx-background-color: " + boja + ";");

            scene.getStylesheets().add(DatabaseConnection.class.getResource("/view/style.css").toExternalForm());

        } catch (Exception e) {
            System.out.println("Greška kod teme: " + e.getMessage());
        }
    }
}