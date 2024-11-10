package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    @FXML
    private Rectangle fragataShip1;

    @FXML
    private Rectangle fragataShip2;

    @FXML
    private Rectangle fragataShip3;

    @FXML
    private Rectangle fragataShip4;

    @FXML
    private Rectangle destructorShip1;

    @FXML
    private Rectangle destructorShip2;

    @FXML
    private Rectangle destructorShip3;

    @FXML
    private Rectangle submarino1;

    @FXML
    private Rectangle submarino2;

    @FXML
    private Rectangle portaaviones;

    @FXML
    private GridPane gridPane;

    @FXML
    private HBox hBox;

    private Game game;

    @FXML
    private GridPane gridPaneGame;

    private double startX, startY;

    private ArrayList<ArrayList<Integer>> matriz;


    public void initialize() {
        eventStartMovement(fragataShip1);
        eventStartMovement(fragataShip2);
        eventStartMovement(fragataShip3);
        eventStartMovement(fragataShip4);
        eventStartMovement(destructorShip1);
        eventStartMovement(destructorShip2);
        eventStartMovement(destructorShip3);
        eventStartMovement(submarino1);
        eventStartMovement(submarino2);
        eventStartMovement(portaaviones);

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


        matriz = game.getMatriz();

        for (ArrayList<Integer> fila : matriz) {
            for (Integer valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }

        System.out.println(" ");


    }

    public void eventStartMovement(Rectangle rectangle) {
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setX(event.getSceneX() - startX);
                rectangle.setY(event.getSceneY() - startY);
            }
        });

        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double finalX = event.getSceneX();
                double finalY = event.getSceneY();
                System.out.println(finalX + " " + finalY + "me soltaste");
            }
        });

        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startX = event.getSceneX() - rectangle.getX();
                startY = event.getSceneY() - rectangle.getY();
            }
        });
    }

    @FXML
    void onDragDetected(MouseEvent event) {
        System.out.println("Drag Detected");
    }

    @FXML
    void onHandleMousePressedGame(MouseEvent event) {
        double cellWidth = gridPaneGame.getWidth() / gridPaneGame.getColumnCount();
        double cellHeight = gridPaneGame.getHeight() / gridPaneGame.getRowCount();

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
    public void onHandleReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
        game.setMatrix();
    }
}
