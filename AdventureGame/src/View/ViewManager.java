package View;

import Model.InfoLabel;
import Model.MenuPanel;
import Model.SecretButton;
import com.mysql.jdbc.exceptions.MySQLDataException;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Model.NavigationButton;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

/** ViewManager manages menu window and all its elements */
public class ViewManager {

    //Constants for managing layout
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final int HEIGHT = 600;
    private final int WIDTH = 800;
    private final int MENU_BUTTON_START_X = 100;
    private final int MENU_BUTTON_START_Y = 100;
    private final String TITLE = "View/resources/Title.png";

    private List<NavigationButton> menuButtons;
    private  MenuPanel helpPanel;
    private  MenuPanel creditsPanel;
    private MenuPanel gameModePanel;
    private static boolean twoPlayersMode = false;

    private MusicManager musicManager;

    /** Creates menu window with basic elements such as buttons etc. */
    public ViewManager() {
        //creating list to manage buttons
        menuButtons = new ArrayList<>();
        //initializing main components of window
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setTitle("Battle Metropolis");
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createPanels();
        createTitle();
        musicManager = new MusicManager();
        musicManager.playMenuTheme();
    }

    /** Returning Main Stage. */
    public Stage getMainStage() {
        return mainStage;
    }

    ///////////////////////MENU METHODS//////////////////////////////
    private void createTitle() {
        ImageView title = new ImageView(TITLE);
        title.setFitWidth(450);
        title.setFitHeight(40);
        title.setLayoutX(WIDTH - 475);
        title.setLayoutY(50);
        mainPane.getChildren().add(title);
    }

    private void createButtons() {
        createPlayButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
        createSecretButton();
    }

    private void createPanels() {
        createHelpPanel();
        createCreditsPanel();
        createGameModePanel();
    }

