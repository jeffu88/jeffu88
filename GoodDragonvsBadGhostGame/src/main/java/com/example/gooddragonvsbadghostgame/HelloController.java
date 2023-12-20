package com.example.gooddragonvsbadghostgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HelloController {


    @FXML
    private Label npcStatsLabel;
    @FXML
    private Label locationLabel;

    @FXML
    private Button upButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    @FXML
    private Button downButton;
    @FXML
    private Label playerStatLabel;
    @FXML
    private Button attackButton;
    @FXML
    private Button runButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button sleepButton;
    @FXML
    private ImageView roomImageView;

     @FXML
    public void leftButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked left!\n");
        // Add logic to handle the left button click in the game
        // Example: game.handleLeftButtonClick();
    }

    @FXML
    public void upButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked up!\n");
        // Add logic to handle the up button click in the game
        // Example: game.handleUpButtonClick();
    }

    @FXML
    public void downButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked down!\n");
        // Add logic to handle the down button click in the game
        // Example: game.handleDownButtonClick();
    }

    @FXML
    public void rightButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked right!\n");
        // Add logic to handle the right button click in the game
        // Example: game.handleRightButtonClick();
    }

    @FXML
    public void attackButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked attack!\n");
        // Add logic for attack button
        // Example: game.handleAttackButtonClick();
    }

    @FXML
    public void runButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked run!\n");
        // Add logic for run button
        // Example: game.handleRunButtonClick();
    }

    @FXML
    public void searchButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked search!\n");
        // Add logic for search button
        // Example: game.handleSearchButtonClick();
    }

    @FXML
    public void sleepButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked sleep!\n");
        // Add logic for sleep button
        // Example: game.handleSleepButtonClick();
    }

       public void setIntroImage(String introImageView) {
    }
}
