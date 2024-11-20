package com.example.miniproyecto_3_battlership.model.Player;

import com.example.miniproyecto_3_battlership.model.exeption.CrossedShipsException;
import com.example.miniproyecto_3_battlership.model.ships.*;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayerBot extends APlayer implements Serializable {

    int [] positionAttack = new int[2];
    boolean direction = false;
    Stack<int[]> shots = new Stack<>();
    int [] lastShot = new int[2];
    int verifyShot;
    private ArrayList<int[]> enemyShipsInfo = new ArrayList<>(); // [0] = x, [1] = y, [2] = size, [3] = orientation


    public void generateBotGame(){
        for (int i = 0; i < 4; i++) {
            enemyShipsInfo.add(new int[]{0,0,1,0});
        }

        for (int i = 0; i < 3; i++) {
            enemyShipsInfo.add(new int[]{0,0,2,0});
        }

        for (int i = 0; i < 2; i++) {
            enemyShipsInfo.add(new int[]{0,0,3,0});
        }

        for (int i = 0; i < 1; i++) {
            enemyShipsInfo.add(new int[]{0,0,4,0});
        }

        int row, col, randomHorientation;
        boolean tryAgain;
        for (int i = 0; i < enemyShipsInfo.size(); i++) {
            do {
                tryAgain = false;
                row = (int) (Math.random() * 9);
                col = (int) (Math.random() * 9);
                Random random = new Random();
                randomHorientation = random.nextInt(2);
                enemyShipsInfo.get(i)[0] = row;
                enemyShipsInfo.get(i)[1] = col;
                enemyShipsInfo.get(i)[3] = randomHorientation;
                for (int j = 0; j < enemyShipsInfo.get(i)[2]; j++) {
                    try {
                        if (randomHorientation == 1) {
                            if (shipsMatrix.get(row).get(col - j) != 0) {
                                tryAgain = true;
                            }
                        } else {
                            if (shipsMatrix.get(row - j).get(col) != 0) {
                                tryAgain = true;
                            }
                        }
                    }catch (IndexOutOfBoundsException e){
                        tryAgain = true;
                    }
                }
            } while (tryAgain);
            for (int j = 0; j < enemyShipsInfo.get(i)[2]; j++) {
                if(enemyShipsInfo.get(i)[3] == 1){
                    shipsMatrix.get(row).set(col - j, 1);
                } else {
                    shipsMatrix.get(row - j).set(col, 1);
                }
            }
        }
    };

    public int[] botIntelligence(ArrayList<ArrayList<Integer>> matrix){
       int[]  dpos= new int[2];
       dpos[0]=(int)(Math.random()*9);
       dpos[1]=(int)(Math.random()*9);
        int[] pos = new int[2];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
               // if(shipsMatrix.get(i).get(j)==)
                if(shipsMatrix.get(i).get(j) ==1){
                    System.out.println("halle un barco");
                    pos[0]=i+1;
                    pos[1]=j;
                    return pos;
                }
    public void botIntelligence(ArrayList<ArrayList<Integer>> matrix){
         lastShot = shots.peek();
         verifyShot = matrix.get(lastShot[0]).get(lastShot[1]);
         System.out.print(verifyShot);
         if(verifyShot == -1){
             System.out.printf("Le pegue");
             positionAttack[0]= lastShot[0];
             positionAttack[1]= lastShot[1]-1;
         }else{
             generatePositionRandom(matrix);
         }
    }

    public void generatePositionRandom(ArrayList<ArrayList<Integer>> matrix){
        int actualPosition;
        do{
            positionAttack[0]=(int)(Math.random()*9 +1);
            positionAttack[1]=(int)(Math.random()*9 +1);
            actualPosition = matrix.get(positionAttack[0]).get(positionAttack[1]);
            System.out.println(actualPosition);
        }while (actualPosition == 2 || actualPosition == -1);
    }

    public int[] getPositionRandom(){
        return positionAttack;
    }

    public Stack<int[]> getShots(){
        return shots;
    }

    public void addShots(int rowBot, int ColumnBot){
        int[] positionBot = {rowBot,ColumnBot};
        shots.push(positionBot);
    }

    public ArrayList<Ship> getEnemyShips(){
        ArrayList<Ship> ships = new ArrayList<>();
        for(int i = 0; i < enemyShipsInfo.size(); i++) {
            int[] shipInfo = this.enemyShipsInfo.get(i);
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



    public void clearMatrix(){
        for (ArrayList<Integer> fila : shipsMatrix) {
            Collections.fill(fila, 0);
        }
        Integer[][] shipsPositionMatrixBot = new Integer[10][10];
    }
}
