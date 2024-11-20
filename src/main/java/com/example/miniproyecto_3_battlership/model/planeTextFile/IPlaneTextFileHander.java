package com.example.miniproyecto_3_battlership.model.planeTextFile;

public interface IPlaneTextFileHander {
    void writeToFile(String fileName, String text);
    String[] readFromFile(String fileName);
}
