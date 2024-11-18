package com.example.miniproyecto_3_battlership.model.serializable;

import com.example.miniproyecto_3_battlership.model.game.Game;
import com.example.miniproyecto_3_battlership.model.ships.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {
    private ArrayList<int[]> shipPositions;
    private Game game;

    public Save(ArrayList<int[]> ship, Game game) {
        this.shipPositions = ship;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<Ship> getShip() {
        ArrayList<Ship> ships = new ArrayList<>();
        for(int i = 0; i < shipPositions.size(); i++) {
            int[] shipInfo = this.shipPositions.get(i);
            int row = shipInfo[0];
            int col = shipInfo[1];
            int size = shipInfo[2];
            int isHorizontal = shipInfo[3];

            Ship shipSelected = new Ship();

            if (size == 1) {
                shipSelected = new Fragata();
            }
            if (size == 2) {
                shipSelected = new Destructor();
            }
            if (size == 3) {
                shipSelected = new Submarino();
            }
            if (size == 4) {
                shipSelected = new Portaaviones();
            }
            if (isHorizontal != 1) {
                shipSelected.rotateShip();
            }
            shipSelected.setPosition(row, col);
            ships.add(shipSelected);
        }
        return ships;
    }

}
