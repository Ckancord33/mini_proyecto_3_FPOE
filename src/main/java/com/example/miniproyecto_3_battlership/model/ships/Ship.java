package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Ship extends Pane implements Serializable {
    protected Group shapesGroup = new Group();
    protected Rectangle body = new Rectangle();
    protected boolean isSelect;
    protected boolean isHorizontal = true;
    protected int size;
    protected boolean potentialRotate = true;
    protected int[] position = new int[2];
    protected boolean isPlaced = false;
    protected boolean isDestroyed = false;

    public Ship() {
    }

    public void selectDesing() {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.WHITE);
        glow.setRadius(15);
        glow.setSpread(0.5);
        shapesGroup.setEffect(glow);
        isSelect = true;
    }

    public void originDesing() {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.CADETBLUE);
        glow.setRadius(10);
        glow.setSpread(0.4);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0);
        glow.setInput(colorAdjust);
        shapesGroup.setEffect(glow);
        isSelect = false;
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

    public void setIsSelect(boolean isSelect){
        this.isSelect = isSelect;
    }

    public void setScaleShip(double X, double Y){
        shapesGroup.setScaleX(X);
        shapesGroup.setScaleY(Y);
        body.setScaleX(X);
        body.setScaleY(Y);
    }

    public void setIsDestroyed(boolean isDestroyed){
        this.isDestroyed = isDestroyed;
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

}
