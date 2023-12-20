package Model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/** Basic label with custom font. */
public class InfoLabel extends Label {
    private static final String FONT_PATH = "src/Model/Resources/Fonts/HeartbitXX.ttf";
    /** Constructor that initializes label with text and given size.*/
    public InfoLabel (String text, double X, double Y, int size) {
        setPrefWidth(300);
        setPrefHeight(150);
        setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);
        setTextFill(Color.WHITE);  //changing font color
        setLabelFont(size);
        setLayoutX(X);
        setLayoutY(Y);
        setTextAlignment(TextAlignment.CENTER);
    }

    private void setLabelFont(int size) {
        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), size)); //size 25 is small label font
        } catch (FileNotFoundException e) {
            setFont(Font.font("Comic Sans", 17)); //if font is missing loading comic sans
        }
    }
}
