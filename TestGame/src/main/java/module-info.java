module com.example.testgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testgame to javafx.fxml;
    exports com.example.testgame;
}