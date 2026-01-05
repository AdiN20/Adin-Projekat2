package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Putanja do tvog FXML fajla (pazi da je folder view unutar resources)
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));

        // Postavljamo naslov i veličinu prozora
        primaryStage.setTitle("Life Management System - Login");
        primaryStage.setScene(new Scene(root, 400, 400));

        // Prikazujemo prozor
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Pokreće JavaFX aplikaciju
        launch(args);
    }
}