package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Submarino implements Ship {

    StackPane submarino = new StackPane();
    private Rectangle body;
    private boolean isHorizontal = true;

    public Submarino() {
        // Cuerpo del barco
        body = new Rectangle(31.8, 31.8);
        body.setFill(Color.DARKGRAY);


        // Agregar todas las partes al Pane
        this.submarino.getChildren().addAll(body);
    }

    public Submarino(boolean b) {
        // Cuerpo del barco
        body = new Rectangle(63.63*3, 63.63);
        body.setFill(Color.YELLOW);


        // Agregar todas las partes al Pane
        this.submarino.getChildren().addAll(body);
        isHorizontal = b;
    }



    public StackPane getSubmarino() {
        return submarino;
    }

    public void selectDesing(){
        body.setFill(Color.BLACK);
    }

    public void originDesing(){
        body.setFill(Color.DARKGRAY);
    }

    @Override
    public boolean getOrientation() {
        return false;
    }


    @Override
    public void rotateShip() {
        isHorizontal = !isHorizontal;
        submarino.setRotate(90);
    }

    @Override
    public StackPane getShip() {
        return submarino;
    }
}
