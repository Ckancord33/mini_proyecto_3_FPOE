package com.example.miniproyecto_3_battlership.model.Player;

import java.util.ArrayList;

public abstract class APlayer implements IPlayer {

    protected final ArrayList<ArrayList<Integer>> shipsMatrix = new ArrayList<>();


    @Override
    public void setMatrix() {
        int filas = 10;
        int columnas = 10;
        for (int i = 0; i < filas; i++) {
            ArrayList<Integer> fila = new ArrayList<>();
            for (int j = 0; j < columnas; j++) {
                fila.add(0);
            }
            this.shipsMatrix.add(fila);
        }
    }

    @Override
    public ArrayList<ArrayList<Integer>> getMatrix(){
      return shipsMatrix;
    }

    @Override
    public void clearMatrix(){

    }

    @Override
    public void changeMatrix(int row, int col,int value){
        shipsMatrix.get(row).set(col, value);
    }

    @Override
    public void setChosenMatrix(int[][] matrix){

    }

    @Override
    public void generateBotGame(){

    }

    @Override
    public void botIntelligence(ArrayList<ArrayList<Integer>> matrix){

    }




    public void showMatrix(){
        for (int i=0;i<10;i++){
            for (int j= 0;j<10;j++){
                System.out.print(shipsMatrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public boolean verifyWinner(){
        int counter = 0;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (shipsMatrix.get(i).get(j) == -1){
                    counter++;
                }
            }
        }
        return counter == 20;
    }


}
