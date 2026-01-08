package controller;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.client.MongoCollection;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.FileOutputStream;

public class TodoController {

    @FXML private TextArea noteArea;
    @FXML private ListView<String> notesListView;
    @FXML private Label statsLabel;

    private MongoCollection<org.bson.Document> todoCollection;

    @FXML
    public void initialize() {
        todoCollection = DatabaseConnection.getDatabase().getCollection("tasks");
        ucitajZadatke();
        azurirajStats();
    }

    private void azurirajStats() {
        long broj = todoCollection.countDocuments();
        if (statsLabel != null) {
            statsLabel.setText("Ukupno zadataka: " + broj);
        }
    }

    @FXML
    public void handleAddNote() {
        String taskText = noteArea.getText();
        if (taskText != null && !taskText.isEmpty()) {
            org.bson.Document doc = new org.bson.Document("task", taskText).append("status", "pending");
            todoCollection.insertOne(doc);
            noteArea.clear();
            ucitajZadatke();
            azurirajStats();
        }
    }

    @FXML
    public void handleExportPDF() {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Lista_Zadataka.pdf"));
            document.open();
            document.add(new Paragraph("MOJA TO-DO LISTA"));
            document.add(new Paragraph("--------------------------------------------------"));
            document.add(new Paragraph(" "));

            int i = 1;
            for (org.bson.Document doc : todoCollection.find()) {
                String zadatak = doc.getString("task");
                document.add(new Paragraph(i + ". " + zadatak));
                i++;
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("--------------------------------------------------"));
            document.add(new Paragraph(statsLabel.getText()));

            System.out.println("PDF uspjesno kreiran: Lista_Zadataka.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void ucitajZadatke() {
        notesListView.getItems().clear();
        for (org.bson.Document doc : todoCollection.find()) {
            notesListView.getItems().add(doc.getString("task"));
        }
    }

    @FXML
    public void handleBack() {
        noteArea.getScene().getWindow().hide();
    }
}