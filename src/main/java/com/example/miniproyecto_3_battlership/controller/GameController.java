package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    private Game game;

    @FXML
    private CheckBox check1;

    @FXML
    private CheckBox check2;

    @FXML
    private CheckBox check3;

    @FXML
    private CheckBox check4;

    private int size;

    @FXML
    private GridPane gridPaneGame;

    @FXML
    private GridPane gridPaneShips;

    @FXML
    private BorderPane gameBorderPane;

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

//        gameBorderPane.setBackground(new Background(background));

        createShips();
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


//    public void eventStartMovement(Rectangle rectangle) {
//        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                rectangle.setX(event.getSceneX() - startX);
//                rectangle.setY(event.getSceneY() - startY);
//            }
//        });
//
//        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                double finalX = event.getSceneX();
//                double finalY = event.getSceneY();
//                System.out.println(finalX + " " + finalY + "me soltaste");
//            }
//        });
//
//        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                startX = event.getSceneX() - rectangle.getX();
//                startY = event.getSceneY() - rectangle.getY();
//            }
//        });
//    }


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
    void createShips() {;
        double cellWidth = 41.9;
        double cellHeight = 45.6;


        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                int finalRows = rows;
                int finalCol = col;
                cell.setOnMouseEntered(e -> onHandleMouseEnteredShips(e, cell, finalRows, finalCol));
                cell.setOnMouseExited(e -> onHandleMouseExitedShips(e, cell, finalRows, finalCol));

                gridPaneShips.add(cell, col, rows);
            }
        }
    }

    private void onHandleMouseEnteredShips(MouseEvent e, Rectangle cell, int finalRows, int finalCol) {
        Color colorhover = Color.rgb(0,0,0,0.3 );
        if (size == 1){
            cell.setFill(colorhover);
        }else if(size == 2){
            cell.setFill(colorhover);
            for (var node : gridPaneShips.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);
                rowIndex = (rowIndex == null) ? 0 : rowIndex;
                colIndex = (colIndex == null) ? 0 : colIndex;

                if (rowIndex == finalRows && colIndex == finalCol-1 && node instanceof Rectangle cell2) {
                    cell2.setFill(colorhover);
                }
            }
        }else if(size == 3){
            cell.setFill(colorhover);
            for (var node : gridPaneShips.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);
                rowIndex = (rowIndex == null) ? 0 : rowIndex;
                colIndex = (colIndex == null) ? 0 : colIndex;

                if (rowIndex == finalRows && colIndex == finalCol-1 && node instanceof Rectangle cell2) {
                    cell2.setFill(colorhover);
                }
                if (rowIndex == finalRows && colIndex == finalCol+1 && node instanceof Rectangle cell3){
                    cell3.setFill(colorhover);
                }
            }
        } else if (size == 4){
            cell.setFill(colorhover);
            for (var node : gridPaneShips.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);
                rowIndex = (rowIndex == null) ? 0 : rowIndex;
                colIndex = (colIndex == null) ? 0 : colIndex;

                if (rowIndex == finalRows && colIndex == finalCol-1 && node instanceof Rectangle cell2) {
                    cell2.setFill(colorhover);
                }
                if (rowIndex == finalRows && colIndex == finalCol+1 && node instanceof Rectangle cell3){
                    cell3.setFill(colorhover);
                }
                if (rowIndex == finalRows && colIndex == finalCol-2 && node instanceof Rectangle cell4){
                    cell4.setFill(colorhover);
                }

            }
        }


    }

    private void onHandleMouseExitedShips(MouseEvent e, Rectangle cell, int finalRows, int finalCol) {
        Color colorDefault = Color.TRANSPARENT;
        if (size == 1){
            cell.setFill(colorDefault);
        }else if(size == 2){
            cell.setFill(colorDefault);
            for (var node : gridPaneShips.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);
                rowIndex = (rowIndex == null) ? 0 : rowIndex;
                colIndex = (colIndex == null) ? 0 : colIndex;

                if (rowIndex == finalRows && colIndex == finalCol-1 && node instanceof Rectangle cell2) {
                    cell2.setFill(colorDefault);
                }
            }
        }else if(size == 3){
            cell.setFill(colorDefault);
            for (var node : gridPaneShips.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);
                rowIndex = (rowIndex == null) ? 0 : rowIndex;
                colIndex = (colIndex == null) ? 0 : colIndex;

                if (rowIndex == finalRows && colIndex == finalCol-1 && node instanceof Rectangle cell2) {
                    cell2.setFill(colorDefault);
                }
                if (rowIndex == finalRows && colIndex == finalCol+1 && node instanceof Rectangle cell3){
                    cell3.setFill(colorDefault);
                }
            }
        }else if (size == 4){
            cell.setFill(colorDefault);
            for (var node : gridPaneShips.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);
                rowIndex = (rowIndex == null) ? 0 : rowIndex;
                colIndex = (colIndex == null) ? 0 : colIndex;

                if (rowIndex == finalRows && colIndex == finalCol-1 && node instanceof Rectangle cell2) {
                    cell2.setFill(colorDefault);
                }
                if (rowIndex == finalRows && colIndex == finalCol+1 && node instanceof Rectangle cell3){
                    cell3.setFill(colorDefault);
                }
                if (rowIndex == finalRows && colIndex == finalCol-2 && node instanceof Rectangle cell4){
                    cell4.setFill(colorDefault);
                }

            }
        }
    }

    @FXML
    public void onHandleReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
        game.setMatrix();
    }

    public void checkSelection(javafx.event.ActionEvent actionEvent) {
        if(check1.isSelected()) {
            check2.setSelected(false);
            check3.setSelected(false);
            check4.setSelected(false);
            size = 1;
        }else if(check2.isSelected()) {
            check1.setSelected(false);
            check3.setSelected(false);
            check4.setSelected(false);
            size = 2;
        }else if(check3.isSelected()) {
            check1.setSelected(false);
            check2.setSelected(false);
            check4.setSelected(false);
            size = 3;
        }else if(check4.isSelected()) {
            check1.setSelected(false);
            check2.setSelected(false);
            check3.setSelected(false);
            size = 4;
        }
    }
}
