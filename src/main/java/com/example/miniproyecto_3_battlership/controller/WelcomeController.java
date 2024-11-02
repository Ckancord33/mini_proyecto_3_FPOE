package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;

public class WelcomeController {
    @FXML
    private Button btnContinue;

    @FXML
    private Button btnCredits;

    @FXML
    private Label lbNewGameHover;

    @FXML
    private Label lbNewGame;

    @FXML
    private Button btnOptions;

    @FXML
    private Button btnQuitGame;

    @FXML
    private BorderPane welcomeBorderPane;
    @FXML
    public void initialize() {
        Image backgroundImage = new Image(getClass().getResource("/com/example/miniproyecto_3_battlership/Image/background_game.png").toExternalForm());

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );

        welcomeBorderPane.setBackground(new Background(background));

        applyHoverEffect(btnContinue);
        applyHoverEffect(btnCredits);
        applyHoverEffect(btnOptions);
        applyHoverEffect2(lbNewGame);
        applyHoverEffect(btnQuitGame);

    }
    @FXML
    public void onHandlePlayGame(javafx.event.ActionEvent actionEvent) throws IOException {
        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }

    public void onHandleQuitGame(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }


    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: transparent; -fx-stroke: #7D3434;"); // Cambia el color de fondo
        });

        button.setOnMouseExited(event -> {

            button.setStyle("-fx-background-color: transparent;"); // Vuelve al estilo original

        });
    }

    private void applyHoverEffect2(Label label) {
        label.setOnMouseEntered(event -> {

            lbNewGameHover.setTextFill(Color.web("#7D3434"));
        });

        label.setOnMouseExited(event -> {

            lbNewGameHover.setTextFill(Color.TRANSPARENT);

        });
    }

    public void onHandlePlayNewGame(MouseEvent mouseEvent) throws IOException {
        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }
}
