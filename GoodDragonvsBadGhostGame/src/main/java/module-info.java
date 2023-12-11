module com.example.gooddragonvsbadghostgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.gooddragonvsbadghostgame to javafx.fxml;
    exports com.example.gooddragonvsbadghostgame;
}