package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Destructor extends Ship {

    public Destructor() {
        this.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-border-style: solid;");
        body = new Rectangle(0, 0, 127, 63.5);
        body.setFill(javafx.scene.paint.Color.DARKGRAY);
        body.setStroke(Color.TRANSPARENT);
        this.getChildren().add(body);
        isSelect = false;
        size = 2;
    }

    public Destructor(boolean b) {
        this.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-border-style: solid;");
        body = new Rectangle(0, 0, 127, 63.5);
        body.setFill(javafx.scene.paint.Color.DARKGRAY);
        this.getChildren().add(body);
        isSelect = false;
        size = 2;
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
