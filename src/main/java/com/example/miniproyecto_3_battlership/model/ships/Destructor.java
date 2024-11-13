package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Destructor implements Ship {

    StackPane Destructor = new StackPane();
    private Rectangle body;
    private boolean isHorizontal;

    public Destructor() {
        // Cuerpo del barco
        body = new Rectangle(31.8, 31.8);
        body.setFill(Color.DARKGRAY);


        // Agregar todas las partes al Pane
        this.Destructor.getChildren().addAll(body);
    }
    public Destructor(boolean b) {
        body = new Rectangle(63.63*2,63.63);
        body.setFill(Color.BLUE);


        // Agregar todas las partes al Pane
        this.Destructor.getChildren().addAll(body);
        isHorizontal = b;
    }

    public StackPane getDestructor() {
        return Destructor;
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
        Destructor.setRotate(90);

    }

    @Override
    public StackPane getShip() {
        return Destructor;
    }
}
