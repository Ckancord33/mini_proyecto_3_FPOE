package com.example.miniproyecto_3_battlership.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class HelloController {

    @FXML
    private Rectangle rectangle;
    private double startX, startY;
    private double finalX, finalY;

    @FXML
    private GridPane gridPane;

    @FXML
    void onHandleMouseDragged(MouseEvent event) {
        // Actualiza la posición del rectángulo mientras se arrastra
        rectangle.setX(event.getSceneX() - startX);
        rectangle.setY(event.getSceneY() - startY);
    }

    @FXML
    void setOnMouseRealeased(MouseEvent event) {
        finalX = event.getSceneX();
        finalY = event.getSceneY();
        if(finalX > 152 - 20 && finalX < 152 + 20 && finalY > 53 - 20 && finalY < 53 + 20){
            System.out.println("Me pusiste xd");
            rectangle.setFill(javafx.scene.paint.Color.RED);
        }else{
            rectangle.setFill(javafx.scene.paint.Color.BLUE);
        }
        System.out.println(event.getSceneX() + " " + event.getSceneY() + " Si es aqui xd");
    }

    @FXML
    void setOnMousePressed(MouseEvent event) {

        // Registra la posición inicial del clic
        startX = event.getSceneX() - rectangle.getX();
        startY = event.getSceneY() - rectangle.getY();
    }


    @FXML
    void onDragDetected(MouseEvent event) {
        //138 44
        System.out.println("Drag Detected");
    }

    @FXML
    void onDragDone(DragEvent event) {
        System.out.println("Drag Done");
    }

    @FXML
    void onDragExited(DragEvent event) {
        System.out.println("Drag Exited");
    }

    @FXML
    void onDragOver(DragEvent event) {
        System.out.println("Drag Over");
    }

    @FXML
    void onMouseDragExited(MouseDragEvent event) {
        System.out.println("Drag Mouse Exited");
    }

    @FXML
    void onMouseDragOver(MouseDragEvent event) {
        System.out.println("Drag Mouse Over");
    }

    @FXML
    void onMouseDragReleased(MouseDragEvent event) {
        System.out.println("Drag Mouse Released");
    }

    @FXML
    void onDragDropped(DragEvent event) {
        System.out.println("Drag Dropped");
    }

    @FXML
    void onDragEntered(DragEvent event) {
        System.out.println("Drag Entered");
    }


    @FXML
    void onDragMouseEntered(MouseDragEvent event) {
        System.out.println("Drag Mouse Entered");
    }

    @FXML
    void onDragMouseReleased(MouseDragEvent event) {
        System.out.println("Drag Mouse Released");
    }



}
