package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Submarino extends Ship {

    public Submarino() {
        this.setWidth(190);
        this.setHeight(63);

        this.setStyle("-fx-border-color: black;" +
            "-fx-border-width: 1;" +
            "-fx-border-style: solid;"
        );
        size =  3;
        body = new Rectangle(0, 0, 190, 63);
        body.setFill(javafx.scene.paint.Color.DARKGRAY);
        body.setStroke(Color.TRANSPARENT);
        this.getChildren().add(body);
        isSelect = false;
        size = 3;
        isHorizontal = true;
    }

    public Submarino(boolean b) {
        this.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-border-style: solid;" +
                "-fx-padding: 0;"
        );
        body = new Rectangle(0, 0, 190, 63.5);
        body.setFill(javafx.scene.paint.Color.DARKGRAY);
        this.getChildren().add(body);
        isSelect = false;
        size = 3;
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
