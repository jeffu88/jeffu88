package com.example.gamedemo;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HelloController {

    private java.awt.Label welcomeText;

    @FXML
    protected void onStartButtonClick() {
        welcomeText.setText("Welcome to the Game Demo!");
    }
    @FXML
    private Button upButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    @FXML
    private Button downButton;
    @FXML
    private Button attackButton;
    @FXML
    private Button runButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button sleepButton;
    @FXML
    private Label playerStatLabel;
    @FXML
    private Label npcStatsLabel;
    @FXML
    private ImageView roomImageView;
    @FXML
    private Label locationLabel;
    @FXML
    private Button roomButton;

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

        locationLabel.setText("You clicked sleep!\n");
        // Add logic for sleep button
        // Example: game.handleSleepButtonClick();
    }

    @Deprecated
    public void roomButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onRoomButtonClick(ActionEvent actionEvent) {
    }

    @Deprecated
    public void roomButtonClick(Event event) {
    }
}
