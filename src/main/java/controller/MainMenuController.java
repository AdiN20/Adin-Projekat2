package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    public void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
            database.DatabaseConnection.primijeniBoju(scene);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleOpenTodo() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/todo.fxml"));
            Stage stage = new Stage();
            stage.setTitle("To-Do Lista");
            Scene scene = new Scene(root, 400, 500);
            scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
            database.DatabaseConnection.primijeniBoju(scene);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleOpenFinance() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/finance.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Finansije");
            Scene scene = new Scene(root, 400, 500);
            scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
            database.DatabaseConnection.primijeniBoju(scene);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleOpenNotes() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/notes.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Bilješke");
            Scene scene = new Scene(root, 450, 500);
            scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
            database.DatabaseConnection.primijeniBoju(scene);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleOpenSettings() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/settings.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Postavke");
            Scene scene = new Scene(root, 300, 400);
            scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
            database.DatabaseConnection.primijeniBoju(scene);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleOpenUsers() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/users.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pregled Korisnika");
            Scene scene = new Scene(root, 350, 450);
            scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
            database.DatabaseConnection.primijeniBoju(scene);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleOpenCalendar() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/calendar.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Kalendar");
            Scene scene = new Scene(root, 450, 450);
            scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
            database.DatabaseConnection.primijeniBoju(scene);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}