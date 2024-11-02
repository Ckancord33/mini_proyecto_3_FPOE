package com.example.miniproyecto_3_battlership.controller;

import com.example.miniproyecto_3_battlership.view.GameStage;
import com.example.miniproyecto_3_battlership.view.WelcomeStage;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class WelcomeController {

    @FXML
    public void initialize() {

    }
    @FXML
    public void onHandlePlayGame(javafx.event.ActionEvent actionEvent) throws IOException {
        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }
}
