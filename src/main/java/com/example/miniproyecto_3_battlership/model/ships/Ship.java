package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Ship extends Pane {
    protected Rectangle body;
    protected boolean isSelect;
    protected boolean isHorizontal = true;
    protected int size;
    protected boolean potentialRotate = true;
    protected int[] position = new int[2];
    protected boolean isPlaced = false;

    public Ship() {
    }

    public void selectDesing() {
    }

    public void originDesing() {
    }

    public void rotateShip() {
        if(isHorizontal) {
            this.setRotate(90);
            isHorizontal = false;
        }else{
            this.setRotate(0);
            isHorizontal = true;
        }
    }

    public void setIsHorizontal(boolean isHorizontal){
        this.isHorizontal = isHorizontal;
    }

    public void setPosition(int x, int y) {
        position[0] = x;
        position[1] = y;
    }

    public int[] getPosition() {
        return position;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public int getSize() {
        return size;
    }

    public boolean isPlaced(){
        return isPlaced;
    }

    public void setIsPlaced(boolean isPlaced){
        this.isPlaced = isPlaced;
    }

    public boolean isHorizontal(){
        return isHorizontal;
    }

    public void setPotentialRotate(boolean potentialRotate){
        this.potentialRotate = potentialRotate;
    }

    public boolean potentialRotate(){
        return potentialRotate;
    }

}
