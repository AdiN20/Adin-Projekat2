package controller;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.client.MongoCollection;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;

public class FinanceController {
    @FXML private TextField descriptionField;
    @FXML private TextField amountField;
    @FXML private ChoiceBox<String> typeBox;
    @FXML private ListView<String> expenseListView;
    @FXML private Label totalLabel;
    @FXML private Label statsLabel;

    private MongoCollection<org.bson.Document> financeCollection;

    @FXML
    public void initialize() {
        financeCollection = DatabaseConnection.getDatabase().getCollection("finances");
        typeBox.getItems().addAll("Trošak", "Prihod");
        typeBox.setValue("Trošak");
        ucitajTroskove();
        azurirajStats();
    }

    private void azurirajStats() {
        long brojUnosa = financeCollection.countDocuments();
        statsLabel.setText("Broj transakcija: " + brojUnosa);
    }

    @FXML
    public void handleAddExpense() {
        try {
            String desc = descriptionField.getText();
            String amountRaw = amountField.getText();
            String type = typeBox.getValue();

            if (!desc.isEmpty() && !amountRaw.isEmpty()) {
                double amount = Double.parseDouble(amountRaw);
                org.bson.Document doc = new org.bson.Document("description", desc)
                        .append("amount", amount)
                        .append("type", type);
                financeCollection.insertOne(doc);

                descriptionField.clear();
                amountField.clear();
                ucitajTroskove();
                azurirajStats();
            }
        } catch (NumberFormatException e) {
            System.out.println("Greška: Unesite ispravan broj!");
        }
    }

    @FXML
    public void handleExportPDF() {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Finansijski_Izvjestaj.pdf"));
            document.open();
            document.add(new Paragraph("FINANSIJSKI IZVJESTAJ"));
            document.add(new Paragraph("--------------------------------------------------"));
            document.add(new Paragraph(" "));

            for (org.bson.Document doc : financeCollection.find()) {
                String tip = doc.getString("type");
                String opis = doc.getString("description");
                Double iznos = doc.getDouble("amount");
                document.add(new Paragraph(tip + ": " + opis + " | " + iznos + " KM"));
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("--------------------------------------------------"));
            document.add(new Paragraph(totalLabel.getText()));
            document.add(new Paragraph(statsLabel.getText()));

            System.out.println("PDF za finansije uspjesno kreiran!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void ucitajTroskove() {
        expenseListView.getItems().clear();
        double total = 0;
        for (org.bson.Document doc : financeCollection.find()) {
            double amount = doc.getDouble("amount");
            String type = doc.getString("type");

            if ("Prihod".equals(type)) {
                total += amount;
                expenseListView.getItems().add("[+] " + doc.getString("description") + ": " + amount + " KM");
            } else {
                total -= amount;
                expenseListView.getItems().add("[-] " + doc.getString("description") + ": " + amount + " KM");
            }
        }
        totalLabel.setText("Saldo: " + String.format("%.2f", total) + " KM");
    }

    @FXML
    public void handleBack() {
        descriptionField.getScene().getWindow().hide();
    }
}