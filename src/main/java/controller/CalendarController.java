package controller;

import database.DatabaseConnection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class CalendarController {
    @FXML private TextField eventField;
    @FXML private DatePicker datePicker;
    @FXML private ListView<String> calendarListView;

    private MongoCollection<Document> calendarCollection;

    @FXML
    public void initialize() {
        calendarCollection = DatabaseConnection.getDatabase().getCollection("calendar");
        ucitajDogadjaje();
    }

    @FXML
    public void handleAddEvent() {
        String eventName = eventField.getText();
        LocalDate date = datePicker.getValue();

        if (eventName != null && !eventName.isEmpty() && date != null) {
            Document doc = new Document("event", eventName).append("date", date.toString());
            calendarCollection.insertOne(doc);

            eventField.clear();
            ucitajDogadjaje();
        }
    }

    private void ucitajDogadjaje() {
        calendarListView.getItems().clear();
        for (Document doc : calendarCollection.find()) {
            calendarListView.getItems().add(doc.getString("date") + " - " + doc.getString("event"));
        }
    }

    @FXML
    public void handleBack() {
        eventField.getScene().getWindow().hide();
    }
}