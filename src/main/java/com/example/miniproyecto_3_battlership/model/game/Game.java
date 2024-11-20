package com.example.miniproyecto_3_battlership.model.game;

import com.example.miniproyecto_3_battlership.model.Player.IPlayer;
import com.example.miniproyecto_3_battlership.model.Player.PlayerBot;
import com.example.miniproyecto_3_battlership.model.Player.PlayerPerson;
import com.example.miniproyecto_3_battlership.model.ships.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    public PlayerBot playerBot = new PlayerBot();
    public PlayerPerson playerPerson = new PlayerPerson();
    private ArrayList<int[]> shipPositions;

    public void setArraysShips(ArrayList<int[]> ship){
        this.shipPositions = ship;
    }


    public PlayerBot getPlayerBot(){
        return playerBot;
    }

    public void setPlayerBot(PlayerBot playerBot){
        this.playerBot = playerBot;
    }

    public PlayerPerson getPlayerPerson(){
        return playerPerson;
    }

    public boolean verifyWinner(IPlayer player){
        return player.verifyWinner();
    }

    public void checkGeneratedScore(){

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
