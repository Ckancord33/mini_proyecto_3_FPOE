package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.Player.PlayerBot;
import com.example.miniproyecto_3_battlership.model.Player.PlayerPerson;
import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.model.serializable.Save;
import com.example.miniproyecto_3_battlership.model.serializable.SerializableFileHandler;
import com.example.miniproyecto_3_battlership.model.ships.*;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class GameController implements Serializable {

    private Game game;
    private PlayerBot playerBot;
    private PlayerPerson playerPerson;
    private SerializableFileHandler serializableFileHandler = new SerializableFileHandler();


    @FXML
    private GridPane gridPaneGame;

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
    private ArrayList<Ship> ship = new ArrayList<>();
    private Rectangle[][] enemyShadow = new Rectangle[10][10];
    private Save save;



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

        createEnemuShadows();

    }

    public void setGridPaneShips(ArrayList <int[]> shipsPositions, int[][] shipsSelected){
        animationIn();

        game = new Game();
        playerBot = game.getPlayerBot();
        playerPerson = game.getPlayerPerson();
        playerPerson.setMatrix();
        playerBot.setMatrix();
        playerPerson.setChosenMatrix(shipsSelected);
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

        save = new Save(shipsPositions, game, shipsSelected);
        ship = save.getShip();
        createGridPaneGame();

        for(Node node : gridPaneShips.getChildren()){
            node.setOnMouseClicked(null);
            node.setOnMouseEntered(null);
            node.setOnMouseExited(null);
        }
        gridPaneShips.setStyle("-fx-cursor: default;");
        gridPaneShips.setLayoutX(-102);
        gridPaneShips.setLayoutY(199);
        anchorPaneLeft.getChildren().add(1,gridPaneShips);

        serializableFileHandler.serialize("save.ser", save);
    }

    public void Continue(){
        save = (Save) serializableFileHandler.deserialize("save.ser");
        game = save.getGame();
        playerBot = game.getPlayerBot();
        ship = save.getShip();
        setCharacter();
        setEnemy();
        createGridPaneGame();
        gridPaneShips.setStyle("-fx-cursor: default;");
        gridPaneShips.setLayoutX(-102);
        gridPaneShips.setLayoutY(199);
        anchorPaneLeft.getChildren().add(1,gridPaneShips);

    }

    public void createGridPaneGame(){
        gridPaneShips = new GridPane();
        gridPaneShips.setPrefSize(700.0, 700.0);
        gridPaneShips.setCursor(Cursor.HAND);
        for (int i = 0; i < 11; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHalignment(javafx.geometry.HPos.CENTER);
            column.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
            column.setMinWidth(10.0);
            column.setPrefWidth(100.0);
            gridPaneShips.getColumnConstraints().add(column);
        }

        // Add row constraints
        for (int i = 0; i < 11; i++) {
            RowConstraints row = new RowConstraints();
            row.setValignment(javafx.geometry.VPos.CENTER);
            row.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
            row.setMinHeight(10.0);
            row.setPrefHeight(30.0);
            gridPaneShips.getRowConstraints().add(row);
        }

        // Add column labels (A-J)
        String[] columnLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int i = 0; i < columnLabels.length; i++) {
            Label label = new Label(columnLabels[i]);
            label.setTextFill(javafx.scene.paint.Color.web("#f2f2f2"));
            label.setFont(new javafx.scene.text.Font("HotelDeParisXe", 30.0));
            GridPane.setColumnIndex(label, i + 1);
            gridPaneShips.getChildren().add(label);
        }

        // Add row labels (1-10)
        for (int i = 1; i <= 10; i++) {
            Label label = new Label(String.valueOf(i));
            label.setTextFill(javafx.scene.paint.Color.web("#f5f5f5"));
            label.setFont(new Font("HotelDeParisXe", 30.0));
            GridPane.setRowIndex(label, i);
            gridPaneShips.getChildren().add(label);
        }
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneShips.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto_3_battlership/Css/css.css")).toExternalForm());
        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.getStyleClass().add("cell");
                gridPaneShips.add(cell, col, rows);
            }
        }

        for(int i = 0; i < ship.size(); i++){
            Ship shipSelected = this.ship.get(i);
            int row = shipSelected.getPosition()[0] + 1;
            int col = shipSelected.getPosition()[1] + 1;
            try {
                if (shipSelected.isHorizontal()) {
                    gridPaneShips.add(shipSelected, col - shipSelected.getSize() + 1, row);
                    GridPane.setRowSpan(shipSelected, 0);
                    GridPane.setColumnSpan(shipSelected, shipSelected.getSize());
                } else {
                    gridPaneShips.add(shipSelected, col, row - shipSelected.getSize() + 1);
                    GridPane.setColumnSpan(shipSelected, 0);
                    GridPane.setRowSpan(shipSelected, shipSelected.getSize());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

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
    void botAttack(int row, int column) {

        matriz = playerPerson.getMatrix();
        if(row != 0 && column != 0) {
            if (matriz.get(row-1).get(column-1) != 0)  {
                System.out.println("PUM LE ATINASTE");
                Circle circle = new Circle(0,0,20, Color.RED);
                gridPaneShips.add(circle, column, row);
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    playerTurn();
                });
                playerPerson.changeMatrix(row-1, column-1, -1);
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
                playerPerson.changeMatrix(row-1, column-1, 2);
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

    }

    public void createEnemuShadows() {
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneGame.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto_3_battlership/Css/css.css")).toExternalForm());
        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.getStyleClass().add("cell");
                enemyShadow[rows - 1][col - 1] = cell;
                int finalRows = rows - 1;
                int finalCol = col - 1;
                enemyShadow[rows - 1][col - 1].setOnMouseEntered(e -> onHandleMouseEnteredShips(finalRows, finalCol));
                enemyShadow[rows - 1][col - 1].setOnMouseExited(e -> onHandleMouseExitedShips(finalRows, finalCol));
                enemyShadow[rows - 1][col - 1].setOnMouseClicked(e -> onHandleMouseClickedShips(finalRows, finalCol));
                gridPaneGame.add(enemyShadow[rows - 1][col - 1], col, rows);
            }
        }
    }

    public void onHandleMouseEnteredShips(int row, int col) {
        Color colorhover = Color.rgb(0, 0, 0, 0.5);
        enemyShadow[row][col].setFill(colorhover);
    }

    public void onHandleMouseClickedShips(int row, int column) {
        row += 1;
        column += 1;

        matriz = playerBot.getMatrix();
        if(row != 0 && column != 0) {
            if (matriz.get(row-1).get(column-1) != 0) {
                System.out.println("PUM LE ATINASTE");
                Circle circle = new Circle(0,0,30, Color.DARKGRAY);
                gridPaneGame.add(circle, column, row);
                playerTurn();
                infoLabel.setText("La maquina esta pensando...");
                playerBot.changeMatrix(row -1, column -1, -1);
                enemyShadow[row-1][column-1].setOnMouseClicked(null);
                enemyShadow[row-1][column-1].setStyle("-fx-cursor: default;");
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

    public void onHandleMouseExitedShips(int row, int col) {
        Color colorDefault = Color.TRANSPARENT;
        enemyShadow[row][col].setFill(colorDefault);
    }


        @FXML
    public void onHandleReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }



}
