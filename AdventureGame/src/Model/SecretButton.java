package Model;

import javafx.scene.control.Button;
/** Easter egg. Like... you are not supposed to know what it is... */
public class SecretButton extends Button {

    private final String BUTTON_STYLE = "-fx-background-color: transparent";
    /** Constructor that sets button details. */
    public SecretButton() {
        setPrefHeight(48);
        setPrefWidth(200);
        setStyle(BUTTON_STYLE);
    }
}
