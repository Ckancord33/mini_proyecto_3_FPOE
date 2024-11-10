package com.example.miniproyecto_3_battlership.model.game;

import com.example.miniproyecto_3_battlership.model.AGame;

import java.util.ArrayList;
import java.util.Collections;

public class Game extends AGame {

    private final ArrayList<ArrayList<Integer>> matriz = new ArrayList<>();

    public void generateGameBot(){
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
            if(matriz.get(randomRow).get(randomCol)==0){
                matriz.get(randomRow).set(randomCol,1);
                fragataNumbers++;
            }
        }while (fragataNumbers < 4);

        do{
            randomRow = (int)(Math.random()*8);
            randomCol = (int)(Math.random()*8);
            direction = (int)(Math.random()*3);
            switch(direction){
                case 0:
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow-1).get(randomCol)==0){
                        matriz.get(randomRow).set(randomCol,2);
                        matriz.get(randomRow-1).set(randomCol,2);
                        destroyerNumbers++;
                    }
                    break;
                case 1:
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow).get(randomCol+1)==0){
                        matriz.get(randomRow).set(randomCol,2);
                        matriz.get(randomRow).set(randomCol+1,2);
                        destroyerNumbers++;
                    }
                    break;
                case 2:
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow+1).get(randomCol)==0){
                        matriz.get(randomRow).set(randomCol,2);
                        matriz.get(randomRow+1).set(randomCol,2);
                        destroyerNumbers++;
                    }
                    break;
                case 3:
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow).get(randomCol-1)==0){
                        matriz.get(randomRow).set(randomCol,2);
                        matriz.get(randomRow).set(randomCol-1,2);
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
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow-1).get(randomCol)==0 && matriz.get(randomRow-2).get(randomCol)==0){
                        matriz.get(randomRow).set(randomCol,3);
                        matriz.get(randomRow-1).set(randomCol,3);
                        matriz.get(randomRow-2).set(randomCol,3);
                        submarineNumbers++;
                    }
                    break;
                case 1:
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow).get(randomCol+1)==0 && matriz.get(randomRow).get(randomCol+2)==0){
                        matriz.get(randomRow).set(randomCol,3);
                        matriz.get(randomRow).set(randomCol+1,3);
                        matriz.get(randomRow).set(randomCol+2,3);
                        submarineNumbers++;
                    }
                    break;
                case 2:
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow+1).get(randomCol)==0 && matriz.get(randomRow+2).get(randomCol)==0){
                        matriz.get(randomRow).set(randomCol,3);
                        matriz.get(randomRow+1).set(randomCol,3);
                        matriz.get(randomRow+2).set(randomCol,3);
                        submarineNumbers++;
                    }
                    break;
                case 3:
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow).get(randomCol-1)==0 && matriz.get(randomRow).get(randomCol-2)==0){
                        matriz.get(randomRow).set(randomCol,3);
                        matriz.get(randomRow).set(randomCol-1,3);
                        matriz.get(randomRow).set(randomCol-2,3);
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
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow-1).get(randomCol)==0 && matriz.get(randomRow-2).get(randomCol)==0 && matriz.get(randomRow-3).get(randomCol)==0){
                        matriz.get(randomRow).set(randomCol,4);
                        matriz.get(randomRow-1).set(randomCol,4);
                        matriz.get(randomRow-2).set(randomCol,4);
                        matriz.get(randomRow-3).set(randomCol,4);
                        airportCarrierNumbers++;
                    }
                    break;
                case 1:
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow).get(randomCol+1)==0 && matriz.get(randomRow).get(randomCol+2)==0 && matriz.get(randomRow).get(randomCol+3)==0){
                        matriz.get(randomRow).set(randomCol,4);
                        matriz.get(randomRow).set(randomCol+1,4);
                        matriz.get(randomRow).set(randomCol+2,4);
                        matriz.get(randomRow).set(randomCol+3,4);
                        airportCarrierNumbers++;
                    }
                    break;
                case 2:
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow+1).get(randomCol)==0 && matriz.get(randomRow+2).get(randomCol)==0 && matriz.get(randomRow+3).get(randomCol)==0){
                        matriz.get(randomRow).set(randomCol,4);
                        matriz.get(randomRow+1).set(randomCol,4);
                        matriz.get(randomRow+2).set(randomCol,4);
                        matriz.get(randomRow+3).set(randomCol,4);
                        airportCarrierNumbers++;
                    }
                    break;
                case 3:
                    if(matriz.get(randomRow).get(randomCol)==0 && matriz.get(randomRow).get(randomCol-1)==0 && matriz.get(randomRow).get(randomCol-2)==0 && matriz.get(randomRow).get(randomCol-3)==0){
                        matriz.get(randomRow).set(randomCol,4);
                        matriz.get(randomRow).set(randomCol-1,4);
                        matriz.get(randomRow).set(randomCol-2,4);
                        matriz.get(randomRow).set(randomCol-3,4);
                        airportCarrierNumbers++;
                    }

            }

        }while (airportCarrierNumbers < 1);


    };

    public ArrayList<ArrayList<Integer>> getMatriz() {
        return matriz;
    }

    public void setMatrix() {
        int filas = 10;
        int columnas = 10;
        for (int i = 0; i < filas; i++) {
            ArrayList<Integer> fila = new ArrayList<>();
            for (int j = 0; j < columnas; j++) {
                fila.add(0); // Puedes agregar un valor inicial, como 0
            }
            this.matriz.add(fila);
        }
    }

    public void clearMatriz(){
        for (ArrayList<Integer> fila : matriz) {
            Collections.fill(fila, 0);
        }
        Integer[][] matriz = new Integer[10][10];


    }

}