    private void addMenuButton(NavigationButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size()*100);
        //adding new button to button list
        menuButtons.add(button);
        //showing button on screen
        mainPane.getChildren().add(button);
    }

    private void createPlayButton() {
        NavigationButton playButton = new NavigationButton("PLAY");
        addMenuButton(playButton);

        playButton.setOnAction(event -> {
            musicManager.playClickSound();
            if (!creditsPanel.isHid())
                creditsPanel.movePanel();
            if (!helpPanel.isHid())
                helpPanel.movePanel();
            gameModePanel.movePanel();
        });
    }

    private void createHelpButton() {
        NavigationButton helpButton = new NavigationButton("HELP");
        addMenuButton(helpButton);

        //action handler to call panel animation whenever button is pressed
        helpButton.setOnAction(event -> {
            musicManager.playClickSound();
            if (!creditsPanel.isHid())
                creditsPanel.movePanel();
            if (!gameModePanel.isHid())
                gameModePanel.movePanel();
            helpPanel.movePanel();
        });
    }

    private void createCreditsButton() {
        NavigationButton creditsButton = new NavigationButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(event -> {
            musicManager.playClickSound();
            if (!helpPanel.isHid())
                helpPanel.movePanel();
            if (!gameModePanel.isHid())
                gameModePanel.movePanel();
            creditsPanel.movePanel();
        });
    }

    private void createExitButton() {
        NavigationButton exitButton = new NavigationButton("EXIT");
        addMenuButton(exitButton);

        //handler to exit app if button is pressed
        exitButton.setOnAction(event -> {
            musicManager.playClickSound();
            Platform.exit();
            System.exit(0);
        });
    }

    private void createSecretButton() {
        SecretButton secretButton = new SecretButton();
        secretButton.setLayoutX(0);
        secretButton.setLayoutY(0);
        mainPane.getChildren().add(secretButton);

        secretButton.setOnAction(event -> {
            musicManager.playClickSound();
            musicManager.enableSecretTheme();
        });
    }

    private void createBackground() {
        Image backgroundMenuImage = new Image("View/resources/temp_background.png", WIDTH, HEIGHT, false, true);
        BackgroundImage backgroundMenu = new BackgroundImage(backgroundMenuImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(backgroundMenu));
    }

    private void createHelpPanel() {
        helpPanel = new MenuPanel(800, 130);
        mainPane.getChildren().add(helpPanel);
        InfoLabel helpLabel = new InfoLabel("CONTROLS", 120, -30, 35);
        helpPanel.getPane().getChildren().add(helpLabel);

        InfoLabel controlsLabel = new InfoLabel("FIRST PLAYER:", 0, 30, 30);
        helpPanel.getPane().getChildren().add(controlsLabel);
        InfoLabel firstPlayerControls = new InfoLabel("UP, DOWN, LEFT, RIGHT", 0, 60, 25);
        helpPanel.getPane().getChildren().add(firstPlayerControls);
        InfoLabel firstPlayerControlsContinue = new InfoLabel(" - MOVEMENT", 170, 60, 25);
        helpPanel.getPane().getChildren().add(firstPlayerControlsContinue);
        InfoLabel firstPlayerControls2 = new InfoLabel("CTRL - SHOOT", 0, 90, 25);
        helpPanel.getPane().getChildren().add(firstPlayerControls2);
        InfoLabel controlsLabel2 = new InfoLabel("SECOND PLAYER:", 0, 120, 30);
        helpPanel.getPane().getChildren().add(controlsLabel2);
        InfoLabel secondPlayerControls = new InfoLabel("W,A,S,D - MOVEMENT", 0, 150, 25);
        helpPanel.getPane().getChildren().add(secondPlayerControls);
        InfoLabel secondPlayerControls2 = new InfoLabel("SHIFT - SHOOT", 0, 180, 25);
        helpPanel.getPane().getChildren().add(secondPlayerControls2);

    }

    private void createCreditsPanel() {
        creditsPanel = new MenuPanel(800, 130);
        mainPane.getChildren().add(creditsPanel);

        InfoLabel creatorsLabel = new InfoLabel("CREATORS", 110, -30, 35);
        InfoLabel creator1Label = new InfoLabel("JAN KIES", 60, 50, 30);
        InfoLabel creator2Label = new InfoLabel("BARTOSZ SOBIESKI", 60, 100, 30);
        InfoLabel creator3Label = new InfoLabel("MARCIN PIOTROWSKI ", 60, 150, 30);
        creditsPanel.getPane().getChildren().add(creatorsLabel);
        creditsPanel.getPane().getChildren().add(creator1Label);
        creditsPanel.getPane().getChildren().add(creator2Label);
        creditsPanel.getPane().getChildren().add(creator3Label);
    }

    private void createGameModePanel() {
        gameModePanel = new MenuPanel(800, 130);
        mainPane.getChildren().add(gameModePanel);

        InfoLabel gameModeLabel = new InfoLabel("CHOOSE GAME MODE", 55, -30, 35);
        gameModePanel.getPane().getChildren().add(gameModeLabel);

        NavigationButton onePlayerMode = new NavigationButton("One Player");
        onePlayerMode.setLayoutX(100);
        onePlayerMode.setLayoutY(125);
        gameModePanel.getPane().getChildren().add(onePlayerMode);

        onePlayerMode.setOnAction(event -> {
            musicManager.playClickSound();
            MapChooseManager mapChooseManager = new MapChooseManager(mainStage,musicManager,false);
            mapChooseManager.createChooseMenu();
        });

        NavigationButton twoPlayersMode = new NavigationButton("Two Players");
        twoPlayersMode.setLayoutX(100);
        twoPlayersMode.setLayoutY(200);
        gameModePanel.getPane().getChildren().add(twoPlayersMode);

        twoPlayersMode.setOnAction(event -> {
            musicManager.playClickSound();
            MapChooseManager mapChooseManager = new MapChooseManager(mainStage,musicManager,true);
            mapChooseManager.createChooseMenu();
        });
    }


}
