package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnection {

    private static final String URI = "mongodb://localhost:27017";
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {

            mongoClient = MongoClients.create(URI);
        }

        return mongoClient.getDatabase("LifeManagementDB");
    }
}