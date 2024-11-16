package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.model.ships.*;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameController {

    private Game game;


    @FXML
    private GridPane gridPaneGame;

    @FXML
    private GridPane gridPaneShips;

    @FXML
    private BorderPane gameBorderPane;

    @FXML
    private AnchorPane anchorPaneMiddle;

    Fragata[] fragatas = new Fragata[4];
    Destructor[] destructores = new Destructor[3];
    Submarino[] submarinos = new Submarino[2];
    Portaaviones[] portaaviones = new Portaaviones[1];
    private ArrayList<ArrayList<Integer>> matriz;



    public void initialize() {


        //IMAGEN DE FONDO
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
//        createBordersGridPaneGame();
        game = new Game();
        game.setMatrix();
        boolean error = true;
        do {
            try {

                game.generateGameBot();
                error = false;
            } catch (IndexOutOfBoundsException e) {
                game.clearMatriz();
                System.out.println("Error, intentando nuevamente");
            }
        } while (error);


    }


    @FXML
    void onHandleMousePressedGame(MouseEvent event) {
        double cellWidth = gridPaneGame.getWidth() / gridPaneGame.getColumnCount();
        double cellHeight = gridPaneGame.getHeight() / gridPaneGame.getRowCount();

        System.out.println(cellWidth + " " + cellHeight);

        double x = event.getX();
        double y = event.getY();

        int column = (int) (x / cellWidth);
        int row = (int) (y / cellHeight);

        matriz = game.getMatriz();
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

        System.out.println("Clic en la celda: columna " + column + ", fila " + row);
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
//
//    public void putShips(int[][] positionHeadShips){
//        int[] nShips = {0,0,0,0};
//        for (int row = 0; row < 10; row++) {
//            for (int col = 0; col < 10; col++) {
//                switch (positionHeadShips[row][col] ){
//                    case 1:
//                        gridPaneShips.add(fragatas[nShips[0]], col+1, row+1);
//                        nShips[0]++;
//                        break;
//                    case 2:
//                        addShipsGridPaneShips(destructores[nShips[1]], row, col);
//                        nShips[1]++;
//                        break;
//                    case 3:
//                        addShipsGridPaneShips(submarinos[nShips[2]], row, col);
//                        nShips[2]++;
//                        break;
//                    case 4:
//                        addShipsGridPaneShips(portaaviones[nShips[3]], row, col);
//                        nShips[3]++;
//                        break;
//                }
//            }
//        }
//    }
//
//    public void addShipsGridPaneShips(Ship ship, int row, int col) {
//        if (ship.isHorizontal()) {
//            gridPaneShips.add(ship, col -ship.getSize() +2, row+1);
//            GridPane.setRowSpan(ship, 0);
//            GridPane.setColumnSpan(ship, ship.getSize());
//        } else {
//            gridPaneShips.add(ship, col+1, row - ship.getSize()+2);
//            GridPane.setColumnSpan(ship, 1);
//            GridPane.setRowSpan(ship, ship.getSize());
//        }
//    }

    public void imprimirMatriz(int[][] shipsSelected) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(shipsSelected[i][j] + " ");
            }
            System.out.println();
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



    @FXML
    public void onHandleReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }



}
