package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Ship extends Pane {
    protected Rectangle body;
    protected boolean isSelect;
    protected boolean isHorizontal;
    protected int size;

    public Ship() {
    }

    public void selectDesing() {
    }

    public void originDesing() {
    }

    public void rotateShip() {
        isHorizontal = !isHorizontal;
        this.setRotate(90);
    }

    public boolean isSelect() {
        return isSelect;
    }

    public int getSize() {
        return size;
    }

}
