package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.CrossedShipsException;
import com.example.miniproyecto_3_battlership.model.ships.*;
import com.example.miniproyecto_3_battlership.view.GameSelectionStage;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

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

    private Color colorDefault = Color.TRANSPARENT;

    private Color colorhover = Color.rgb(0,0,0,0.5 );

    Fragata[] fragatas = new Fragata[4];
    Destructor[] destructores = new Destructor[3];
    Submarino[] submarinos = new Submarino[2];
    Portaaviones[] portaaviones = new Portaaviones[1];

    private Ship shipSelected;
    private int[][] shipsSelected = new int[10][10];

    private boolean habitable;

    private int actualShadowRow;

    private int actualShadowCol;

    @FXML
    private HBox hBoxDestructores;

    @FXML
    private HBox hBoxFragatas;


    private Rectangle[][] shadowShipsSelection = new Rectangle[10][10];


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

        for(int i = 0; i < 4; i++){
            fragatas[i] = new Fragata();
            int finalI = i;
            fragatas[i].setOnMouseClicked(e -> shipSelected(fragatas[finalI]));
            hBoxFragatas.getChildren().add(fragatas[i]);
        }

        for(int i = 0; i < 3; i++){
            destructores[i] = new Destructor();
            int finalI = i;
            destructores[i].setOnMouseClicked(e -> shipSelected(destructores[finalI]));
            hBoxDestructores.getChildren().add(destructores[i]);
        }

        for(int i = 0; i < 2; i++){
            submarinos[i] = new Submarino();
            int finalI = i;
            submarinos[i].setOnMouseClicked(e -> shipSelected(submarinos[finalI]));
            hBoxSubmarinos.getChildren().add(submarinos[i]);
        }

        for(int i = 0; i < 1; i++){
            portaaviones[i] = new Portaaviones();
            int finalI = i;
            portaaviones[i].setOnMouseClicked(e -> shipSelected(portaaviones[finalI]));
            hBoxPortaAviones.getChildren().add(portaaviones[i]);
        }

        infoLabel.setText("Teniente seleccione sus barcos");

    }



    public void shipSelected(Ship ship) {
        if(shipSelected != null && shipSelected != ship){
            shipSelected.originDesing();
            shipSelected = null;
        }
        if(ship.isSelect()) {
            ship.originDesing();
            shipSelected = null;
        } else {
            ship.selectDesing();
            shipSelected = ship;
        }
    }

    @FXML
    void onHandleBorderPaneKeyTyped(KeyEvent event) {
        if(event.getCharacter().equalsIgnoreCase("R") && shipSelected != null){
            if(!shipSelected.isPlaced()) {
                shipSelected.rotateShip();
                shipSelected.setPotentialRotate(!shipSelected.potentialRotate());
            }else{
                shipSelected.setPotentialRotate(!shipSelected.potentialRotate());
            }
            if(actualShadowCol != -1) {
                for(int i = 0; i < shadowShipsSelection.length; i++){
                    for(int j = 0; j < shadowShipsSelection[i].length; j++){
                        shadowShipsSelection[i][j].setFill(Color.TRANSPARENT);
                    }
                }
                onHandleMouseEnteredShips(actualShadowRow, actualShadowCol);
            }
        }
    }



    @FXML
    void createShadowShip() {;
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneShips.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto_3_battlership/Css/css.css")).toExternalForm());
        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.getStyleClass().add("cell");
                shadowShipsSelection[rows-1][col-1] = cell;
                int finalRows = rows-1;
                int finalCol = col-1;
                shadowShipsSelection[rows-1][col-1].setOnMouseEntered(e -> onHandleMouseEnteredShips(finalRows, finalCol));
                shadowShipsSelection[rows-1][col-1].setOnMouseExited(e -> onHandleMouseExitedShips(finalRows, finalCol));
                shadowShipsSelection[rows-1][col-1].setOnMouseClicked(e -> onHandleMouseClickedShips(finalRows, finalCol));
                gridPaneShips.add(shadowShipsSelection[rows-1][col-1], col, rows);
            }
        }
    }


    private void onHandleMouseEnteredShips(int row, int col) {
        actualShadowCol = col;
        actualShadowRow = row;
        colorhover = Color.rgb(0,0,0,0.5 );
        if(shipSelected != null) {
            try {
                habitable = true;
                System.out.println(shipSelected.isHorizontal());
                for(int j = 0; j < shipSelected.getSize(); j++) {
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
                if((shipSelected.isHorizontal() && !shipSelected.isPlaced()) || shipSelected.potentialRotate()) {
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row][col - (i - 1)].setFill(colorhover);
                    }
                }else{
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row- (i - 1)][col].setFill(colorhover);
                    }

                }
            } catch (ArrayIndexOutOfBoundsException | CrossedShipsException x) {
                System.out.println("Error en la grilla");
                colorhover = Color.rgb(254,0,0,0.2 );
                try {
                    habitable = false;
                    if((shipSelected.isHorizontal() && !shipSelected.isPlaced()) || shipSelected.potentialRotate()) {
                        for (int i = 1; i <= shipSelected.getSize(); i++) {
                            shadowShipsSelection[row][col - (i - 1)].setFill(colorhover);
                            System.out.println("se pinto una vez");
                        }
                    }else{
                        for (int i = 1; i <= shipSelected.getSize(); i++) {
                            shadowShipsSelection[row - (i - 1)][col].setFill(colorhover);
                            System.out.println("se pinto una vez");
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Error en la grilla 2");
                }

            }
        }



    }

    private void onHandleMouseExitedShips(int row, int col) {
        actualShadowCol = -1;
        actualShadowRow = -1;
        if(shipSelected != null) {
            colorDefault = Color.TRANSPARENT;
            try {
                if((shipSelected.isHorizontal() && !shipSelected.isPlaced()) || shipSelected.potentialRotate()) {
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row][col - (i - 1)].setFill(colorDefault);
                    }
                }else{
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row - (i - 1)][col].setFill(colorDefault);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException x) {

                System.out.println("Error en la grilla");

            }
        }

    }

    public void onHandleMouseClickedShips(int row, int col){
        row += 1;
        col += 1;

        if(shipSelected != null && habitable ){
            if(shipSelected.isPlaced()){
                for(int i = 0; i < shipSelected.getSize(); i++) {
                    if(shipSelected.isHorizontal()) {
                        shipsSelected[shipSelected.getPosition()[0]][shipSelected.getPosition()[1] - i] = 0;
                    }else{
                        shipsSelected[shipSelected.getPosition()[0] - i][shipSelected.getPosition()[1]] = 0;
                    }
                }
            }
            shipSelected.setIsPlaced(true);
            gridPaneShips.getChildren().remove(shipSelected);
            if(shipSelected.potentialRotate() != shipSelected.isHorizontal()){
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
            }catch (IllegalArgumentException e){
                shipSelected.setPosition(row - 1, col - 1);
                for(int i = 0; i < shipSelected.getSize(); i++) {
                    if(shipSelected.isHorizontal()) {
                        shipsSelected[shipSelected.getPosition()[0]][shipSelected.getPosition()[1] - i] = 1;
                    }else{
                        shipsSelected[shipSelected.getPosition()[0] - i][shipSelected.getPosition()[1]] = 1;
                    }
                }
                infoLabel.setText("Teniente seleccione sus barcos");
            }
        }else if(!habitable){
            infoLabel.setText("Teniente ponga sus barcos en posiciones validas");
        }
    }

    public void imprimirMatriz(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(shipsSelected[i][j] + " ");
            }
            System.out.println();
        }
    }

    @FXML
    void onHandleStartGame(ActionEvent event) throws IOException {
        int totalSum = 0;
        for(int i = 0;i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(shipsSelected[i][j] != 0) {
                    totalSum++;
                }
            }
        }

        if(totalSum != 20){
            infoLabel.setText("Teniente debe seleccionar todos los barcos antes de poder ir a la batalla");
            return;
        }

        for(int i = 0;i < 10; i++){
            for(int j = 0; j < 10; j++){
                shipsSelected[i][j] = 0;
            }
        }

        for (var node : gridPaneShips.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);
            rowIndex = (rowIndex == null) ? 0 : rowIndex;
            colIndex = (colIndex == null) ? 0 : colIndex;

            if (node instanceof Fragata fragata) {
                shipsSelected[rowIndex-1][colIndex-1]=1;
            }
            if (node instanceof Destructor destructor) {
                for (int i = 0; i < destructor.getSize(); i++) {
                    shipsSelected[rowIndex-1][colIndex+i-1]=2;
                }
            }
            if (node instanceof Submarino submarino) {
                for (int i = 0; i < submarino.getSize(); i++) {
                    shipsSelected[rowIndex-1][colIndex+i-1]=3;
                }
            }
            if (node instanceof Portaaviones portaaviones) {
                for(int i = 0; i < portaaviones.getSize(); i++){
                    shipsSelected[rowIndex-1][colIndex+i-1]=4;
                }
            }
        }
        imprimirMatriz();
        GameSelectionStage.getInstance();
        GameStage.getInstance();
        GameStage.getInstance().getGameController().createShips(shipsSelected);
        GameSelectionStage.deleteInstance();
    }


    @FXML
    public void onHandleReturn(ActionEvent actionEvent) throws IOException {
        GameSelectionStage.deleteInstance();
        WelcomeStage.getInstance();
    }

}
