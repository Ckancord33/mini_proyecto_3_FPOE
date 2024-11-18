package com.example.miniproyecto_3_battlership.model.game;

import com.example.miniproyecto_3_battlership.model.Player.PlayerBot;
import com.example.miniproyecto_3_battlership.model.Player.PlayerPerson;

import java.io.Serializable;

public class Game implements Serializable {

    public PlayerBot playerBot = new PlayerBot();
    public PlayerPerson playerPerson = new PlayerPerson();

    public PlayerBot getPlayerBot(){
        return playerBot;
    }

    public void setPlayerBot(PlayerBot playerBot){
        this.playerBot = playerBot;
    }

    public PlayerPerson getPlayerPerson(){
        return playerPerson;
    }

    public void gameTurn(){

    }

    public boolean verifyWinner(){
        return false;
    }

    public void checkGeneratedScore(){

    }

}
