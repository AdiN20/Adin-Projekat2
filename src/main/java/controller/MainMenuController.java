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
            stage.setScene(new Scene(root, 400, 400));
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
            stage.setScene(new Scene(root, 400, 500));
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
            stage.setScene(new Scene(root, 400, 500));
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
            stage.setScene(new Scene(root, 450, 500));
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
            stage.setScene(new Scene(root, 300, 400));
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
            stage.setScene(new Scene(root, 350, 450));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}