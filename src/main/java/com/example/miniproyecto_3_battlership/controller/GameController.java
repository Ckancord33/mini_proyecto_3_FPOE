package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.Player.PlayerBot;
import com.example.miniproyecto_3_battlership.model.Player.PlayerPerson;
import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.model.planeTextFile.PlainTextFileHandler;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class GameController implements Serializable {

    private Game game;
    private PlayerBot playerBot;
    private PlayerPerson playerPerson;
    private SerializableFileHandler serializableFileHandler = new SerializableFileHandler();


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

    private ArrayList<ArrayList<Integer>> matriz;
    private ArrayList<Ship> ship = new ArrayList<>();
    private Rectangle[][] enemyShadow = new Rectangle[10][10];
    private Save save;

    private Image image;
    private ImagePattern imagePatter;



    public void initialize() {

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/mira.png")));
        imagePatter  = new ImagePattern(image);

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

        save = new Save(shipsPositions);
        ship = save.getShip();
        createGridPaneGame();
        
        gridPaneShips.setStyle("-fx-cursor: default;");

        serializableFileHandler.serialize("save.ser", save);
        serializableFileHandler.serialize("game.ser", game);
        
        playerPerson.showMatrix();
        System.out.println();
        playerBot.showMatrix();
    }

    public void Continue(){
        save = (Save) serializableFileHandler.deserialize("save.ser");
        game = (Game) serializableFileHandler.deserialize("game.ser");
        playerBot = game.getPlayerBot();
        playerPerson = game.getPlayerPerson();
        ship = save.getShip();
        setCharacter();
        setEnemy();
        createGridPaneGame();
        gridPaneShips.setStyle("-fx-cursor: default;");
        loadGridPaneShips();
        loadGridPaneGame();

    }

    public void createGridPaneGame(){
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
        PlainTextFileHandler plainTextFileHandler = new PlainTextFileHandler();
        String[] data = plainTextFileHandler.readFromFile("character.txt");
        String nameCharacterActual = data[0];
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

    public void onHandleMouseClickedShips(int row, int column) {
        row += 1;
        column += 1;

        matriz = playerBot.getMatrix();
        if(row != 0 && column != 0) {
            enemyShadow[row-1][column-1].setOnMouseClicked(null);
            enemyShadow[row-1][column-1].setOnMouseEntered(null);
            enemyShadow[row-1][column-1].setOnMouseExited(null);
            enemyShadow[row-1][column-1].setStyle("-fx-cursor: default;");
            if (matriz.get(row-1).get(column-1) != 0) {
                System.out.println("PUM LE ATINASTE");
                gridPaneGame.add(successSymbol(), column, row);
                playerTurn();
                infoLabel.setText("La maquina esta pensando...");
                playerBot.changeMatrix(row -1, column -1, -1);
                PauseTransition pause = new PauseTransition(Duration.seconds(0));
                pause.setOnFinished(event2 -> {
                    botAttack( 1+ (int)(Math.random()*9),  1+ (int)(Math.random()*9));
                });
                pause.play();

            } else{
                System.out.println("NO LE ATINASTE");
                gridPaneGame.add(errorSymbol(), column, row);
                playerTurn();
                infoLabel.setText("La maquina esta pensando...");
                playerBot.changeMatrix(row -1, column -1, 2);
                PauseTransition pause = new PauseTransition(Duration.seconds(0));
                pause.setOnFinished(event2 -> {
                    botAttack( 1+ (int)(Math.random()*9),  1+ (int)(Math.random()*9));
                });
                pause.play();
            }
        }
        serializableFileHandler.serialize("game.ser", game);
        victory(game.verifyWinner(playerBot));

    }

    public void loadGridPaneShips(){
        matriz = playerPerson.getMatrix();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(matriz.get(i).get(j) == 2){
                    gridPaneShips.add(errorSymbol(), j+1, i+1);
                }else if(matriz.get(i).get(j) == -1){
                    Circle circle = new Circle(0,0,20, Color.RED);
                    gridPaneShips.add(circle, j+1, i+1);
                }
            }
        }
    }

    public void loadGridPaneGame(){
        matriz = playerBot.getMatrix();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(matriz.get(i).get(j) == 2){
                    gridPaneGame.add(errorSymbol(), j+1, i+1);
                    enemyShadow[i][j].setOnMouseClicked(null);
                    enemyShadow[i][j].setOnMouseEntered(null);
                    enemyShadow[i][j].setOnMouseExited(null);
                }else if(matriz.get(i).get(j) == -1){
                    Circle circle = new Circle(0,0,20, Color.RED);
                    gridPaneGame.add(circle, j+1, i+1);
                    enemyShadow[i][j].setOnMouseClicked(null);
                    enemyShadow[i][j].setOnMouseEntered(null);
                    enemyShadow[i][j].setOnMouseExited(null);
                }
            }
        }
    }

    public void playerTurn() {
        if (gridPaneGame.isDisable()){
            gridPaneGame.setDisable(false);
        }else{
            gridPaneGame.setDisable(true);
        }
    }

    @FXML

    void botAttack(int row, int column) {

        matriz = playerPerson.getMatrix();
        if(row != 0 && column != 0) {
            if (matriz.get(row-1).get(column-1) != 0 && matriz.get(row-1).get(column-1) != 2 && matriz.get(row-1).get(column-1) != -1) {
                System.out.println("PUM LE ATINASTE");
                gridPaneShips.add(successSymbol(), column, row);
                PauseTransition pause = new PauseTransition(Duration.seconds(0));
                pause.setOnFinished(event -> {
                    playerTurn();
                });
                playerPerson.changeMatrix(row-1, column-1, -1);
                pause.play();
                defeat((game.verifyWinner(playerPerson)));

            } else{
                System.out.println("NO LE ATINASTE");
                gridPaneShips.add(errorSymbol(), column, row);
                playerPerson.changeMatrix(row-1, column-1, 2);
                PauseTransition pause = new PauseTransition(Duration.seconds(0));
                pause.setOnFinished(event -> {
                    playerTurn();
                });
                pause.play();
            }
        }
    }

    public Group errorSymbol(){
        Group group = new Group();
        Line line1 = new Line(9, 9, 29, 29);
        Line line2 = new Line(29, 9, 9, 29);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(5);
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(5);
        group.getChildren().addAll(line1, line2);
        return group;
    }

    public Group successSymbol(){
        Group group = new Group();
        Circle circle = new Circle(0,0,20, Color.RED);
        group.getChildren().add(circle);
        return group;
    }

    public void victory(boolean victory){
        if (victory){
            infoLabel.setText("¡Felicidades! Has ganado");
            gridPaneGame.setDisable(true);
            Path path = Paths.get("game.ser");
            Path path2 = Paths.get("save.ser");

            try {
                Files.delete(path);
                Files.delete(path2);
                System.out.println("El archivo ha sido borrado.");
            } catch (IOException e) {
                System.err.println("Error al borrar el archivo: " + e.getMessage());
            }

        }

    }

    public void defeat(boolean defeat){
        if (defeat){
            infoLabel.setText("¡Has perdido! Intentalo de nuevo");
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

        enemyShadow[row][col].setFill(imagePatter);
    }

    

    public void onHandleMouseExitedShips(int row, int col) {
        Color colorDefault = Color.TRANSPARENT;
        enemyShadow[row][col].setFill(colorDefault);
    }


        @FXML
    public void onHandleReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        playerBot.showMatrix();
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }



}
