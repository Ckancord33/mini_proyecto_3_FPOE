package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.layout.StackPane;

import java.awt.*;

public interface Ship{
    void rotateShip();
    StackPane getShip();
    void selectDesing();
    void originDesing();
    boolean getOrientation();
}
