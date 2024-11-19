package com.example.miniproyecto_3_battlership.model.ships;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;

import java.util.Collection;
import java.util.Objects;

public class Submarino extends Ship {

    public Submarino() {
        this.setWidth(190);
        this.setHeight(63);
        size =  3;
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(90); // Posición X del centro
        ellipse.setCenterY(25); // Posición Y del centro
        ellipse.setRadiusX(60); // Radio horizontal
        ellipse.setRadiusY(25);  // Radio vertical
        ellipse.setFill(javafx.scene.paint.Color.GRAY);

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/texture2.png")));
        ImagePattern imagePattern = new ImagePattern(image);
        ellipse.setFill(imagePattern);
        ellipse.setStroke(Color.BLACK);
        Polygon polygon = new Polygon();








        DropShadow glow = new DropShadow();
        glow.setColor(Color.CADETBLUE);
        glow.setRadius(10);
        glow.setSpread(0.3);
        shapesGroup.setEffect(glow);
        shapesGroup.setStyle("-fx-cursor: hand");
        shapesGroup.setLayoutX(10);
        shapesGroup.setLayoutY(2);
        shapesGroup.setScaleX(1.1);
        shapesGroup.setScaleY(1.1);

        shapesGroup.getChildren().addAll(ellipse);

        shapesGroup.setScaleX(1.0);
        shapesGroup.setScaleY(1.0);
        this.getChildren().add(shapesGroup);
        isSelect = false;
        size = 3;
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
