package com.example.miniproyecto_3_battlership;

import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        WelcomeStage.getInstance();
    }

    public static void main(String[] args) {
        launch();
    }
}