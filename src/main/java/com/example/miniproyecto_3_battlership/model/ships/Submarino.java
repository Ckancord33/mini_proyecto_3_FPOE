package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Submarino extends Ship {

    public Submarino() {
        double scaleX = 63.0 / 88.0;
        double scaleY = 189.0 / 260.0;

        double centerX = 63.0 / 2;
        double centerY = 189.0 / 2;

        Ellipse ellipse = new Ellipse(35.0 * scaleX, 130.0 * scaleY);
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/texture5.png")));
        ImagePattern imagePattern = new ImagePattern(image1);
        ellipse.setFill(imagePattern);

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(0.0);
        ellipse.setLayoutX(centerX + (70.0 - 88.0 / 2) * scaleX);
        ellipse.setLayoutY(centerY + (136.0 - 260.0 / 2) * scaleY);

        Polygon polygon1 = new Polygon(
                95.0 * scaleX, 23.0 * scaleY,
                122.0 * scaleX, 11.0 * scaleY,
                104.0 * scaleX, -23.0 * scaleY
        );
        polygon1.setFill(Color.DARKGRAY);
        polygon1.setStroke(Color.BLACK);
        polygon1.setStrokeWidth(0.0);
        polygon1.setLayoutX(centerX + (-62.0 - 88.0 / 2) * scaleX + 2);
        polygon1.setLayoutY(centerY + (255.0 - 260.0 / 2) * scaleY);

        Polygon polygon2 = new Polygon(
                131.0 * scaleX, 14.0 * scaleY,
                106.0 * scaleX, 4.0 * scaleY,
                122.0 * scaleX, -31.0 * scaleY
        );
        polygon2.setFill(Color.DARKGRAY);
        polygon2.setStroke(Color.BLACK);
        polygon2.setStrokeWidth(0.0);
        polygon2.setLayoutX(centerX + (-26.0 - 88.0 / 2) * scaleX - 2);
        polygon2.setLayoutY(centerY + (263.0 - 260.0 / 2) * scaleY);

        Polygon polygon3 = new Polygon(
                84.0 * scaleX, 21.0 * scaleY,
                108.0 * scaleX, 15.0 * scaleY,
                108.0 * scaleX, -32.0 * scaleY
        );
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/textureFragata1.png")));
        ImagePattern imagePatter2  = new ImagePattern(image2);
        ellipse.setFill(imagePatter2);
        polygon3.setStroke(Color.BLACK);
        polygon3.setStrokeWidth(0.0);
        polygon3.setLayoutX(centerX + (-80.0 - 88.0 / 2) * scaleX +5);
        polygon3.setLayoutY(centerY + (130.0 - 260.0 / 2) * scaleY);

        Polygon polygon4 = new Polygon(
                95.0 * scaleX, 23.0 * scaleY,
                122.0 * scaleX, 29.0 * scaleY,
                95.0 * scaleX, -26.0 * scaleY
        );
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/textureFragata1.png")));
        ImagePattern imagePatter3  = new ImagePattern(image3);
        ellipse.setFill(imagePatter3);
        polygon4.setStrokeWidth(0.0);
        polygon4.setLayoutX(centerX + (16.0 - 88.0 / 2) * scaleX - 5);
        polygon4.setLayoutY(centerY + (120.0 - 260.0 / 2) * scaleY);

        QuadCurve quadCurve = new QuadCurve(
                -28.0 * scaleX, -4.0 * scaleY,
                -10.0 * scaleX, -54.0 * scaleY,
                10.0 * scaleX, -4.0 * scaleY
        );
        quadCurve.setFill(Color.web("#5a5a5b"));
        quadCurve.setStroke(Color.BLACK);
        quadCurve.setStrokeWidth(0.0);
        quadCurve.setLayoutX(centerX + (78.0 - 88.0 / 2) * scaleX);
        quadCurve.setLayoutY(centerY + (278.0 - 260.0 / 2) * scaleY);


        shapesGroup = new Group(ellipse, polygon1, polygon2, polygon3, polygon4, quadCurve);
        shapesGroup.setScaleX(0.85);
        shapesGroup.setScaleY(0.85);
        shapesGroup.setRotate(90);
        shapesGroup.setLayoutX(45);
        shapesGroup.setLayoutY(-70);
        this.getChildren().add(shapesGroup);
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
}
