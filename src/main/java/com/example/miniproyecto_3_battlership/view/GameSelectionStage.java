package com.example.miniproyecto_3_battlership.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameSelectionStage extends Stage {

    public GameSelectionStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto_3_battlership/gameSelectionView.fxml"));
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
        private static GameSelectionStage INSTANCE;
    }

    public static GameSelectionStage getInstance() throws IOException {
        GameSelectionStage.GameStageHolder.INSTANCE =
                GameSelectionStage.GameStageHolder.INSTANCE != null ?
                        GameSelectionStage.GameStageHolder.INSTANCE : new GameSelectionStage();
        return GameSelectionStage.GameStageHolder.INSTANCE;
    }

    public static void deleteInstance() {
        GameSelectionStage.GameStageHolder.INSTANCE.close();
        GameSelectionStage.GameStageHolder.INSTANCE = null;
    }


}
