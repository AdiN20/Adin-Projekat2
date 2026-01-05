package controller;

import database.DatabaseConnection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FinanceController {
    @FXML private TextField descriptionField;
    @FXML private TextField amountField;
    @FXML private ListView<String> expenseListView;
    @FXML private Label totalLabel;

    private MongoCollection<Document> financeCollection;

    @FXML
    public void initialize() {
        financeCollection = DatabaseConnection.getDatabase().getCollection("finances");
        ucitajTroskove();
    }

    @FXML
    public void handleAddExpense() {
        try {
            String desc = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());

            Document doc = new Document("description", desc).append("amount", amount);
            financeCollection.insertOne(doc);

            descriptionField.clear();
            amountField.clear();
            ucitajTroskove();
        } catch (NumberFormatException e) {
            System.out.println("Greška: Unesite ispravan broj!");
        }
    }

    private void ucitajTroskove() {
        expenseListView.getItems().clear();
        double total = 0;
        for (Document doc : financeCollection.find()) {
            double amount = doc.getDouble("amount");
            expenseListView.getItems().add(doc.getString("description") + ": " + amount + " KM");
            total += amount;
        }
        totalLabel.setText("Ukupno: " + String.format("%.2f", total) + " KM");
    }

    @FXML
    public void handleBack() {
        descriptionField.getScene().getWindow().hide();
    }
}