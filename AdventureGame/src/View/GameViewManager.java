package View;


import Model.InfoLabel;
import Model.MapElements.Base;
import Model.MapElements.BrickBlock;
import Model.MenuPanel;
import Model.NavigationButton;
import Model.Tanks.Tank;
import Model.Tanks.TankPlayer;
import Model.Tanks.TankSecondPlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** GameViewManager is responsible for managing game window.
 * It contains game loop that checks if game is over.
 * It also creates pause panel and win/lose panel.*/
public class GameViewManager {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private Rectangle overlay;
    private MenuPanel pausePanel;
    private InfoLabel pauseLabel;
    private NavigationButton resumeButton;
    private NavigationButton exitButton;

    //variables for animation
    private List<Tank> tanksList;


    private Tank playerOneTank;
    private AnimationTimer gameTimer;
    private Base base;
    private DataBaseConnector dataBaseConnector;
    private String mapName;
    private MapManager mapManager;

    private boolean isGamePaused;
    private boolean twoPlayersMode;
    private boolean gridMode;
    private boolean matrixAvaliable;
    private boolean isGameEnded;

    private static int GAME_WIDTH;  //Map divided into blocks 50x50 pixels each
    private static int GAME_HEIGHT; //Map has size 16x12 blocks
    private static int BLOCK_SIZE;
    private static String[][] positionMatrix;
    private ArrayList<BrickBlock> brickList;
    private ArrayList<ImageView> waterList;
    Tank spawningTank;
    private int hunterCount;
    private int baseRasherCount;
    private ImageView star;
    //array used to detect collisions. It contains strings. If string is a number
    //that means in this position tank is present and number equals it`s. If any other string

    private final static String standardTankSprite = "Model/Resources/tankSprites/tank_dark.png";
    private final static String huntTankSprite = "Model/Resources/tankSprites/tank_hunt.png";
    private final static String rushTankSprite = "Model/Resources/tankSprites/tank_rush.png";
    private final static String playerOneTankSprite = "Model/Resources/tankSprites/tank_red.png";

    private MusicManager musicManager;
    private boolean starAvaliable;
    ///////////////////////WINDOW INITIALIZATION////////////////////////////////////
    /** Creates gameViewManager.*/
    public GameViewManager(MusicManager musicManager, String mapName, boolean twoPlayersMode) {
        this.musicManager = musicManager;
        this.mapName = mapName;
        initializeStage();
        createBackground();
        musicManager.playMainTheme();
        createShadowOverlay();
        createPausePanel();
        this.twoPlayersMode = twoPlayersMode;
        matrixAvaliable = true;
        isGameEnded = false;
        starAvaliable=false;
        star = new ImageView("Model/MapElements/MapPieces/starAnimated.gif");
        star.setFitHeight(50);
        star.setFitWidth(50);
        star.setVisible(false);
        gamePane.getChildren().add(star);
    }

    private void initializeStage() {
        dataBaseConnector = new DataBaseConnector("SELECT * FROM map WHERE name = '" + mapName + "';");
        dataBaseConnector.getData();
        GAME_WIDTH = dataBaseConnector.getGame_width();
        GAME_HEIGHT = dataBaseConnector.getGame_height();
        BLOCK_SIZE = 50;
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.initStyle(StageStyle.UNDECORATED); //hiding system window bar
        gameStage.setTitle("Battle Metropolis");
        gridMode = false;
        isGamePaused = false;
        mapManager = new MapManager(gamePane, gameScene, gameStage, dataBaseConnector);
        waterList = mapManager.getWaterList();
        hunterCount=0;
        baseRasherCount=0;
    }

    //////////////////////////GAME ELEMENTS////////////////////////////////////////
    //
    /** Creating game window with all of elements such as tanks, map and base. */
    public void createGame(Stage menuStage, boolean twoPlayersMode) {
        tanksList = new ArrayList<>();  //initializing array list that allows to manage all tanks on map
        this.menuStage = menuStage;
        this.menuStage.close();

        spawnBase(gamePane, mapManager.getBaseX(), mapManager.getBaseY(), positionMatrix, 5); //BASE NEED TO BE INITIALIZED BEFORE TANKS!!!
        spawnPlayerOneTank(gamePane, gameScene, mapManager.getPlayerOneX(), mapManager.getPlayerOneY(), playerOneTankSprite, tanksList, positionMatrix,
                KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN, KeyCode.CONTROL, dataBaseConnector, brickList, waterList);

        for (Point i : mapManager.getNeutralList()) {
            spawnNeutralTank(gamePane, (int) i.getX(), (int) i.getY(), standardTankSprite, tanksList, positionMatrix, dataBaseConnector, brickList, waterList);
        }
        mapManager.bushToFront();
        ((TankPlayer) playerOneTank).heartsToFront();
        waterToBack();
        createGameLoop();
        gameStage.show();
    }

