package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Fragata implements Ship {

    StackPane fragata = new StackPane();
    private Rectangle body;

    public Fragata() {
        // Cuerpo del barco
        body = new Rectangle(31.8, 31.8);
        body.setFill(Color.DARKGRAY);


        // Agregar todas las partes al Pane
        this.fragata.getChildren().addAll(body);
    }

    public Fragata(boolean b) {
        body = new Rectangle(63.63,63.63);
        body.setFill(Color.DARKGRAY);


        // Agregar todas las partes al Pane
        this.fragata.getChildren().addAll(body);
    }

    public StackPane getFragata() {
        return fragata;
    }

    public void selectDesing(){
        body.setFill(Color.BLACK);
    }

    public void originDesing(){
        body.setFill(Color.DARKGRAY);
    }

    @Override
    public void rotateShip() {

    }

    @Override
    public StackPane getShip() {
        return fragata;
    }
}
