package com.example.miniproyecto_3_battlership.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameStage extends Stage {

    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto_3_battlership/gameView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        setWidth(screenWidth * 1);
        setHeight(screenHeight * 1);
        setTitle("BattleShip");
        setScene(scene);
        show();
    }

    private static class GameStageHolder {
        /**
         * The unique instance of {@code WelcomeStage}.
         */
        private static GameStage INSTANCE;
    }

    public static GameStage getInstance() throws IOException {
        GameStage.GameStageHolder.INSTANCE =
                GameStage.GameStageHolder.INSTANCE != null ?
                        GameStage.GameStageHolder.INSTANCE : new GameStage();
        return GameStage.GameStageHolder.INSTANCE;
    }

    public static void deleteInstance() {
        GameStage.GameStageHolder.INSTANCE.close();
        GameStage.GameStageHolder.INSTANCE = null;
    }


}
