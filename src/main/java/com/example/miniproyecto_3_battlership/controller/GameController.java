package com.example.miniproyecto_3_battlership.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class GameController {

    @FXML
    private Rectangle rectangle;
    private double startX, startY;

    @FXML
    private GridPane gridPane;

    @FXML
    void onHandleMouseDragged(MouseEvent event) {
        rectangle.setX(event.getSceneX() - startX);
        rectangle.setY(event.getSceneY() - startY);
    }

    @FXML
    void setOnMouseRealeased(MouseEvent event) {
        double finalX = event.getSceneX();
        double finalY = event.getSceneY();
        System.out.println(finalX + " " + finalY + "me soltaste");
    }

    @FXML
    void setOnMousePressed(MouseEvent event) {
        startX = event.getSceneX() - rectangle.getX();
        startY = event.getSceneY() - rectangle.getY();
    }


    @FXML
    void onDragDetected(MouseEvent event) {
        System.out.println("Drag Detected");
    }


}
