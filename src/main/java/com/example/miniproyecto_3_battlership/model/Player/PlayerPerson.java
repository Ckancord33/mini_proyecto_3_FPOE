package com.example.miniproyecto_3_battlership.model.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerPerson extends APlayer implements Serializable {


    @Override
    public void setChosenMatrix(int[][] matrix){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                shipsMatrix.get(i).set(j, matrix[i][j]);
            }
        }
    }


}
