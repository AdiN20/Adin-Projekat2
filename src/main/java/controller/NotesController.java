package controller;

import com.mongodb.client.MongoCollection;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.bson.Document;

public class NotesController {
    @FXML private TextArea noteTextArea;
    @FXML private ListView<String> notesListView;



    private MongoCollection<Document> notesCollection;

    @FXML
    public void initialize() {



        notesCollection = DatabaseConnection.getDatabase().getCollection("notes");
        ucitajBiljeske();
    }

    @FXML
    public void handleSaveNote() {
        String content = noteTextArea.getText();
        if (!content.isEmpty()) {

            Document doc = new Document("content", content)
                    .append("timestamp", java.time.LocalDateTime.now().toString());
            notesCollection.insertOne(doc);

            noteTextArea.clear();
            ucitajBiljeske();
        }
    }

    private void ucitajBiljeske() {
        notesListView.getItems().clear();
        for (Document doc : notesCollection.find()) {
            notesListView.getItems().add(doc.getString("content"));
        }
    }

    @FXML
    public void handleBack() {
        noteTextArea.getScene().getWindow().hide();
    }

    @FXML
    public void handleOpenNotes() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/notes.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Bilješke");
            stage.setScene(new Scene(root, 450, 500));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}