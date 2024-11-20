package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.planeTextFile.PlainTextFileHandler;
import com.example.miniproyecto_3_battlership.model.sound.Sounds;
import com.example.miniproyecto_3_battlership.view.GameSelectionStage;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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
    private int easterEgg;
    private int easterEgg2;

    private String nameCharacter;

    private PlainTextFileHandler plainTextFileHandler;

    private Sounds mainMusic;
    @FXML
    public void initialize() {
        mainMusic = new Sounds();
        mainMusic.loadSound("src/main/resources/com/example/miniproyecto_3_battlership/Sounds/mainmusic.wav");
        mainMusic.loopSound();

        plainTextFileHandler = new PlainTextFileHandler();
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

        choiceBox.getItems().addAll("Coronel Sander","Almirante Zemansky", "Mayor Lovelace", "Coronela Rosalind", "????", "???");
        selectionCharacter("Coronel Sander");

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectionCharacter(newValue);
        });


        // METODO QUE COLOCA LA CALAVERA
        btnHoverStyle(btnContinue, 1);
        btnHoverStyle(btnNewGame, 2);
        btnHoverStyle(btnOptions, 3);
        btnHoverStyle(btnCredits, 4);
        btnHoverStyle(btnQuitGame, 5);

        doesExist("game.ser");

        samuelVoice = new Sounds();
        samuelVoice.loadSound("src/main/resources/com/example/miniproyecto_3_battlership/Sounds/mivoice2.wav");




    }

    private void selectionCharacter(String newValue) {

        Image character1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/gameCharacter1.png")));
        Image character2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/gameCharacter2.png")));
        Image character3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/gameCharacter3.png")));
        Image character4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/gameCharacter4.png")));
        Image character5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/gameCharacter5.png")));
        Image character6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/gameCharacter6.png")));
        Image characterEasterEgg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/gameCharacter7.png")));
        Image characterEasterEgg2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/gameCharacter8.png")));

        if ( newValue == "Coronel Sander") {
            imgCharacter.setImage(character1);
            nameCharacter = "Coronel Sander";
        }else if (newValue == "Almirante Zemansky") {
            imgCharacter.setImage(character2);
            nameCharacter = "Almte. Zemansky";
        }else if (newValue == "Mayor Lovelace") {
            imgCharacter.setImage(character3);
            nameCharacter = "Mayor Lovelace";
        } else if (newValue == "Coronela Rosalind") {
            imgCharacter.setImage(character4);
            nameCharacter = "Coronela Rosalind";
        }else if (newValue == "????") {
            imgCharacter.setImage(character5);
            nameCharacter = "????";
        }
        else if (newValue == "???") {
            imgCharacter.setImage(character6);
            nameCharacter = "???";
        }else if(newValue == "Teniente Ampudia"){
            imgCharacter.setImage(characterEasterEgg);
            nameCharacter = "Teniente Ampudia";
        }else if(newValue == "Capitana Cordoba"){
            imgCharacter.setImage(characterEasterEgg2);
            nameCharacter = "Capitana Cordoba";
        }



    }

    @FXML
    public void onHandlePlayGame(javafx.event.ActionEvent actionEvent) throws IOException {
        mainMusic.stopSound();
        plainTextFileHandler.writeToFile("character.txt", nameCharacter + "," + " " + "," + "0");
        WelcomeStage.deleteInstance();
        GameSelectionStage.getInstance();

    }

    public void doesExist(String path){
        File file = new File(path);
        if(file.exists()){
            btnContinue.setDisable(false);
        }else{
            btnContinue.setDisable(true);
        }
    }

    @FXML
    public void onHandleCredits(ActionEvent event){
        easterEgg = 0;
        easterEgg2 = 0;
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
            easterEgg2 += 1;
            if(easterEgg2 == 5 && !choiceBox.getItems().contains("Capitana Cordoba")){
                choiceBox.getItems().add("Capitana Cordoba");
            }
        });

        juanLink.setOnAction(e -> {
            easterEgg += 1;
            if(easterEgg == 5 && !choiceBox.getItems().contains("Teniente Ampudia")){
                choiceBox.getItems().add("Teniente Ampudia");
            }

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
    void onHandleContinueGame(ActionEvent event) throws IOException {
        WelcomeStage.deleteInstance();
        GameStage.getInstance().getGameController().Continue();
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
