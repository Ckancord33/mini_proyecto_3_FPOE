package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Portaaviones implements Ship {

    StackPane Portaaviones = new StackPane();
    private Rectangle body;
    private boolean isHorizontal;

    public Portaaviones() {
        // Cuerpo del barco
        body = new Rectangle(31.8, 31.8);
        body.setFill(Color.DARKGRAY);


        // Agregar todas las partes al Pane
        this.Portaaviones.getChildren().addAll(body);
    }

    public Portaaviones(boolean b) {
        body = new Rectangle(63.63*4, 63.63);
        body.setFill(Color.VIOLET);
        this.Portaaviones.getChildren().addAll(body);
        isHorizontal = b;
    }

    public StackPane getPortaaviones() {
        return Portaaviones;
    }

    public void selectDesing(){
        body.setFill(Color.BLACK);
    }

    public void originDesing(){
        body.setFill(Color.DARKGRAY);
    }

    @Override
    public boolean getOrientation() {
        return isHorizontal;
    }

    @Override
    public void rotateShip() {
        isHorizontal = !isHorizontal;
        Portaaviones.setRotate(90);
    }

    @Override
    public StackPane getShip() {
        return Portaaviones;
    }
}
