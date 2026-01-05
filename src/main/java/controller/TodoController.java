package controller;

import com.mongodb.client.MongoCollection;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bson.Document;

public class TodoController {

    @FXML private TextField taskField;
    @FXML private ListView<String> taskListView;

    private MongoCollection<Document> todoCollection;

    @FXML
    public void initialize() {

        todoCollection = DatabaseConnection.getDatabase().getCollection("tasks");
        ucitajZadatke();
    }

    @FXML
    public void handleAddTask() {
        String taskText = taskField.getText();
        if (!taskText.isEmpty()) {

            Document doc = new Document("task", taskText).append("status", "pending");
            todoCollection.insertOne(doc);


            taskListView.getItems().add(taskText);
            taskField.clear();
        }
    }

    private void ucitajZadatke() {
        taskListView.getItems().clear();
        for (Document doc : todoCollection.find()) {
            taskListView.getItems().add(doc.getString("task"));
        }
    }

    @FXML
    public void handleBack() {

        taskField.getScene().getWindow().hide();
    }
}