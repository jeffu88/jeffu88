module com.example.gamedemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.gamedemo to javafx.fxml;
    exports com.example.gamedemo;
}