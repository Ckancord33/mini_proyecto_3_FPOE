package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.model.ships.*;
import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.ActionEvent;
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

    private Ship[] shipsSelect = new Ship[4];
    private Ship[] shipGame = new Ship[4];
    private int[] shipsNumbers = {4,3,2,1};

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

        createShips();createBorders();
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

        vBoxSelectShips();

    }

    public void vBoxSelectShips(){
        Fragata fragata = new Fragata();
        shipsSelect[0] = fragata;
        Destructor destructor = new Destructor();
        shipsSelect[1] = destructor;
        Submarino submarino = new Submarino();
        shipsSelect[2] = submarino;
        Portaaviones portaaviones = new Portaaviones();
        shipsSelect[3] = portaaviones;

        shipSelectVbox.getChildren().addAll(fragata.getFragata(), destructor.getDestructor(), submarino.getSubmarino(), portaaviones.getPortaaviones());
        shipSelectVbox.setSpacing(9);
        fragata.getFragata().setOnMouseClicked(e -> shipSelect(1));
        destructor.getDestructor().setOnMouseClicked(e -> shipSelect(2));
        submarino.getSubmarino().setOnMouseClicked(e -> shipSelect(3));
        portaaviones.getPortaaviones().setOnMouseClicked(e -> shipSelect(4));

    }

    public void shipSelect(int type){

        if(type == 1){
            restarColorShips();
            shipsSelect[0].selectDesing();
            size = 1;
        }else if(type == 2){
            restarColorShips();
            size = 2;
            shipsSelect[1].selectDesing();
        }else if(type == 3){
            restarColorShips();
            size = 3;
            shipsSelect[2].selectDesing();
        }else if(type == 4){
            restarColorShips();
            size = 4;
            shipsSelect[3].selectDesing();
        }
//        gridPaneShips.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
//            double x = event.getSceneX();
//            double y = event.getSceneY();
//            System.out.println("Cursor clicked at: (" + x + ", " + y + ")");
//
//            double cellWidth = gridPaneShips.getWidth() / gridPaneShips.getColumnCount();
//            double cellHeight = gridPaneShips.getHeight() / gridPaneShips.getRowCount();
//
//            int column = (int) (x / cellWidth);
//            int row = (int) (y / cellHeight);
//            System.out.println("Clic en la celda: columna " + column + ", fila " + row);
//            gridPaneShips.add(ships[type-1].getShip(), column, row);
//
//        });

    }


    public void restarColorShips(){
        for (Ship ship : shipsSelect) {
            ship.originDesing();
        }
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
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneShips.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto_3_battlership/Css/css.css")).toExternalForm());

        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.getStyleClass().add("cell");
                int finalRows = rows;
                int finalCol = col;
                cell.setOnMouseEntered(e -> onHandleMouseEnteredShips(e, cell, finalRows, finalCol));
                cell.setOnMouseExited(e -> onHandleMouseExitedShips(e, cell, finalRows, finalCol));
                gridPaneShips.add(cell, col, rows);
            }
        }
    }

    @FXML
    void onHandleGridPaneShips(MouseEvent event) {
        double cellWidth = gridPaneShips.getWidth() / gridPaneShips.getColumnCount();
        double cellHeight = gridPaneShips.getHeight() / gridPaneShips.getRowCount();

        double x = event.getX();
        double y = event.getY();

        int column = (int) (x / cellWidth);
        int row = (int) (y / cellHeight);

        shipGame[0] = new Fragata(true);
        shipGame[1] = new Destructor(true);
        shipGame[2] = new Submarino(true);
        shipGame[3] = new Portaaviones(true);


        if (row != 0 && column != 0){
            System.out.println("Clic en la celda: columna " + column + ", fila " + row);
            if (size == 1 && shipsNumbers[0] != 0){
                gridPaneShips.add(shipGame[0].getShip(), column, row);
                shipsNumbers[0]--;
            }else if(size == 2 && shipsNumbers[1] != 0 && column != 1){
                GridPane.setColumnSpan(shipGame[1].getShip(), 2);
                gridPaneShips.add(shipGame[1].getShip(), column-1, row);
                shipsNumbers[1]--;
            }else if(size == 3 && shipsNumbers[2] != 0 && column != 1 && column != 10){
                GridPane.setColumnSpan(shipGame[2].getShip(), 3);
                gridPaneShips.add(shipGame[2].getShip(), column-1, row);
                shipsNumbers[2]--;
            }else if(size == 4 && shipsNumbers[3] != 0 && column > 2 && column != 10){
                GridPane.setColumnSpan(shipGame[3].getShip(), 4);
                gridPaneShips.add(shipGame[3].getShip(), column-2, row);
                shipsNumbers[3]--;
            }
        }


    }



    private void onHandleMouseEnteredShips(MouseEvent e, Rectangle cell, int finalRows, int finalCol) {
        Color colorhover = Color.rgb(0,0,0,0.5 );
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

    void createBorders() {
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
        game.setMatrix();
    }


}
