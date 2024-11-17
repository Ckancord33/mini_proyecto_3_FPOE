package com.example.miniproyecto_3_battlership.model.Player;

import java.util.ArrayList;

public interface IPlayer {
    void setMatrix();
    ArrayList<ArrayList<Integer>> getMatrix();
    void clearMatrix();
    void changeValuesMatrix(int[][] matrix);

    void setChosenMatrix(int[][] matrix);
    void generateBotGame();
    void botIntelligence();

    void gameTurnPlayer();
}
