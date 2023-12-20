package com.example.mygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HelloController {

    @FXML
    private final Label npcStatsLabel;
    @FXML
    private Label locationLabel;

    @FXML
    private Label playerStatLabel;

    public HelloController(Label npcStatsLabel) {
        this.npcStatsLabel = npcStatsLabel;
    }

    @Deprecated
    public void leftButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked left!\n");
        // Add logic to handle the left button click in the game
        // Example: game.handleLeftButtonClick();
    }

    @Deprecated
    public void upButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked up!\n");
        // Add logic to handle the up button click in the game
        // Example: game.handleUpButtonClick();
    }

    @Deprecated
    public void downButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked down!\n");
        // Add logic to handle the down button click in the game
        // Example: game.handleDownButtonClick();
    }

    @Deprecated
    public void rightButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked right!\n");
        // Add logic to handle the right button click in the game
        // Example: game.handleRightButtonClick();
    }

    @Deprecated
    public void attackButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked attack!\n");
        // Add logic for attack button
        // Example: game.handleAttackButtonClick();
    }

    @Deprecated
    public void runButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked run!\n");
        // Add logic for run button
        // Example: game.handleRunButtonClick();
    }

    @Deprecated
    public void searchButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked search!\n");
        // Add logic for search button
        // Example: game.handleSearchButtonClick();
    }

    @Deprecated
    public void sleepButtonClicked(ActionEvent actionEvent) {
        StringBuilder textArea = new StringBuilder();
        textArea.append("You clicked sleep!\n");
        // Add logic for sleep button
        // Example: game.handleSleepButtonClick();
    }

    public void setIntroImage(String introImageView) {
    }
}
