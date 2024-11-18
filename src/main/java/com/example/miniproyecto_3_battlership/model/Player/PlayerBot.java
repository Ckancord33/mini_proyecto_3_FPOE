package com.example.miniproyecto_3_battlership.model.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class PlayerBot extends APlayer implements Serializable {


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
                        shipsMatrix.get(randomRow).set(randomCol,2);
                        shipsMatrix.get(randomRow-1).set(randomCol,2);
                        destroyerNumbers++;
                    }
                    break;
                case 1:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol+1)==0){
                        shipsMatrix.get(randomRow).set(randomCol,2);
                        shipsMatrix.get(randomRow).set(randomCol+1,2);
                        destroyerNumbers++;
                    }
                    break;
                case 2:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow+1).get(randomCol)==0){
                        shipsMatrix.get(randomRow).set(randomCol,2);
                        shipsMatrix.get(randomRow+1).set(randomCol,2);
                        destroyerNumbers++;
                    }
                    break;
                case 3:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol-1)==0){
                        shipsMatrix.get(randomRow).set(randomCol,2);
                        shipsMatrix.get(randomRow).set(randomCol-1,2);
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
                        shipsMatrix.get(randomRow).set(randomCol,3);
                        shipsMatrix.get(randomRow-1).set(randomCol,3);
                        shipsMatrix.get(randomRow-2).set(randomCol,3);
                        submarineNumbers++;
                    }
                    break;
                case 1:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol+1)==0 && shipsMatrix.get(randomRow).get(randomCol+2)==0){
                        shipsMatrix.get(randomRow).set(randomCol,3);
                        shipsMatrix.get(randomRow).set(randomCol+1,3);
                        shipsMatrix.get(randomRow).set(randomCol+2,3);
                        submarineNumbers++;
                    }
                    break;
                case 2:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow+1).get(randomCol)==0 && shipsMatrix.get(randomRow+2).get(randomCol)==0){
                        shipsMatrix.get(randomRow).set(randomCol,3);
                        shipsMatrix.get(randomRow+1).set(randomCol,3);
                        shipsMatrix.get(randomRow+2).set(randomCol,3);
                        submarineNumbers++;
                    }
                    break;
                case 3:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol-1)==0 && shipsMatrix.get(randomRow).get(randomCol-2)==0){
                        shipsMatrix.get(randomRow).set(randomCol,3);
                        shipsMatrix.get(randomRow).set(randomCol-1,3);
                        shipsMatrix.get(randomRow).set(randomCol-2,3);
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
                        shipsMatrix.get(randomRow).set(randomCol,4);
                        shipsMatrix.get(randomRow-1).set(randomCol,4);
                        shipsMatrix.get(randomRow-2).set(randomCol,4);
                        shipsMatrix.get(randomRow-3).set(randomCol,4);
                        airportCarrierNumbers++;
                    }
                    break;
                case 1:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol+1)==0 && shipsMatrix.get(randomRow).get(randomCol+2)==0 && shipsMatrix.get(randomRow).get(randomCol+3)==0){
                        shipsMatrix.get(randomRow).set(randomCol,4);
                        shipsMatrix.get(randomRow).set(randomCol+1,4);
                        shipsMatrix.get(randomRow).set(randomCol+2,4);
                        shipsMatrix.get(randomRow).set(randomCol+3,4);
                        airportCarrierNumbers++;
                    }
                    break;
                case 2:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow+1).get(randomCol)==0 && shipsMatrix.get(randomRow+2).get(randomCol)==0 && shipsMatrix.get(randomRow+3).get(randomCol)==0){
                        shipsMatrix.get(randomRow).set(randomCol,4);
                        shipsMatrix.get(randomRow+1).set(randomCol,4);
                        shipsMatrix.get(randomRow+2).set(randomCol,4);
                        shipsMatrix.get(randomRow+3).set(randomCol,4);
                        airportCarrierNumbers++;
                    }
                    break;
                case 3:
                    if(shipsMatrix.get(randomRow).get(randomCol)==0 && shipsMatrix.get(randomRow).get(randomCol-1)==0 && shipsMatrix.get(randomRow).get(randomCol-2)==0 && shipsMatrix.get(randomRow).get(randomCol-3)==0){
                        shipsMatrix.get(randomRow).set(randomCol,4);
                        shipsMatrix.get(randomRow).set(randomCol-1,4);
                        shipsMatrix.get(randomRow).set(randomCol-2,4);
                        shipsMatrix.get(randomRow).set(randomCol-3,4);
                        airportCarrierNumbers++;
                    }

            }

        }while (airportCarrierNumbers < 1);


    };

    public void botIntelligence(){
        System.out.println("Bot Intelligence");
    }



    public void clearMatrix(){
        for (ArrayList<Integer> fila : shipsMatrix) {
            Collections.fill(fila, 0);
        }
        Integer[][] shipsPositionMatrixBot = new Integer[10][10];
    }
}
