package com.example.miniproyecto_3_battlership.model.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerPerson extends APlayer implements Serializable {
    private final ArrayList<ArrayList<Integer>> selectedShipsMatrix = new ArrayList<>();
    @Override
    public void changeValuesMatrix(int[][] matrix){

    }

    @Override
    public void setChosenMatrix(int[][] matrix){

    }

}
