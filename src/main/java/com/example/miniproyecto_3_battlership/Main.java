package com.example.miniproyecto_3_battlership;

import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main entry point of the Battleship game application.
 * This class initializes the application and launches the initial stage.
 *
 * <p>The application uses the Singleton design pattern to manage stages.</p>
 *
 * <p>Authors: Nicolás Córdoba, Samuel Arenas, Juan Manuel Ampudia</p>
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by initializing the WelcomeStage.
     *
     * @param stage the primary stage provided by JavaFX (not used directly in this method).
     * @throws IOException if an error occurs while loading the initial resources for the stage.
     */
    @Override
    public void start(Stage stage) throws IOException {
        WelcomeStage.getInstance();
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args the command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        launch();
    }
}