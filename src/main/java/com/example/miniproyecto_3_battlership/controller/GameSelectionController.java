package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.exeption.CrossedShipsException;
import com.example.miniproyecto_3_battlership.model.planeTextFile.PlainTextFileHandler;
import com.example.miniproyecto_3_battlership.model.ships.*;
import com.example.miniproyecto_3_battlership.view.GameSelectionStage;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameSelectionController {

    @FXML
    private GridPane gridPaneShips;

    @FXML
    private BorderPane gameBorderPane;

    @FXML
    private VBox shipSelectVbox;

    @FXML
    private HBox hBoxSubmarinos;

    @FXML
    private HBox hBoxPortaAviones;

    @FXML
    private Label infoLabel;

    @FXML
    private Label lbSelecction;

    @FXML
    private Rectangle rectangleLabelInfo;

    @FXML
    private Rectangle rectangleLabelSelection;


    private Color colorDefault = Color.TRANSPARENT;

    private Color colorhover = Color.rgb(0, 0, 0, 0.5);

    Fragata[] fragatas = new Fragata[4];
    Destructor[] destructores = new Destructor[3];
    Submarino[] submarinos = new Submarino[2];
    Portaaviones[] portaaviones = new Portaaviones[1];
    ArrayList<Ship> ships = new ArrayList<>();
    ArrayList<int[]> shipsPosition = new ArrayList<>();

    private Ship shipSelected;
    private final int[][] shipsSelected = new int[10][10];
    private final int[][] positionsHeadShips = new int[10][10];

    private boolean habitable;

    private int actualShadowRow;

    private int actualShadowCol;

    @FXML
    private HBox hBoxDestructores;

    @FXML
    private HBox hBoxFragatas;

    @FXML
    private AnchorPane anchorPaneLeft;

    @FXML
    private AnchorPane anchorPaneMiddle;

    @FXML
    private Label nameCharacter;

    @FXML
    private ImageView imgCharacter;

    @FXML
    private Button randomButton;


    private final Rectangle[][] shadowShipsSelection = new Rectangle[10][10];


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

        createShadowShip();

        for (int i = 0; i < 4; i++) {
            fragatas[i] = new Fragata();
            int finalI = i;
            fragatas[i].setOnMouseClicked(e -> shipSelected(fragatas[finalI]));
            hBoxFragatas.getChildren().add(fragatas[i]);
            ships.add(fragatas[i]);
        }

        for (int i = 0; i < 3; i++) {
            destructores[i] = new Destructor();
            int finalI = i;
            destructores[i].setOnMouseClicked(e -> shipSelected(destructores[finalI]));
            hBoxDestructores.getChildren().add(destructores[i]);
            ships.add(destructores[i]);
        }

        for (int i = 0; i < 2; i++) {
            submarinos[i] = new Submarino();
            int finalI = i;
            submarinos[i].setOnMouseClicked(e -> shipSelected(submarinos[finalI]));
            hBoxSubmarinos.getChildren().add(submarinos[i]);
            ships.add(submarinos[i]);
        }

        for (int i = 0; i < 1; i++) {
            portaaviones[i] = new Portaaviones();
            int finalI = i;
            portaaviones[i].setOnMouseClicked(e -> shipSelected(portaaviones[finalI]));
            hBoxPortaAviones.getChildren().add(portaaviones[i]);
            ships.add(portaaviones[i]);
        }

        infoLabel.setText("Teniente seleccione sus barcos");
        setCharacter();
    }

    public void shipPositions() {
        for (int i = 0; i < ships.size(); i++) {
            Ship ship = ships.get(i);
            int row = ship.getPosition()[0];
            int col = ship.getPosition()[1];
            int size = ship.getSize();
            int horizontal = ship.isHorizontal() ? 1 : 0;
            int isDestroyed = ship.isDestroyed() ? 1 : 0;
            shipsPosition.add(new int[]{row, col, size, horizontal, isDestroyed});

        }
    }

    public void setCharacter() {
        PlainTextFileHandler plainTextFileHandler = new PlainTextFileHandler();
        String[] data = plainTextFileHandler.readFromFile("character.txt");
        String nameCharacterActual = data[0];
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

    @FXML
    public void onHandleRandomButton() {

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(randomButton);
        rotateTransition.setCycleCount(1);
        rotateTransition.setDuration(Duration.seconds(.5));

        Random rand = new Random();
        int randomRotation = rand.nextInt(360) + 360 * 3;

        rotateTransition.setByAngle(randomRotation);
        rotateTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(randomButton);
        translateTransition.setCycleCount(2);
        translateTransition.setDuration(Duration.seconds(0.1));

        translateTransition.setByX(10);
        translateTransition.setAutoReverse(true);

        translateTransition.play();
        rotateTransition.play();

        rotateTransition.setOnFinished(event2 -> {
            int randomRow, randomCol, randomHorientation;
            if (shipSelected != null) {
                shipSelected(shipSelected);
            }
            for (int i = 0; i < ships.size(); i++) {
                shipSelected(ships.get(i));
                do {
                    randomRow = (int) (Math.random() * 9);
                    randomCol = (int) (Math.random() * 9);
                    randomHorientation = (int) (Math.random() * 1);
                    if (randomHorientation == 0) {
                        onHandleBorderPaneKeyTyped2();
                    }
                    onHandleMouseEnteredShips(randomRow, randomCol);
                    onHandleMouseClickedShips(randomRow, randomCol);
                    onHandleMouseExitedShips(randomRow, randomCol);

                } while (!shipSelected.isPlaced() && !habitable);
                shipSelected(shipSelected);
            }
            resetShadow();
            infoLabel.setText("Teniente se pusieron sus barcos de manera estrategica");

        });


    }


    public void shipSelected(Ship ship) {
        if (shipSelected != null && shipSelected != ship) {
            shipSelected.originDesing();
            shipSelected = null;
        }
        if (ship.isSelect()) {
            ship.originDesing();
            shipSelected = null;
        } else {
            ship.selectDesing();
            shipSelected = ship;
        }
    }

    @FXML
    void onHandleBorderPaneKeyTyped(KeyEvent event) {
        if (event.getCharacter().equalsIgnoreCase("R") && shipSelected != null) {
            if (!shipSelected.isPlaced()) {
                shipSelected.rotateShip();
                shipSelected.setPotentialRotate(!shipSelected.potentialRotate());
            } else {
                shipSelected.setPotentialRotate(!shipSelected.potentialRotate());
            }
            if (actualShadowCol != -1) {
                for (int i = 0; i < shadowShipsSelection.length; i++) {
                    for (int j = 0; j < shadowShipsSelection[i].length; j++) {
                        shadowShipsSelection[i][j].setFill(Color.TRANSPARENT);
                    }
                }
                onHandleMouseEnteredShips(actualShadowRow, actualShadowCol);
            }
        }
    }

    void onHandleBorderPaneKeyTyped2() {
        if (!shipSelected.isPlaced()) {
            shipSelected.rotateShip();
            shipSelected.setPotentialRotate(!shipSelected.potentialRotate());
        } else {
            shipSelected.setPotentialRotate(!shipSelected.potentialRotate());
        }
        if (actualShadowCol != -1) {
            for (int i = 0; i < shadowShipsSelection.length; i++) {
                for (int j = 0; j < shadowShipsSelection[i].length; j++) {
                    shadowShipsSelection[i][j].setFill(Color.TRANSPARENT);
                }
            }
            onHandleMouseEnteredShips(actualShadowRow, actualShadowCol);
        }
    }


    @FXML
    void createShadowShip() {
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneShips.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto_3_battlership/Css/css.css")).toExternalForm());
        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.getStyleClass().add("cell");
                shadowShipsSelection[rows - 1][col - 1] = cell;
                int finalRows = rows - 1;
                int finalCol = col - 1;
                shadowShipsSelection[rows - 1][col - 1].setOnMouseEntered(e -> onHandleMouseEnteredShips(finalRows, finalCol));
                shadowShipsSelection[rows - 1][col - 1].setOnMouseExited(e -> onHandleMouseExitedShips(finalRows, finalCol));
                shadowShipsSelection[rows - 1][col - 1].setOnMouseClicked(e -> onHandleMouseClickedShips(finalRows, finalCol));
                gridPaneShips.add(shadowShipsSelection[rows - 1][col - 1], col, rows);
            }
        }
    }


    private void onHandleMouseEnteredShips(int row, int col) {
        actualShadowCol = col;
        actualShadowRow = row;
        colorhover = Color.rgb(0, 0, 0, 0.5);
        if (shipSelected != null) {
            try {
                habitable = true;
                System.out.println(shipSelected.isHorizontal());
                for (int j = 0; j < shipSelected.getSize(); j++) {
                    if (shipSelected.potentialRotate()) {
                        if (shipsSelected[row][col - j] != 0) {
                            throw new CrossedShipsException();
                        }
                    } else {
                        if (shipsSelected[row - j][col] != 0) {
                            throw new CrossedShipsException();
                        }
                    }
                }
                if ((shipSelected.isHorizontal() && !shipSelected.isPlaced()) || shipSelected.potentialRotate()) {
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row][col - (i - 1)].setFill(colorhover);
                    }
                } else {
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row - (i - 1)][col].setFill(colorhover);
                    }

                }
            } catch (ArrayIndexOutOfBoundsException | CrossedShipsException x) {
                System.out.println("Error en la grilla");
                colorhover = Color.rgb(254, 0, 0, 0.2);
                try {
                    habitable = false;
                    if ((shipSelected.isHorizontal() && !shipSelected.isPlaced()) || shipSelected.potentialRotate()) {
                        for (int i = 1; i <= shipSelected.getSize(); i++) {
                            shadowShipsSelection[row][col - (i - 1)].setFill(colorhover);
                            System.out.println("se pinto una vez");
                        }
                    } else {
                        for (int i = 1; i <= shipSelected.getSize(); i++) {
                            shadowShipsSelection[row - (i - 1)][col].setFill(colorhover);
                            System.out.println("se pinto una vez");
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error en la grilla 2");
                }

            }
        }


    }

    private void onHandleMouseExitedShips(int row, int col) {
        actualShadowCol = -1;
        actualShadowRow = -1;
        if (shipSelected != null) {
            colorDefault = Color.TRANSPARENT;
            try {
                if ((shipSelected.isHorizontal() && !shipSelected.isPlaced()) || shipSelected.potentialRotate()) {
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row][col - (i - 1)].setFill(colorDefault);
                    }
                } else {
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row - (i - 1)][col].setFill(colorDefault);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException x) {

                System.out.println("Error en la grilla");

            }
        }

    }

    public void onHandleMouseClickedShips(int row, int col) {
        row += 1;
        col += 1;

        if (shipSelected != null && habitable) {
            if (shipSelected.isPlaced()) {
                for (int i = 0; i < shipSelected.getSize(); i++) {
                    if (shipSelected.isHorizontal()) {
                        shipsSelected[shipSelected.getPosition()[0]][shipSelected.getPosition()[1] - i] = 0;
                    } else {
                        shipsSelected[shipSelected.getPosition()[0] - i][shipSelected.getPosition()[1]] = 0;
                    }
                }
            }
            shipSelected.setIsPlaced(true);
            gridPaneShips.getChildren().remove(shipSelected);
            if (shipSelected.potentialRotate() != shipSelected.isHorizontal()) {
                shipSelected.rotateShip();
            }
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
                shipSelected.setPosition(row - 1, col - 1);
                positionsHeadShips[row - 1][col - 1] = shipSelected.getSize();
                for (int i = 0; i < shipSelected.getSize(); i++) {
                    if (shipSelected.isHorizontal()) {
                        shipsSelected[shipSelected.getPosition()[0]][shipSelected.getPosition()[1] - i] = 1;
                    } else {
                        shipsSelected[shipSelected.getPosition()[0] - i][shipSelected.getPosition()[1]] = 1;
                    }

                }
                infoLabel.setText("Teniente seleccione sus barcos");
            }
        } else if (!habitable) {
            infoLabel.setText("Teniente ponga sus barcos en posiciones validas");
        }
    }


    @FXML
    void onHandleStartGame(ActionEvent event) throws IOException {
        int totalSum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (shipsSelected[i][j] != 0) {
                    totalSum++;
                }
            }
        }

        if (totalSum != 20) {
            infoLabel.setText("Teniente debe seleccionar todos los barcos antes de poder ir a la batalla");
            return;
        }


        rectangleLabelInfo.setOpacity(0);
        infoLabel.setOpacity(0);
        rectangleLabelSelection.setOpacity(0);
        lbSelecction.setOpacity(0);

        Timeline timeline = new Timeline();

        int duration = 2000;
        int frames = 30;

        for (int i = 0; i < frames; i++) {

            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(i * (duration / frames)),
                    e -> {

                        double offsetX = Math.random() * 4 - 4;
                        double offsetY = Math.random() * 4 - 5;

                        gameBorderPane.setTranslateX(offsetX);
                        gameBorderPane.setTranslateY(offsetY);
                    }
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setOnFinished(e -> {
            gameBorderPane.setTranslateX(0);
            gameBorderPane.setTranslateY(0);
        });


        timeline.play();

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.8), anchorPaneLeft);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.5);

        TranslateTransition moveRight = new TranslateTransition(Duration.seconds(0.5), anchorPaneLeft);
        moveRight.setFromX(0);
        moveRight.setToX(anchorPaneLeft.getBoundsInParent().getWidth());

        ParallelTransition transitionLeft = new ParallelTransition(fadeOut, moveRight);
        transitionLeft.play();

        FadeTransition fadeOut2 = new FadeTransition(Duration.seconds(0.8), anchorPaneMiddle);
        fadeOut2.setFromValue(1.0);
        fadeOut2.setToValue(0.7);

        TranslateTransition moveLeft = new TranslateTransition(Duration.seconds(1.5), anchorPaneMiddle);
        moveLeft.setFromX(0);
        moveLeft.setToX(140);

        ParallelTransition transitionMiddle = new ParallelTransition(fadeOut2, moveLeft);
        transitionMiddle.play();
        moveLeft.setInterpolator(Interpolator.EASE_BOTH);

        shipPositions();

        fadeOut.setOnFinished(event2 -> {
            Platform.runLater(() -> {
                try {
                    GameStage.getInstance().getGameController().setGridPaneShips(shipsPosition, shipsSelected);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        moveLeft.setOnFinished(event3 -> {
            GameSelectionStage.deleteInstance();
        });


    }

    public void resetShadow() {
        for (Rectangle[] rectangle : shadowShipsSelection) {
            for (Rectangle r : rectangle) {
                r.setFill(colorDefault);
            }
        }
    }


    @FXML
    public void onHandleReturn(ActionEvent actionEvent) throws IOException {
        GameSelectionStage.deleteInstance();
        WelcomeStage.getInstance();
    }

}
