package com.example.miniproyecto_3_battlership.model.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PlayerBot extends APlayer implements Serializable {

    int [] positionAttack = new int[2];
    boolean direction = false;
    Stack<int[]> shots = new Stack<>();
    int [] lastShot = new int[2];
    int verifyShot;

    public void generateBotGame(){
        int fragataNumbers = 0;
        int destroyerNumbers = 0;
        int submarineNumbers = 0;
        int airportCarrierNumbers = 0;
        int randomRow;
        int randomCol;
        int direction;
        do{
            randomRow = (int)(Math.random()*9);
            randomCol = (int)(Math.random()*9);
            if(shipsMatrix.get(randomRow).get(randomCol)==0){
                shipsMatrix.get(randomRow).set(randomCol,1);
                fragataNumbers++;
            }
        }while (fragataNumbers < 4);

        do{
            randomRow = (int)(Math.random()*8);
            randomCol = (int)(Math.random()*8);
            direction = (int)(Math.random()*3);
            switch(direction){
                case 0:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow-1).get(randomCol)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow-1).set(randomCol,1);
                        destroyerNumbers++;
                    }
                    break;
                case 1:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol+1)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow).set(randomCol+1,1);
                        destroyerNumbers++;
                    }
                    break;
                case 2:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow+1).get(randomCol)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow+1).set(randomCol,1);
                        destroyerNumbers++;
                    }
                    break;
                case 3:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol-1)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow).set(randomCol-1,1);
                        destroyerNumbers++;
                    }

            }

        }while (destroyerNumbers < 3);

        do{
            randomRow = (int)(Math.random()*8);
            randomCol = (int)(Math.random()*8);
            direction = (int)(Math.random()*3);
            switch(direction){
                case 0:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow-1).get(randomCol)==0 && shipsMatrix.get(randomRow-2).get(randomCol)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow-1).set(randomCol,1);
                        shipsMatrix.get(randomRow-2).set(randomCol,1);
                        submarineNumbers++;
                    }
                    break;
                case 1:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol+1)==0 && shipsMatrix.get(randomRow).get(randomCol+2)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow).set(randomCol+1,1);
                        shipsMatrix.get(randomRow).set(randomCol+2,1);
                        submarineNumbers++;
                    }
                    break;
                case 2:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow+1).get(randomCol)==0 && shipsMatrix.get(randomRow+2).get(randomCol)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow+1).set(randomCol,1);
                        shipsMatrix.get(randomRow+2).set(randomCol,1);
                        submarineNumbers++;
                    }
                    break;
                case 3:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol-1)==0 && shipsMatrix.get(randomRow).get(randomCol-2)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow).set(randomCol-1,1);
                        shipsMatrix.get(randomRow).set(randomCol-2,1);
                        submarineNumbers++;
                    }

            }

        }while (submarineNumbers < 2);

        do{
            randomRow = (int)(Math.random()*8);
            randomCol = (int)(Math.random()*8);
            direction = (int)(Math.random()*3);
            switch(direction){
                case 0:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow-1).get(randomCol)==0 && shipsMatrix.get(randomRow-2).get(randomCol)==0 && shipsMatrix.get(randomRow-3).get(randomCol)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow-1).set(randomCol,1);
                        shipsMatrix.get(randomRow-2).set(randomCol,1);
                        shipsMatrix.get(randomRow-3).set(randomCol,1);
                        airportCarrierNumbers++;
                    }
                    break;
                case 1:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol+1)==0 && shipsMatrix.get(randomRow).get(randomCol+2)==0 && shipsMatrix.get(randomRow).get(randomCol+3)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow).set(randomCol+1,1);
                        shipsMatrix.get(randomRow).set(randomCol+2,1);
                        shipsMatrix.get(randomRow).set(randomCol+3,1);
                        airportCarrierNumbers++;
                    }
                    break;
                case 2:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow+1).get(randomCol)==0 && shipsMatrix.get(randomRow+2).get(randomCol)==0 && shipsMatrix.get(randomRow+3).get(randomCol)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow+1).set(randomCol,1);
                        shipsMatrix.get(randomRow+2).set(randomCol,1);
                        shipsMatrix.get(randomRow+3).set(randomCol,1);
                        airportCarrierNumbers++;
                    }
                    break;
                case 3:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol-1)==0 && shipsMatrix.get(randomRow).get(randomCol-2)==0 && shipsMatrix.get(randomRow).get(randomCol-3)==0){
                        shipsMatrix.get(randomRow).set(randomCol,1);
                        shipsMatrix.get(randomRow).set(randomCol-1,1);
                        shipsMatrix.get(randomRow).set(randomCol-2,1);
                        shipsMatrix.get(randomRow).set(randomCol-3,1);
                        airportCarrierNumbers++;
                    }

            }

        }while (airportCarrierNumbers < 1);


    };

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



    public void clearMatrix(){
        for (ArrayList<Integer> fila : shipsMatrix) {
            Collections.fill(fila, 0);
        }
        Integer[][] shipsPositionMatrixBot = new Integer[10][10];
    }
}
