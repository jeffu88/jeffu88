package Model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/** Basic navigation button that is used in all the panels and menu.*/
public class NavigationButton extends Button {

    private final String FONT_PATH = "src/Model/Resources/Fonts/PressStart2P-Regular.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/Model/Resources/MenuContent/Button_BG_pressed.png')";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/Model/Resources/MenuContent/Button_BG_shadow.png')";

    //Main constructor containing all initializing methods
    /** Constructor that sets button details. */
    public NavigationButton(String text) {
        setText(text);
        setButtonFont();
        setPrefHeight(48);
        setPrefWidth(200);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 15));
            setTextFill(Color.WHITE); //change font color
        } catch (FileNotFoundException e) {
            setFont(Font.font("Comic Sans", 25)); //in case of missing font load comic sans
            setTextFill(Color.WHITE);
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(44); //little squish effect to simulate pressing the button
        setLayoutY(getLayoutY() + 4); //movement due to different size of button
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(48);
        setLayoutY(getLayoutY() - 4);
    }


    //Creating handlers to catch clicking on buttons. When this happens, changing button style.
    private void initializeButtonListeners() {
        setOnMousePressed(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });

        //Lambda function that creates new mouse event. If button button released, style is changing
        setOnMouseReleased(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });
    }
}
