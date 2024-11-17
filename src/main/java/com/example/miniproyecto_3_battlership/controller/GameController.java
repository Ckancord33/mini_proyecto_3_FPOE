package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.Player.PlayerBot;
import com.example.miniproyecto_3_battlership.model.Player.PlayerPerson;
import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.model.ships.*;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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

    Fragata[] fragatas = new Fragata[4];
    Destructor[] destructores = new Destructor[3];
    Submarino[] submarinos = new Submarino[2];
    Portaaviones[] portaaviones = new Portaaviones[1];
    private ArrayList<ArrayList<Integer>> matriz;



    public void initialize() {

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        anchorPaneLeft.setTranslateX(screenWidth);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(.5), anchorPaneLeft);
        fadeIn.setFromValue(0.5);
        fadeIn.setToValue(1.0);

        TranslateTransition moveLeft = new TranslateTransition(Duration.seconds(1.5), anchorPaneLeft);
        moveLeft.setFromX(screenWidth);
        moveLeft.setToX(0);

        ParallelTransition newStageTransition = new ParallelTransition(fadeIn, moveLeft);
        newStageTransition.play();


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

        System.out.println(cellWidth + " " + cellHeight);

        double x = event.getX();
        double y = event.getY();

        int column = (int) (x / cellWidth);
        int row = (int) (y / cellHeight);

        matriz = playerBot.getMatriz();
        if(row != 0 && column != 0) {
            if (matriz.get(row-1).get(column-1) == 1) {
                System.out.println("PUM LE ATINASTE");
                Circle circle = new Circle(2,2,4, Color.BLUE);
                gridPaneGame.add(circle, column, row);
            }else if(matriz.get(row-1).get(column-1) == 2){
                System.out.println("PUM LE ATINASTE");
                Circle circle = new Circle(2,2,4, Color.RED);
                gridPaneGame.add(circle, column, row);
            }else if(matriz.get(row-1).get(column-1) == 3){
                System.out.println("PUM LE ATINASTE");
                Circle circle = new Circle(2,2,4, Color.GREEN);
                gridPaneGame.add(circle, column, row);
            }else if(matriz.get(row-1).get(column-1) == 4){
                System.out.println("PUM LE ATINASTE");
                Circle circle = new Circle(2,2,4, Color.PURPLE);
                gridPaneGame.add(circle, column, row);
            }else{
                System.out.println("NO LE ATINASTE");
                AnchorPane pane = new AnchorPane();
                Line line1 = new Line(9, 9, 29, 29);
                Line line2 = new Line(29, 9, 9, 29);


                line1.setStroke(Color.RED);
                line1.setStrokeWidth(5);
                line2.setStroke(Color.RED);
                line2.setStrokeWidth(5);

                pane.getChildren().addAll(line1, line2);

                gridPaneGame.add(pane, column, row);
            }
        }

    }

    public void setGridPaneShips(GridPane gridPaneShips, ArrayList <Ship> ships){
        setShips(ships);
        for(Node node : gridPaneShips.getChildren()){
            node.setOnMouseClicked(null);
            node.setOnMouseEntered(null);
            node.setOnMouseExited(null);
        }
        gridPaneShips.setStyle("-fx-cursor: default;");
        gridPaneShips.setLayoutX(131);
        gridPaneShips.setLayoutY(199);
        anchorPaneMiddle.getChildren().add(gridPaneShips);


    }

    public void setShips(ArrayList <Ship> ships) {
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
        double cellWidth = 38.18;
        double cellHeight = 38.18;
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

    // HERE WILL BE THE WHOLE GAME
    public void gameTurn(){
        do{
            System.out.println("hola");
        }while(game.verifyWinner());
    }


    @FXML
    public void onHandleReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }



}
