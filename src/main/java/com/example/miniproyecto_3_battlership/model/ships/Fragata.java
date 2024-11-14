package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Fragata extends Ship {

    public Fragata() {
        this.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-border-style: solid;");
        body = new Rectangle(0, 0, 63.5, 63.5);
        body.setFill(javafx.scene.paint.Color.DARKGRAY);
        this.getChildren().add(body);
        isSelect = false;
        size = 1;
    }

    public Fragata(boolean b) {
        this.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-border-style: solid;");
        body = new Rectangle(0, 0, 63.5, 63.5);
        body.setFill(javafx.scene.paint.Color.DARKGRAY);
        this.getChildren().add(body);
        isSelect = false;
        size = 1;
    }

    public void selectDesing() {
        body.setFill(javafx.scene.paint.Color.BLACK);
        isSelect = true;
    }

    public void originDesing() {
        body.setFill(javafx.scene.paint.Color.DARKGRAY);
        isSelect = false;
    }
}