    /*
    Timer to call animation in every frame
    After calling moveTank, moveIterator is set to BlockSize/5 that in this case equals 9.
    Now continueTankMovement will be called instead of moveTank.
    This makes sure, user input won`t interrupt animation, because continueTankMovement does not depends on user input.
    Thanks to that, we make sure that tank is moving only by 50, which is map block size and animation will be smooth.
    */

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            Random random = new Random();
            int posX;
            int posY;

            @Override
            public void handle(long now) {
                if(!starAvaliable && Math.random()<0.01) {

                    posX = random.nextInt(dataBaseConnector.getGame_width()/50);
                    posY = random.nextInt(dataBaseConnector.getGame_height()/50);

                    if(positionMatrix[posX][posY] == null) {
                        star.setLayoutX(posX*50);
                        star.setLayoutY(posY*50);
                        star.setVisible(true);
                        starAvaliable =true;
                        ((TankPlayer)playerOneTank).setStarX(posX);
                        ((TankPlayer)playerOneTank).setStarY(posY);
                    }

                }
                if (!isGamePaused && !((TankPlayer) playerOneTank).getIsPaused()) {

                    if (twoPlayersMode) {
                        if ((playerOneTank.getLifePoints() <= 0 && ((TankPlayer) playerOneTank).getSecondPlayerLifePoints() <= 0) || base.getLifePoints() == 0) {
                            tanksList.remove(playerOneTank);
                            showLoseScreen();
                            isGameEnded = true;
                        }
                    } else {
                        if (playerOneTank.getLifePoints() <= 0 || base.getLifePoints() == 0) {
                            showLoseScreen();
                            isGameEnded = true;
                        }
                    }
                    if (mapManager.getNeutralCounter()) {
                        if ((tanksList.size() == 1 && !twoPlayersMode) || tanksList.size() <= 2 && twoPlayersMode) {
                            if (tanksList.get(0) instanceof TankPlayer)
                            {
                                showWinScreen();
                                isGameEnded = true;
                            }
                            else if (twoPlayersMode) {
                                if (tanksList.get(0) instanceof TankSecondPlayer) ;
                                {
                                    showWinScreen();
                                    isGameEnded = true;
                                }
                            }
                        }
                    }
                }
                if (!isGamePaused && ((TankPlayer) playerOneTank).getIsPaused()) {
                    isGamePaused = true;
                    showPausePanel();
                }
            }
        };
        gameTimer.start();
    }

    private void createBackground() {
        mapManager.createBackground();
        positionMatrix = mapManager.createPositionMatrix();
        brickList = mapManager.getBrickList();
    }

    private void createExitButton(double X, double Y) {
        exitButton = new NavigationButton("EXIT");
        exitButton.setVisible(false);
        exitButton.setLayoutX(X);
        exitButton.setLayoutY(Y);
        //showing button on screen
        gamePane.getChildren().add(exitButton);

        //handler to exit app if button is pressed
        exitButton.setOnAction(event ->
        {
            musicManager.playClickSound();
            musicManager.stopMusic();
            Platform.exit();
            System.exit(0);
        });
    }

    private void createResumeButton(double X, double Y) {
        resumeButton = new NavigationButton("RESUME");
        resumeButton.setLayoutX(X);
        resumeButton.setLayoutY(Y);
        resumeButton.setVisible(false);
        //showing button on screen
        gamePane.getChildren().add(resumeButton);

        //handler to exit app if button is pressed
        resumeButton.setOnAction(event ->
        {
            musicManager.playClickSound();
            hidePausePanel();
            isGamePaused = false;
            ((TankPlayer) playerOneTank).setIsPaused(false);
        });
    }

    private void createRetryButton(double X, double Y) {
        NavigationButton retryButton = new NavigationButton("RETRY");
        retryButton.setLayoutX(X);
        retryButton.setLayoutY(Y);
        //showing button on screen
        gamePane.getChildren().add(retryButton);

        //handler to reset game if button is pressed
        retryButton.setOnAction(event -> {
            musicManager.stopMusic();
            musicManager.playClickSound();
            GameViewManager gameViewManager = new GameViewManager(new MusicManager(), mapName, twoPlayersMode);
            gameViewManager.createGame(gameStage, false);
            gameTimer.stop();
        });
    }

    private void createGoToMenuButton(int X, int Y) {
        NavigationButton goToMenuButton = new NavigationButton("GO TO MENU");
        goToMenuButton.setLayoutX(X);
        goToMenuButton.setLayoutY(Y);
        gamePane.getChildren().add(goToMenuButton);

        goToMenuButton.setOnAction(event -> {
            musicManager.stopMusic();
            musicManager.playClickSound();
            gameTimer.stop();
            this.gameStage.close();
            ViewManager manager = new ViewManager();
            manager.getMainStage().show();
        });
    }

    private void createShadowOverlay() {
        overlay = new Rectangle(0, 0, GAME_WIDTH, GAME_HEIGHT);
        overlay.setOpacity(0.7);
        overlay.setVisible(false);
        gamePane.getChildren().add(overlay);
    }

    private MenuPanel createGamePanel() {
        MenuPanel gamePanel = new MenuPanel(GAME_WIDTH / 2 - 200, GAME_HEIGHT / 2 - 150);
        gamePane.getChildren().add(gamePanel);
        createExitButton(GAME_WIDTH / 2 - 99, GAME_HEIGHT / 2 + 70);
        return gamePanel;
    }


    /////////////////////////////////SPAWN METHODS////////////////////////////////////////////////////////////

    //function that checks if spawn position is empty and coordinates are correct, if they are, the Tank constructor is called
    private boolean spawnNeutralTank(AnchorPane gamePane, int spawnPosArrayX, int spawnPosArrayY,
                                     String tankSpriteUrl, List<Tank> tankList, String[][] collisionMatrix, DataBaseConnector dataBaseConnector, ArrayList<BrickBlock> brickList, ArrayList<ImageView> waterList) {
        if (spawnPosArrayX < GAME_WIDTH / BLOCK_SIZE && spawnPosArrayY < GAME_HEIGHT / BLOCK_SIZE) {
            if (collisionMatrix[spawnPosArrayX][spawnPosArrayY] == null) {
                if (Math.round(Math.random()) == 0) {
                    if ((Math.round(Math.random()) == 0) && baseRasherCount<3) {
                        spawningTank = new Tank("RUSH", gamePane, spawnPosArrayX, spawnPosArrayY, rushTankSprite,
                                tankList, collisionMatrix, 4, base, dataBaseConnector, brickList, waterList, this);
                        System.out.println("RUSH");
                        baseRasherCount++;
                    } else {
                        spawningTank = new Tank("RANDOM", gamePane, spawnPosArrayX, spawnPosArrayY, tankSpriteUrl,
                                tankList, collisionMatrix, 3, base, dataBaseConnector, brickList, waterList, this);
                        System.out.println("RAND");
                    }
                } else {
                    if (hunterCount < 5) {
                        spawningTank = new Tank("HUNT", gamePane, spawnPosArrayX, spawnPosArrayY, huntTankSprite,
                                tankList, collisionMatrix, 2, base, dataBaseConnector, brickList, waterList, this);
                        System.out.println("HUNT");
                        hunterCount++;
                    }
                    else {
                        spawningTank = new Tank("RANDOM", gamePane, spawnPosArrayX, spawnPosArrayY, tankSpriteUrl,
                                tankList, collisionMatrix, 3, base, dataBaseConnector, brickList, waterList, this);
                        System.out.println("RAND");
                    }
                }
                spawningTank.start();
                return true;
            }
        }
        return false;
    }

    //function that checks if spawn position is empty and coordinates are correct, if they are, the PlayerTank constructor is called
    private boolean spawnPlayerOneTank(AnchorPane gamePane, Scene gameScene, int spawnPosArrayX, int spawnPosArrayY, String tankSpriteUrl, List<Tank> tankList, String[][] collisionMatrix,
                                       KeyCode moveLeftKey, KeyCode moveRightKey, KeyCode moveUpKey, KeyCode moveDownKey, KeyCode shootKey, DataBaseConnector dataBaseConnector,
                                       ArrayList<BrickBlock> brickList, ArrayList<ImageView> waterList) {
        if (spawnPosArrayX < GAME_WIDTH / BLOCK_SIZE && spawnPosArrayY < GAME_HEIGHT / BLOCK_SIZE) {
            if (collisionMatrix[spawnPosArrayX][spawnPosArrayY] == null) {
                playerOneTank = new TankPlayer(gamePane, gameScene, spawnPosArrayX, spawnPosArrayY,
                        tankSpriteUrl, tankList, collisionMatrix, base,
                        dataBaseConnector, brickList, waterList, twoPlayersMode, this);
                playerOneTank.start();
                return true;
            }
        }
        return false;
    }

    private boolean spawnBase(AnchorPane gamePane, int spawnPosArrayX, int spawnPosArrayY, String[][] collisionMatrix, int lifePoints) {
        //Making sure that all 4 blocks where base will stand are empty and inside the map
        if (spawnPosArrayX < GAME_WIDTH / BLOCK_SIZE && spawnPosArrayY < GAME_HEIGHT / BLOCK_SIZE) {
            if (spawnPosArrayX + 1 < GAME_WIDTH / BLOCK_SIZE && spawnPosArrayY < GAME_HEIGHT / BLOCK_SIZE) {
                if (spawnPosArrayX < GAME_WIDTH / BLOCK_SIZE && spawnPosArrayY + 1 < GAME_HEIGHT / BLOCK_SIZE) {
                    if (spawnPosArrayX + 1 < GAME_WIDTH / BLOCK_SIZE && spawnPosArrayY + 1 < GAME_HEIGHT / BLOCK_SIZE) {
                        if (collisionMatrix[spawnPosArrayX][spawnPosArrayY] == null && collisionMatrix[spawnPosArrayX + 1][spawnPosArrayY] == null &&
                                collisionMatrix[spawnPosArrayX][spawnPosArrayY + 1] == null && collisionMatrix[spawnPosArrayX + 1][spawnPosArrayY + 1] == null) {
                            base = new Base(gamePane, spawnPosArrayX, spawnPosArrayY, lifePoints, collisionMatrix);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


    //////////////////////////////SCREENS////////////////////////////////////////
    private void showLoseScreen() {
        isGamePaused = true;
        overlay.setVisible(true);
        overlay.toFront();
        createGamePanel();
        exitButton.toFront();
        exitButton.setVisible(true);
        createRetryButton(GAME_WIDTH / 2 - 99, GAME_HEIGHT / 2 - 40);
        InfoLabel youLoseLabel = new InfoLabel("YOU LOSE!", ((double) GAME_WIDTH / 2 - 100), GAME_HEIGHT / 2 - 180, 40);
        gamePane.getChildren().add(youLoseLabel);
    }

    private void showWinScreen() {
        isGamePaused = true;
        overlay.setVisible(true);
        overlay.toFront();
        createGamePanel();
        exitButton.toFront();
        exitButton.setVisible(true);
        createGoToMenuButton(GAME_WIDTH / 2 - 99, GAME_HEIGHT / 2 - 40);
        InfoLabel youWonLabel = new InfoLabel("YOU WON!", ((double) GAME_WIDTH / 2 - 100), GAME_HEIGHT / 2 - 180, 40);
        gamePane.getChildren().add(youWonLabel);
    }

    private void createPausePanel() {
        overlay.setVisible(false);
        pausePanel = createGamePanel();
        pausePanel.setVisible(false);
        pauseLabel = new InfoLabel("GAME PAUSED", ((double) GAME_WIDTH / 2 - 125), GAME_HEIGHT / 2 - 180, 40);
        pauseLabel.setVisible(false);
        gamePane.getChildren().add(pauseLabel);
        createResumeButton(GAME_WIDTH / 2 - 99, GAME_HEIGHT / 2 - 40);
    }

    private void showPausePanel() {
        overlay.toFront();
        overlay.setVisible(true);
        pausePanel.toFront();
        pausePanel.setVisible(true);
        pauseLabel.toFront();
        pauseLabel.setVisible(true);
        resumeButton.toFront();
        resumeButton.setVisible(true);
        exitButton.toFront();
        exitButton.setVisible(true);
    }

    private void hidePausePanel() {
        overlay.setVisible(false);
        pausePanel.setVisible(false);
        pauseLabel.setVisible(false);
        resumeButton.setVisible(false);
        exitButton.setVisible(false);
    }
    /** */
    public Tank getPlayerOneTank() {
        return playerOneTank;
    }
    /** */
    public boolean isMatrixAvaliable()
    {
        return matrixAvaliable;
    }
    /** */
    public void setMatrixAvaliable(boolean matrixAvaliable)
    {
        this.matrixAvaliable = matrixAvaliable;
    }
    /** */
    public boolean isGamePaused()
    {
        return isGamePaused;
    }
    /** */
    public boolean isGameEnded()
    {
        return isGameEnded;
    }

    private void waterToBack()
    {
        for(ImageView i : waterList)
            i.toBack();
    }
    /** */
    public int getSecondPlayerX()
    {
        return ((TankPlayer)playerOneTank).getSecondPlayerX();
    }
    /** */
    public int getSecondPlayerY()
    {
        return ((TankPlayer)playerOneTank).getSecondPlayerY();
    }
    /** */
    public boolean getPlayerIsVisible() {
        return ((TankPlayer)playerOneTank).getIsVisible();
    }
    /** */
    public void removeStar() {
        starAvaliable = false;
        star.setVisible(false);
        ((TankPlayer)playerOneTank).setStarX(-1);
        ((TankPlayer)playerOneTank).setStarY(-1);
    }
}
