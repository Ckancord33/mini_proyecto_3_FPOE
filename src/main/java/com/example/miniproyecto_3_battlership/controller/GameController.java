package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.Player.PlayerBot;
import com.example.miniproyecto_3_battlership.model.Player.PlayerPerson;
import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.model.ships.*;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameController {

    private Game game;
    private PlayerBot playerBot;
    private PlayerPerson playerPerson;


    @FXML
    private GridPane gridPaneGame;

    @FXML
    private GridPane gridPaneShips;

    @FXML
    private BorderPane gameBorderPane;

    @FXML
    private AnchorPane anchorPaneMiddle;

    @FXML
    private AnchorPane anchorPaneLeft;

    @FXML
    private Label nameCharacter;

    @FXML
    private Label lbNameVillain;

    @FXML
    private ImageView imgCharacter;

    @FXML
    private ImageView imgVillain;

    @FXML
    private Label infoLabel;

    Fragata[] fragatas = new Fragata[4];
    Destructor[] destructores = new Destructor[3];
    Submarino[] submarinos = new Submarino[2];
    Portaaviones[] portaaviones = new Portaaviones[1];
    private ArrayList<ArrayList<Integer>> matriz;



    public void initialize() {

        Image backgroundImage = new Image(getClass().getResource("/com/example/miniproyecto_3_battlership/Image/background_game_battleship.png").toExternalForm());


        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );

        gameBorderPane.setBackground(new Background(background));

        createBorders();

        game = new Game();
        playerBot = game.getPlayerBot();
        playerBot.setMatrix();
        boolean error = true;
        do {
            try {
                playerBot.generateBotGame();
                error = false;
            } catch (IndexOutOfBoundsException e) {
                playerBot.clearMatrix();
                System.out.println("Error, intentando nuevamente");
            }
        } while (error);

        setCharacter();
        setEnemy();
    }

    private void setEnemy() {
        String[] villanos = {"Zarok", "Varek", "Drakk", "Korr", "Morth", "Tharn", "Vulkar", "Grim", "Raek", "Durn"};
        lbNameVillain.setText(villanos[(int)(Math.random()*9)]);
        int villain =(int)(Math.random() * 2);
        if (villain == 0){
            imgVillain.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character5.png"))));
        }else{
            imgVillain.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character6.png"))));
        }

    }

    public void setCharacter(){
        String nameCharacterActual = WelcomeController.getNameCharacter();
        Image imageCharacterActual;
        nameCharacter.setText(nameCharacterActual);
        if (nameCharacterActual == "Coronel Sander"){
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character1.png")));
            imgCharacter.setImage(imageCharacterActual);
        }else if(nameCharacterActual == "Almte. Zemansky"){
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character2.png")));
            imgCharacter.setImage(imageCharacterActual);
        }else if (nameCharacterActual == "Mayor Lovelace"){
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character3.png")));
            imgCharacter.setImage(imageCharacterActual);
        }else if(nameCharacterActual == "Coronela Rosalind"){
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character4.png")));
            imgCharacter.setImage(imageCharacterActual);
        }else if (nameCharacterActual == "????"){
            nameCharacter.setAlignment(Pos.CENTER);
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character5.png")));
            imgCharacter.setImage(imageCharacterActual);
        }else if(nameCharacterActual == "???"){
            nameCharacter.setAlignment(Pos.CENTER);
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character6.png")));
            imgCharacter.setImage(imageCharacterActual);
        }else if (nameCharacterActual == "Teniente Ampudia"){
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character7.png")));
            imgCharacter.setImage(imageCharacterActual);
        }else if (nameCharacterActual == "Capitana Cordoba"){
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character8.png")));
            imgCharacter.setImage(imageCharacterActual);
        }

    }


    @FXML
    void onHandleMousePressedGameEnemy(MouseEvent event) {
        double cellWidth = gridPaneGame.getWidth() / gridPaneGame.getColumnCount();
        double cellHeight = gridPaneGame.getHeight() / gridPaneGame.getRowCount();

        double x = event.getX();
        double y = event.getY();

        int column = (int) (x / cellWidth);
        int row = (int) (y / cellHeight);

        matriz = playerBot.getMatriz();
        if(row != 0 && column != 0) {
            if (matriz.get(row-1).get(column-1) != 0) {
                System.out.println("PUM LE ATINASTE");
                Circle circle = new Circle(0,0,30, Color.DARKGRAY);
                gridPaneGame.add(circle, column, row);
                playerTurn();
                infoLabel.setText("La maquina esta pensando...");
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event2 -> {
                    botAttack( 1+ (int)(Math.random()*9),  1+ (int)(Math.random()*9));
                });
                pause.play();

            } else{
                System.out.println("NO LE ATINASTE");
                Group group = new Group();
                Line line1 = new Line(9, 9, 29, 29);
                Line line2 = new Line(29, 9, 9, 29);
                line1.setStroke(Color.RED);
                line1.setStrokeWidth(5);
                line2.setStroke(Color.RED);
                line2.setStrokeWidth(5);
                group.getChildren().addAll(line1, line2);
                gridPaneGame.add(group, column, row);
                playerTurn();
                infoLabel.setText("La maquina esta pensando...");
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event2 -> {
                    botAttack( 1+ (int)(Math.random()*9),  1+ (int)(Math.random()*9));
                });
                pause.play();
            }
        }


    }

    @FXML
    void botAttack(int row, int column) {

        matriz = playerBot.getMatriz();
        if(row != 0 && column != 0) {
            if (matriz.get(row-1).get(column-1) != 0) {
                System.out.println("PUM LE ATINASTE");
                Circle circle = new Circle(0,0,20, Color.DARKGRAY);
                gridPaneShips.add(circle, column, row);
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    playerTurn();
                });
                pause.play();

            } else{
                System.out.println("NO LE ATINASTE");
                Group group = new Group();
                Line line1 = new Line(9, 9, 29, 29);
                Line line2 = new Line(29, 9, 9, 29);
                line1.setStroke(Color.RED);
                line1.setStrokeWidth(5);
                line2.setStroke(Color.RED);
                line2.setStrokeWidth(5);
                group.getChildren().addAll(line1, line2);
                gridPaneShips.add(group, column, row);
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    playerTurn();
                });
                pause.play();
            }
        }
    }

    private void playerTurn() {
        if (gridPaneGame.isDisable()){
            gridPaneGame.setDisable(false);
        }else{
            gridPaneGame.setDisable(true);
        }
    }

    public void setGridPaneShips(GridPane gridPaneShipsGame, ArrayList <Ship> ships){
        setShips(ships);
        this.gridPaneShips = gridPaneShipsGame;
        animationIn();
        for(Node node : gridPaneShips.getChildren()){
            node.setOnMouseClicked(null);
            node.setOnMouseEntered(null);
            node.setOnMouseExited(null);
        }

        gridPaneShips.setStyle("-fx-cursor: default;");
        gridPaneShips.setLayoutX(-102);
        gridPaneShips.setLayoutY(199);
        anchorPaneLeft.getChildren().add(1,gridPaneShips);
    }

    public void animationIn(){

        anchorPaneLeft.setTranslateX(-400);

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        anchorPaneMiddle.setTranslateY(screenWidth);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.9), anchorPaneLeft);
        fadeIn.setFromValue(0.5);
        fadeIn.setToValue(1.0);

        TranslateTransition moveLeft = new TranslateTransition(Duration.seconds(2), anchorPaneLeft);
        moveLeft.setToX(0);

        ParallelTransition newStageTransition = new ParallelTransition(fadeIn, moveLeft);
        newStageTransition.play();

        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(2.5), anchorPaneMiddle);
        fadeIn2.setFromValue(0.2);
        fadeIn2.setToValue(1.0);

        TranslateTransition moveUp = new TranslateTransition(Duration.seconds(3), anchorPaneMiddle);
        moveUp.setFromY(screenWidth);
        moveUp.setToY(0);

        ParallelTransition newStageTransition2 = new ParallelTransition(fadeIn2, moveUp);
        newStageTransition2.play();

        moveLeft.setInterpolator(Interpolator.EASE_BOTH);
        moveUp.setInterpolator(Interpolator.EASE_BOTH);

    }

    public void setShips(ArrayList <Ship> ships ) {
        int[] nShips = {0,0,0,0};
        for (Ship ship : ships) {
            if (ship instanceof Fragata) {
                fragatas[nShips[0]] = (Fragata) ship;
                fragatas[nShips[0]].originDesing();

                nShips[0]++;
            }
            if (ship instanceof Destructor) {
                destructores[nShips[1]] = (Destructor) ship;
                destructores[nShips[1]].originDesing();

                nShips[1]++;
            }
            if (ship instanceof Submarino) {
                submarinos[nShips[2]] = (Submarino) ship;
                submarinos[nShips[2]].originDesing();

                nShips[2]++;
            }
            if (ship instanceof Portaaviones) {
                portaaviones[nShips[3]] = (Portaaviones) ship;
                portaaviones[nShips[3]].originDesing();

                nShips[3]++;
            }

        }
    }

    public void createBorders() {
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneGame.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto_3_battlership/Css/css.css")).toExternalForm());

        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.getStyleClass().add("cell");
                gridPaneGame.add(cell, col, rows);
            }
        }
    }


        @FXML
    public void onHandleReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }



}
