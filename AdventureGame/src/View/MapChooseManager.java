package View;

import Model.CustomCheckBox;
import Model.InfoLabel;
import Model.MAP;
import Model.NavigationButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
/** MapChooseManager is responsible for choosing the map. */
public class MapChooseManager
{
    private AnchorPane choosePane;
    private Scene chooseScene;
    private Stage chooseStage;
    private Stage menuStage;
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 800;
    private ArrayList<CustomCheckBox> mapList;
    private String choosenMap;
    private static final String BACKGROUND = "Model/Resources/MenuContent/Background.png";
    private MusicManager musicManager;
    private boolean twoPlayersMode;
    /** Constructor of the MapChooseManager. Passes information about twoPlayersMode to the gameViewManager.
     * Creates pane. */
    public MapChooseManager(Stage menuStage, MusicManager musicManager, boolean twoPlayersMode)
    {
        this.menuStage = menuStage;
        menuStage.close();
        choosePane = new AnchorPane();
        chooseScene = new Scene(choosePane,WIDTH,HEIGHT);
        chooseStage = new Stage();
        chooseStage.initStyle(StageStyle.UNDECORATED);
        chooseStage.setScene(chooseScene);
        chooseStage.setTitle("Choose your map");
        Image background = new Image(BACKGROUND,WIDTH,HEIGHT,false,true);
        BackgroundImage backgroundImage = new BackgroundImage(background,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
        choosePane.setBackground(new Background(backgroundImage));
        this.musicManager = musicManager;
        this.twoPlayersMode = twoPlayersMode;
    }

    /** Creates elements of the chooseMenu. */
    public void createChooseMenu()
    {
        InfoLabel title = new InfoLabel("CHOOSE YOUR MAP:",500,0,80);
        title.setPrefWidth(700);
        title.setPrefHeight(100);
        choosePane.getChildren().add(title);
        choosePane.getChildren().add(createMapChoosePanel());
        createStratButton();
        createBackButton();
        chooseStage.show();
    }

    private HBox createMapChoosePanel()
    {
        HBox box = new HBox();
        box.setSpacing(30);
        mapList = new ArrayList<>();
        for(MAP map : MAP.values())
        {
            CustomCheckBox customCheckBox = new CustomCheckBox(map);
            mapList.add(customCheckBox);
            box.getChildren().add(customCheckBox);
            customCheckBox.setOnMouseClicked(event -> {
                for(CustomCheckBox map1 : mapList)
                {
                    map1.setIsMapChoosen(false);
                }
                customCheckBox.setIsMapChoosen(true);
                choosenMap = customCheckBox.getMapName();
            });
        }
        box.setLayoutX(104);
        box.setLayoutY(150);
        return box;
    }

    private void createStratButton()
    {
        NavigationButton playButton = new NavigationButton("PLAY");
        playButton.setOnAction(event -> {
            if(choosenMap != null)
            {
                musicManager.stopMusic();
                musicManager.playClickSound();
                GameViewManager gameViewManager = new GameViewManager(musicManager, choosenMap, twoPlayersMode);
                gameViewManager.createGame(chooseStage, false);
            }
        });
        playButton.setLayoutX(535);
        playButton.setLayoutY(700);
        choosePane.getChildren().add(playButton);
    }

    private void createBackButton()
    {
        NavigationButton playButton = new NavigationButton("BACK");
        playButton.setOnAction(event -> {
            chooseStage.close();
            menuStage.show();
        });
        playButton.setLayoutX(765);
        playButton.setLayoutY(700);
        choosePane.getChildren().add(playButton);
    }
}
