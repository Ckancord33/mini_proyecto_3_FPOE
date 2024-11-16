package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Portaaviones extends Ship {
    public Portaaviones() {
        this.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-border-style: solid;");
        body = new Rectangle(0, 0, 253, 63.5);
        body.setFill(javafx.scene.paint.Color.DARKGRAY);
        body.setStroke(Color.TRANSPARENT);
        this.getChildren().add(body);
        isSelect = false;
        size = 4;
    }

    public Portaaviones(boolean b) {
        this.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-border-style: solid;");
        body = new Rectangle(0, 0, 253, 63.5);
        body.setFill(javafx.scene.paint.Color.DARKGRAY);
        this.getChildren().add(body);
        isSelect = false;
        size = 4;
    }



    public void selectDesing() {
        body.setFill(javafx.scene.paint.Color.BLACK);
        body.setStroke(Color.TRANSPARENT);
        isSelect = true;
    }

    public void originDesing() {
        body.setFill(javafx.scene.paint.Color.DARKGRAY);
        body.setStroke(Color.TRANSPARENT);
        isSelect = false;
    }
}
