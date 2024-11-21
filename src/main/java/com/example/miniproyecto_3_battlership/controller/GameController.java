package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.Player.PlayerBot;
import com.example.miniproyecto_3_battlership.model.Player.PlayerPerson;
import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.model.planeTextFile.PlainTextFileHandler;
import com.example.miniproyecto_3_battlership.model.serializable.Save;
import com.example.miniproyecto_3_battlership.model.serializable.SerializableFileHandler;
import com.example.miniproyecto_3_battlership.model.ships.*;
import com.example.miniproyecto_3_battlership.view.GameSelectionStage;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private ArrayList<Ship> playerShips = new ArrayList<>();
    private ArrayList<Ship> auxPlayerShips;
    private ArrayList<Ship> enemyShips = new ArrayList<>();
    private ArrayList<Ship> auxEnemyShips;

    private Rectangle[][] enemyShadow = new Rectangle[10][10];
    private Save save;

    private Image image;
    private ImagePattern imagePatter;

    private String nameCharacterActual;
    private String nameEnemyActual;
    private int imageEnemyActual;

    private int rowBot , columnbot ;

    private PlainTextFileHandler plainTextFileHandler;


    public void initialize() {

        rowBot = 0;
        columnbot =0;

        plainTextFileHandler = new PlainTextFileHandler();
        String[] data = plainTextFileHandler.readFromFile("character.txt");
        nameCharacterActual = data[0];
        nameEnemyActual = data[1];
        imageEnemyActual = Integer.parseInt(data[2]);

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/mira.png")));
        imagePatter = new ImagePattern(image);

        Image backgroundImage = new Image(getClass().getResource("/com/example/miniproyecto_3_battlership/Image/background_game_battleship.png").toExternalForm());


        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );

        gameBorderPane.setBackground(new Background(background));

    }

    public void setGridPaneShips(ArrayList<int[]> shipsPositions, int[][] shipsSelected) {
        animationIn();

        game = new Game();
        playerBot = game.getPlayerBot();
        playerPerson = game.getPlayerPerson();
        playerPerson.setMatrix();
        playerBot.setMatrix();
        playerPerson.setChosenMatrix(shipsSelected);
        playerBot.generateBotGame();

        setCharacter();
        setEnemy();

        save = new Save(shipsPositions);
        playerShips = save.getShip();
        auxPlayerShips = new ArrayList<>(playerShips);
        createGridPaneGame();

        gridPaneShips.setStyle("-fx-cursor: default;");

        serializableFileHandler.serialize("save.ser", save);
        serializableFileHandler.serialize("game.ser", game);

        playerPerson.showMatrix();
        System.out.println();
        playerBot.showMatrix();
        createEnemuShadows();
    }

    public void Continue() {
        save = (Save) serializableFileHandler.deserialize("save.ser");
        game = (Game) serializableFileHandler.deserialize("game.ser");
        playerBot = game.getPlayerBot();
        playerPerson = game.getPlayerPerson();
        playerShips = save.getShip();
        auxPlayerShips = new ArrayList<>(playerShips);
        setCharacter();
        setEnemy();
        createGridPaneGame();
        gridPaneShips.setStyle("-fx-cursor: default;");
        createEnemuShadows();
        loadGridPaneShips();
        loadGridPaneGame();

    }

    public void createGridPaneGame() {
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

        for(int i = 0; i < playerShips.size(); i++){
            Ship shipSelected = this.playerShips.get(i);
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

    public ArrayList<int[]> shipPositions(ArrayList<Ship> ships) {
        ArrayList<int[]> shipInfo = new ArrayList<>();
        for (int i = 0; i < ships.size(); i++) {
            Ship ship = ships.get(i);
            int row = ship.getPosition()[0];
            int col = ship.getPosition()[1];
            int size = ship.getSize();
            int horizontal = ship.isHorizontal() ? 1 : 0;
            int isDestroyed = ship.isDestroyed() ? 1 : 0;
            shipInfo.add(new int[]{row, col, size, horizontal, isDestroyed});
        }
        return shipInfo;
    }

    @FXML
    public void onHandlePutEnemyShips(){
        for(int i = 0; i < enemyShips.size(); i++){
            if(!(enemyShips.get(i).isDestroyed())) {
                if (enemyShips.get(i).isVisible()) {
                    enemyShips.get(i).setVisible(false);
                } else {
                    enemyShips.get(i).setVisible(true);
                }
            }
        }
    }

    private void setEnemy() {
        if (Objects.equals(nameEnemyActual, " ")) {
            String[] enemys = {"Zarok", "Varek", "Drakk", "Korr", "Morth", "Tharn", "Vulkar", "Grim", "Raek", "Durn"};
            nameEnemyActual = enemys[(int) (Math.random() * 9)];
            lbNameVillain.setText(nameEnemyActual);
            imageEnemyActual = (int) (Math.random() * 2);
            plainTextFileHandler.writeToFile("character.txt", nameCharacterActual + "," + nameEnemyActual + "," + imageEnemyActual);
        } else {
            lbNameVillain.setText(nameEnemyActual);
        }
        if (imageEnemyActual == 0) {
            imgVillain.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character5.png"))));
        } else {
            imgVillain.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character6.png"))));
        }

    }

    public void setCharacter() {

        Image imageCharacterActual;
        nameCharacter.setText(nameCharacterActual);
        if (Objects.equals(nameCharacterActual, "Coronel Sander")) {
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character1.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Almte. Zemansky")) {
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character2.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Mayor Lovelace")) {
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character3.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Coronela Rosalind")) {
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character4.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "????")) {
            nameCharacter.setAlignment(Pos.CENTER);
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character5.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "???")) {
            nameCharacter.setAlignment(Pos.CENTER);
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character6.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Teniente Ampudia")) {
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character7.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Capitana Cordoba")) {
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character8.png")));
            imgCharacter.setImage(imageCharacterActual);
        }
    }

    public void onHandleMouseClickedShips(int row, int column) {
        row += 1;
        column += 1;

        matriz = playerBot.getMatrix();
        if (row != 0 && column != 0) {
            enemyShadow[row - 1][column - 1].setOnMouseClicked(null);
            enemyShadow[row - 1][column - 1].setOnMouseEntered(null);
            enemyShadow[row - 1][column - 1].setOnMouseExited(null);
            enemyShadow[row - 1][column - 1].setStyle("-fx-cursor: default;");
            if (matriz.get(row - 1).get(column - 1) != 0) {
                infoLabel.setText("Felicidad capitan le atinaste, tira nuevamente! ");
                gridPaneGame.add(successSymbol(), column, row);
                playerBot.changeMatrix(row - 1, column - 1, -1);
                PauseTransition pause = new PauseTransition(Duration.seconds(.5));
                pause.setOnFinished(event2 -> {
                    playerTurn();
                });
                pause.play();
                playerTurn();
            } else {
                infoLabel.setText("Oh fallaste, deja a tu openete atacar");
                gridPaneGame.add(errorSymbol(), column, row);
                playerTurn();
                playerBot.changeMatrix(row - 1, column - 1, 2);
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event2 -> {
                    botAttack();
                });
                pause.play();
            }
        }
        int rowSelected;
        int columnSelected;
        boolean isDestroyed;
        playerBot.showMatrix();
        if(playerBot.getMatrix().get(row - 1).get(column - 1) == -1) {
            for (int i = 0; i < enemyShips.size(); i++) {
                isDestroyed = true;
                rowSelected = enemyShips.get(i).getPosition()[0];
                columnSelected = enemyShips.get(i).getPosition()[1];
                for (int j = 0; j < enemyShips.get(i).getSize(); j++) {
                    if(enemyShips.get(i).isHorizontal()){
                        if(!(playerBot.getMatrix().get(rowSelected).get(columnSelected-j) == -1)){
                            isDestroyed = false;
                        }
                    }else{
                        if(!(playerBot.getMatrix().get(rowSelected-j).get(columnSelected) == -1)){
                            isDestroyed = false;
                        }
                    }
                }
                if(isDestroyed){
                    enemyShips.get(i).setVisible(true);
                    enemyShips.get(i).setIsDestroyed(true);
                    infoLabel.setText("¡Has destruido un barco enemigo!");
                    for (int j = 0; j < enemyShips.get(i).getSize(); j++) {
                        Group group = new Group(new Circle(25, 25, 20, Color.RED));
                        group.setEffect(new DropShadow(4, Color.BLACK));
                        if (enemyShips.get(i).isHorizontal()) {
                            gridPaneGame.add(group, columnSelected + 1 - j, rowSelected + 1);
                        } else {
                            gridPaneGame.add(group, columnSelected + 1, rowSelected + 1 - j);
                        }
                    }
                    enemyShips.remove(i);
                }
            }
        }
        save.setShipPositions(shipPositions(auxPlayerShips));
        playerBot.setEnemyShipsInfo(shipPositions(auxEnemyShips));
        serializableFileHandler.serialize("save.ser", save);
        serializableFileHandler.serialize("game.ser", game);
        victory(game.verifyWinner(playerBot));

    }

    public void loadGridPaneShips() {
        matriz = playerPerson.getMatrix();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matriz.get(i).get(j) == 2) {
                    gridPaneShips.add(errorSymbol(), j + 1, i + 1);
                } else if (matriz.get(i).get(j) == -1) {
                    gridPaneShips.add(successSymbol(), j+1, i+1);
                }
            }
        }
        int rowSelected;
        int columnSelected;
        boolean isDestroyed;
            for (int k = 0; k < playerShips.size(); k++) {
                isDestroyed = true;
                rowSelected = playerShips.get(k).getPosition()[0];
                columnSelected = playerShips.get(k).getPosition()[1];
                for (int l = 0; l < playerShips.get(k).getSize(); l++) {
                    if(playerShips.get(k).isHorizontal()){
                        if(!(matriz.get(rowSelected).get(columnSelected-l) == -1)){
                            isDestroyed = false;
                        }
                    }else{
                        if(!(matriz.get(rowSelected-l).get(columnSelected) == -1)){
                            isDestroyed = false;
                        }
                    }
                }
                if(isDestroyed){
                    playerShips.get(k).setVisible(true);
                    playerShips.get(k).setIsDestroyed(true);
                    infoLabel.setText("¡Has destruido un barco enemigo!");
                    for (int j = 0; j < playerShips.get(k).getSize(); j++) {
                        Group group = new Group(new Circle(25, 25, 20, Color.RED));
                        group.setEffect(new DropShadow(4, Color.BLACK));
                        if (playerShips.get(k).isHorizontal()) {
                            gridPaneShips.add(group, columnSelected + 1 - j, rowSelected + 1);
                        } else {
                            gridPaneShips.add(group, columnSelected + 1, rowSelected + 1 - j);
                        }
                    }
                    playerShips.remove(k);
                }
            }
        }

    public void loadGridPaneGame() {
        matriz = playerBot.getMatrix();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matriz.get(i).get(j) == 2) {
                    gridPaneGame.add(errorSymbol(), j + 1, i + 1);
                    enemyShadow[i][j].setOnMouseClicked(null);
                    enemyShadow[i][j].setOnMouseEntered(null);
                    enemyShadow[i][j].setOnMouseExited(null);
                } else if (matriz.get(i).get(j) == -1) {
                    gridPaneGame.add(successSymbol(), j + 1, i + 1);
                    enemyShadow[i][j].setOnMouseClicked(null);
                    enemyShadow[i][j].setOnMouseEntered(null);
                    enemyShadow[i][j].setOnMouseExited(null);
                }
            }
        }
        int rowSelected;
        int columnSelected;
        boolean isDestroyed;
        for (int k = 0; k < enemyShips.size(); k++) {
            isDestroyed = true;
            rowSelected = enemyShips.get(k).getPosition()[0];
            columnSelected = enemyShips.get(k).getPosition()[1];
            for (int l = 0; l < enemyShips.get(k).getSize(); l++) {
                if (enemyShips.get(k).isHorizontal()) {
                    if (!(matriz.get(rowSelected).get(columnSelected - l) == -1)) {
                        isDestroyed = false;
                    }
                } else {
                    if (!(matriz.get(rowSelected - l).get(columnSelected) == -1)) {
                        isDestroyed = false;
                    }
                }
            }
            if (isDestroyed) {
                enemyShips.get(k).setVisible(true);
                enemyShips.get(k).setIsDestroyed(true);
                infoLabel.setText("¡Has destruido un barco enemigo!");
                for (int j = 0; j < enemyShips.get(k).getSize(); j++) {
                    Group group = new Group(new Circle(25, 25, 20, Color.RED));
                    group.setEffect(new DropShadow(4, Color.BLACK));
                    if (enemyShips.get(k).isHorizontal()) {
                        gridPaneGame.add(group, columnSelected + 1 - j, rowSelected + 1);
                    } else {
                        gridPaneGame.add(group, columnSelected + 1, rowSelected + 1 - j);
                    }
                }
                enemyShips.remove(k);
            }
        }
    }

    public void playerTurn() {
        if (gridPaneGame.isDisable()) {
            gridPaneGame.setDisable(false);
        } else {
            gridPaneGame.setDisable(true);
        }
    }

    @FXML
    void botAttack() {
        infoLabel.setText("La maquina esta pensando...");
        playerBot.generatePositionRandom(playerPerson.getMatrix());
        rowBot = playerBot.getPositionRandom()[0];
        columnbot = playerBot.getPositionRandom()[1];
        matriz = playerPerson.getMatrix();
        System.out.print("row: " + rowBot + " column: " + columnbot);

        if (matriz.get(rowBot - 1).get(columnbot - 1) != 0) {
            infoLabel.setText("Tu opente ha acertado, atacara nuevamente");
            gridPaneShips.add(successSymbol(), columnbot, rowBot);
            playerPerson.changeMatrix(rowBot - 1, columnbot - 1, -1);
            defeat((game.verifyWinner(playerPerson)));
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event2 -> {
                infoLabel.setText("La maquina esta pensando...");
                botAttack();
            });
            pause.play();

        } else {
            infoLabel.setText("Tu oponente ha fallado, es tu turno");
            gridPaneShips.add(errorSymbol(), columnbot, rowBot);
            playerPerson.changeMatrix(rowBot - 1, columnbot - 1, 2);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                playerTurn();
            });
            pause.play();
        }
        int rowSelected;
        int columnSelected;
        boolean isDestroyed;
        if(playerPerson.getMatrix().get(rowBot - 1).get(columnbot - 1) == -1) {
            for (int i = 0; i < playerShips.size(); i++) {
                isDestroyed = true;
                rowSelected = playerShips.get(i).getPosition()[0];
                columnSelected = playerShips.get(i).getPosition()[1];
                for (int j = 0; j < playerShips.get(i).getSize(); j++) {
                    if(playerShips.get(i).isHorizontal()){
                        if(!(playerPerson.getMatrix().get(rowSelected).get(columnSelected-j) == -1)){
                            isDestroyed = false;
                        }
                    }else{
                        if(!(playerPerson.getMatrix().get(rowSelected-j).get(columnSelected) == -1)){
                            isDestroyed = false;
                        }
                    }
                }
                if(isDestroyed){
                    playerShips.get(i).setVisible(true);
                    playerShips.get(i).setIsDestroyed(true);
                    infoLabel.setText("¡El enemigo destruyo un barco!");
                    for (int j = 0; j < playerShips.get(i).getSize(); j++) {
                        Group group = new Group(new Circle(25, 25, 20, Color.RED));
                        group.setEffect(new DropShadow(4, Color.BLACK));
                        if (playerShips.get(i).isHorizontal()) {
                            gridPaneShips.add(group, columnSelected + 1 - j, rowSelected + 1);
                        } else {
                            gridPaneShips.add(group, columnSelected + 1, rowSelected + 1 - j);
                        }
                    }
                    playerShips.remove(i);
                }
            }
        }
        save.setShipPositions(shipPositions(auxPlayerShips));
        playerBot.setEnemyShipsInfo(shipPositions(auxEnemyShips));
        serializableFileHandler.serialize("save.ser", save);
        serializableFileHandler.serialize("game.ser", game);
    }

    public Group errorSymbol() {
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

    public Group successSymbol() {
        Group group = new Group();
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/bombfx.png")));
        ImagePattern imagePattern4 = new ImagePattern(image4);
        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/ropetxt.png")));
        ImagePattern imagePattern5 = new ImagePattern(image5);

        Circle bombBody = new Circle(25, 25, 20);
        bombBody.setFill(Color.DARKSLATEBLUE); // Color gris oscuro con matiz azul
        bombBody.setFill(imagePattern4);
        bombBody.setEffect(new DropShadow(4, Color.BLACK));
        Polygon fuse = new Polygon(25, 5, 40, 0,42,2,26,7);
        fuse.setFill(imagePattern5);

        Polygon spark = new Polygon(
                28, -12,  // Pico superior largo
                30, -6,  // Pico superior derecho corto
                33, -4,  // Pico derecho largo
                30, -2,  // Pico inferior derecho corto
                28, 0,   // Pico inferior largo
                26, -2,  // Pico inferior izquierdo corto
                23, -4,  // Pico izquierdo largo
                26, -6   // Pico superior izquierdo corto
        );
        spark.setFill(Color.rgb(239,196,64));
        spark.setStroke(Color.ORANGE);
        spark.setStrokeWidth(0.5);
        spark.setEffect(new DropShadow(5, Color.YELLOW));

        spark.setScaleX(1.2);
        spark.setScaleY(1.2);
        spark.setTranslateX(15);
        spark.setTranslateY(0);


        group.getChildren().addAll(bombBody, fuse, spark);
        return group;
    }

    public void victory(boolean victory) {
        if (victory) {
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

        playVideoVictory();

    }

    private void playVideoVictory() {
//        Pane pane = new Pane();
//        pane.setPrefSize(1920, 1080);
//        Image video = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/gifs/victory.gif")));
//        ImageView imageView = new ImageView(video);
//        imageView.setFitWidth(1920);
//        imageView.setFitHeight(1080);
//        imageView.setPreserveRatio(true);
//
//        pane.getChildren().add(imageView);
//
//        Stage paneStage = new Stage();
//        Scene paneScene = new Scene(pane);
//        paneStage.initStyle(StageStyle.UNDECORATED);
//        paneStage.setScene(paneScene);
//        paneStage.setTitle("Pane con Imagen");
//        paneStage.show();
    }

    public void defeat(boolean defeat) {
        if (defeat) {
            infoLabel.setText("¡Has perdido! Intentalo de nuevo");
            gridPaneGame.setDisable(true);
        }

    }

    public void animationIn() {

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
        enemyShips = playerBot.getEnemyShips();
        auxEnemyShips = new ArrayList<>(enemyShips);
        for(int i = 0; i < enemyShips.size(); i++){
            Ship shipSelected = enemyShips.get(i);
            int row = shipSelected.getPosition()[0] + 1;
            int col = shipSelected.getPosition()[1] + 1;
            try {
                if (shipSelected.isHorizontal()) {
                    gridPaneGame.add(shipSelected, col - shipSelected.getSize() + 1, row);
                    GridPane.setRowSpan(shipSelected, 0);
                    GridPane.setColumnSpan(shipSelected, shipSelected.getSize());
                } else {
                    gridPaneGame.add(shipSelected, col, row - shipSelected.getSize() + 1);
                    GridPane.setColumnSpan(shipSelected, 0);
                    GridPane.setRowSpan(shipSelected, shipSelected.getSize());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            shipSelected.setVisible(false);
        }
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
    void onHandleClickResetGame(ActionEvent event) throws IOException {
        GameStage.deleteInstance();
        GameSelectionStage.getInstance();
    }

    @FXML
    public void onHandleReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        playerBot.showMatrix();
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }


}
