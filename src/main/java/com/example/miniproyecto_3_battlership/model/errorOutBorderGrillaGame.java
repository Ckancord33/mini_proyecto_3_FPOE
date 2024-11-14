package com.example.miniproyecto_3_battlership.model;

public class errorOutBorderGrillaGame extends Exception {

    public errorOutBorderGrillaGame(){
        super();
    }

    public errorOutBorderGrillaGame(String message) {
        super(message);
    }

    public errorOutBorderGrillaGame(String message, Throwable cause) {
        super(message, cause);
    }

    public errorOutBorderGrillaGame(Throwable cause) {
        super(cause);
    }


}
