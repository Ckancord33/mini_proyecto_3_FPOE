package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.model.ships.*;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameController {

    private Game game;

    private int size;

    @FXML
    private GridPane gridPaneGame;

    @FXML
    private GridPane gridPaneShips;

    @FXML
    private BorderPane gameBorderPane;

    @FXML
    private VBox shipSelectVbox;

    Fragata[] fragatas = new Fragata[4];
    Destructor[] destructores = new Destructor[3];
    Submarino[] submarinos = new Submarino[2];
    Portaaviones[] portaaviones = new Portaaviones[1];


    private double startX, startY;

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

    @FXML
    public void createShips(int[][] shipsSelected) {;
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneShips.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto_3_battlership/Css/css.css")).toExternalForm());

        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.getStyleClass().add("cell");
//                gridPaneShips.add(shipSelected, col - shipSelected.getSize() + 1, row);
//                GridPane.setColumnSpan(shipSelected, shipSelected.getSize());
                gridPaneShips.add(cell, col, rows);
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



    @FXML
    public void onHandleReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }



}
