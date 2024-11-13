module com.example.miniproyecto_3_battlership {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.xml.dom;


    opens com.example.miniproyecto_3_battlership to javafx.fxml;
    exports com.example.miniproyecto_3_battlership;
    exports com.example.miniproyecto_3_battlership.controller;
    opens com.example.miniproyecto_3_battlership.controller to javafx.fxml;
}