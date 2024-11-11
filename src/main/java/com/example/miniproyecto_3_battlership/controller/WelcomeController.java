package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.model.sound.Sounds;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class WelcomeController {
    @FXML
    private Button btnContinue;

    @FXML
    private ImageView img1;

    @FXML
    private Button btnCredits;

    @FXML
    private ImageView img4;

    @FXML
    private Button btnNewGame;

    @FXML
    private ImageView img2;

    @FXML
    private Button btnOptions;

    @FXML
    private ImageView img3;


    @FXML
    private Button btnQuitGame;

    @FXML
    private ImageView img5;

    @FXML
    private BorderPane welcomeBorderPane;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private ImageView imgCharacter;

    private Sounds samuelVoice;

    @FXML
    public void initialize() {

        Image character1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/chapter1.png")));
        Image character2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/chapter2.png")));
        Image character3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/chapter3.png")));
        Image character4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/chapter4.png")));
        Image character5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/chapter5.png")));
        Image character6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/chapter6.png")));


        //IMAGEN DE FONDO
        Image backgroundImage = new Image(getClass().getResource("/com/example/miniproyecto_3_battlership/Image/background_game.png").toExternalForm());


        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );

        welcomeBorderPane.setBackground(new Background(background));

        choiceBox.getItems().addAll("Coronel sander","Coronel chapalin", "Coronela Flora", "Coronela Patricia", "????", "???");

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if ( newValue == "Coronel sander") {
                imgCharacter.setImage(character1);
                System.out.println("entre1");
            }else if (newValue == "Coronel chapalin") {
                imgCharacter.setImage(character2);
                System.out.println("entre2");
            }else if (newValue == "Coronela Flora") {
                imgCharacter.setImage(character3);
            } else if (newValue == "Coronela Patricia") {
                imgCharacter.setImage(character4);
            }else if (newValue == "????") {
                imgCharacter.setImage(character5);
            }
            else if (newValue == "???") {
                imgCharacter.setImage(character6);
            }
        });


        // METODO QUE COLOCA LA CALAVERA
        btnHoverStyle(btnContinue, 1);
        btnHoverStyle(btnNewGame, 2);
        btnHoverStyle(btnOptions, 3);
        btnHoverStyle(btnCredits, 4);
        btnHoverStyle(btnQuitGame, 5);

        samuelVoice = new Sounds();
        samuelVoice.loadSound("src/main/resources/com/example/miniproyecto_3_battlership/Sounds/mivoice2.wav");


    }
    @FXML
    public void onHandlePlayGame(javafx.event.ActionEvent actionEvent) throws IOException {
        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }

    @FXML
    public void onHandleCredits(ActionEvent event){
        System.out.println("alerta manito");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Credits");
        alert.setHeaderText(null);

        alert.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/skulk_pixel_2.png")))));

        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto_3_battlership/Css/alert-styless.css")).toExternalForm());

        Label contentText = new Label("Developed by:");

        Hyperlink samuelLink = new Hyperlink("- Samuel Arenas");
        Hyperlink nicolasLink = new Hyperlink("- Nicolas Cordoba");
        Hyperlink juanLink = new Hyperlink("- Juan Manuel Ampudia");

        samuelLink.setOnAction(e -> {
            System.out.println("Acción para Samuel");
            samuelVoice.playSound();
            alert.setContentText("Samuel Arenas.");
        });

        nicolasLink.setOnAction(e -> {
            System.out.println("Acción para Nicolas");
            alert.setContentText("Nicolas Cordoba.");
        });

        juanLink.setOnAction(e -> {
            System.out.println("Acción para Juan Manuel");
            alert.setContentText("Juan Manuel Ampudia.");
        });

        VBox contentBox = new VBox(1);
        contentBox.getChildren().addAll(contentText,samuelLink, nicolasLink, juanLink);
        contentBox.getChildren().forEach(child -> child.getStyleClass().add("vbox-child"));



        alert.getDialogPane().setContent(contentBox);
        Stage stageCredits = (Stage) alert.getDialogPane().getScene().getWindow();
        stageCredits.initStyle(StageStyle.UNDECORATED);
        alert.setWidth(1000);

        alert.showAndWait();

    }

    @FXML
    public void onHandleQuitGame(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnHoverStyle(Button button, int i) {
        button.setOnMouseEntered(mouseEvent -> {
            switch (i) {
                case 1:
                    img1.setOpacity(1);
                    break;
                case 2:
                    img2.setOpacity(1);
                    break;
                case 3:
                    img3.setOpacity(1);
                    break;
                case 4:
                    img4.setOpacity(1);
                    break;
                case 5:
                    img5.setOpacity(1);
                    break;
            }
        });
        button.setOnMouseExited(mouseEvent -> {
            switch (i) {
                case 1:
                    img1.setOpacity(0);
                    break;
                case 2:
                    img2.setOpacity(0);
                    break;
                case 3:
                    img3.setOpacity(0);
                    break;
                case 4:
                    img4.setOpacity(0);
                    break;
                case 5:
                    img5.setOpacity(0);
                    break;
            }
        });
    }

}
