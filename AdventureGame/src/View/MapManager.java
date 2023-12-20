package View;

import Model.MapElements.BrickBlock;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
/** MapManager is responsible for generating map from database data. */
public class MapManager
{
    //Legend:
    //0 = desert;
    //3 = brick3
    //2 = brick2
    //1 = brick1
    //4 = wall
    //5 = bush
    //6 = meadow
    //7 = water
    //F = player one
    //S = player two
    //N = neutral tank
    //B = base
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private ArrayList<ImageView> bushList;
    private ArrayList<BrickBlock> brickList;
    private ArrayList<ImageView> waterList;
    private int GAME_WIDTH;
    private int GAME_HEIGHT;
    private int BLOCK_SIZE;
    private int BACKGROUND_TYPE;
    private DataBaseConnector dbConnector;
    private int playerOneX;
    private int playerOneY;
    private int baseX;
    private int baseY;
    private boolean baseCondition = true;
    private boolean neutralCounter = false;
    private ArrayList<Point> neutralList;

    private static final String BRICK1 = "Model/MapElements/MapPieces/Brick1.png";
    private static final String BRICK2 = "Model/MapElements/MapPieces/Brick2.png";
    private static final String BRICK3 = "Model/MapElements/MapPieces/Brick3.png";
    private static final String BUSH = "Model/MapElements/MapPieces/Bush.png";
    private static final String WALL = "Model/MapElements/MapPieces/Wall.png";
    private static final String DESSERT = "Model/MapElements/MapPieces/Background.png";
    private static final String MEADOW = "Model/MapElements/MapPieces/Meadow.png";
    private static final String WATER = "Model/MapElements/MapPieces/Water.gif";

    private static String[][] positionMatrix;
    private static String map_stream = "";

    /** Creates Map Manager. All of references in constructor are needed to create map.*/
    public MapManager(AnchorPane gamePane, Scene gameScene, Stage gameStage , DataBaseConnector dbConnector)
    {
        this.gamePane = gamePane;
        this.gameScene = gameScene;
        this.gameStage = gameStage;
        bushList = new ArrayList<>();
        this.dbConnector = dbConnector;
        GAME_HEIGHT = dbConnector.getGame_height();
        GAME_WIDTH = dbConnector.getGame_width();
        BLOCK_SIZE = 50;
        map_stream = dbConnector.getMap_stream();
        BACKGROUND_TYPE = dbConnector.getBackground_type();
        positionMatrix = new String[GAME_WIDTH/BLOCK_SIZE][GAME_HEIGHT/BLOCK_SIZE];
        brickList = new ArrayList<>();
        neutralList = new ArrayList<>();
        waterList = new ArrayList<>();
    }

    /** Loads the background images to the game pane */
    public void createBackground()
    {
        Image backgroundGameImage;
        String backgroundSprite = null;
        switch (BACKGROUND_TYPE)
        {
            case 0:
            {
                backgroundSprite = DESSERT;
                break;
            }
            case 6:
            {
                backgroundSprite = MEADOW;
                break;
            }
        }
        backgroundGameImage = new Image(backgroundSprite, BLOCK_SIZE, BLOCK_SIZE, false, true);
        BackgroundImage backgroundGame = new BackgroundImage(backgroundGameImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(backgroundGame));
    }

    /** Creates position matrix that is used to control tanks and detect collisions.
     * It is also used to generate obstacles on the map. */

    public String[][] createPositionMatrix()
    {
        int counter = 0;
        char[] tmp = map_stream.toCharArray();
        String picture = BUSH;
        System.out.println(tmp);
        System.out.println(GAME_HEIGHT);
        System.out.println(GAME_WIDTH);

        for(int i=0; i<GAME_WIDTH/BLOCK_SIZE; i++ )
            for (int j=0; j<GAME_HEIGHT/BLOCK_SIZE; j++ )
            {
                boolean flag = true;
                switch (tmp[counter])
                {
                    case '3':
                    {
                        BrickBlock brickBlock = new BrickBlock(gamePane,j,i,3,positionMatrix);
                        brickList.add(brickBlock);
                        flag = false;
                        break;
                    }
                    case '2':
                    {
                        BrickBlock brickBlock = new BrickBlock(gamePane,j,i,2,positionMatrix);
                        brickList.add(brickBlock);
                        flag = false;
                        break;
                    }
                    case '1':
                    {
                        BrickBlock brickBlock = new BrickBlock(gamePane,j,i,1,positionMatrix);
                        brickList.add(brickBlock);
                        flag = false;
                        break;
                    }
                    case '4':
                    {
                        picture = WALL;
                        positionMatrix[j][i] = "Wall";
                        break;
                    }
                    case 'F':
                    {
                        playerOneX = j;
                        playerOneY = i;
                        flag = false;
                        break;
                    }
                    case 'B':
                    {
                        if (baseCondition)
                        {
                            baseX = j;
                            baseY = i;
                            baseCondition = false;
                        }
                        flag = false;
                        break;
                    }
                    case 'N':
                    {
                        neutralCounter = true;
                        neutralList.add(new Point(j,i));
                        flag = false;
                        break;
                    }
                    case '0':
                    {
                        flag = false;
                        break;
                    }
                    case '6':
                    {
                        flag = false;
                        break;
                    }
                    case '5':
                    {
                        picture = BUSH;
                        break;
                    }
                    case '7':
                    {
                        picture = WATER;
                        break;
                    }
                }
                if(flag)
                {
                    ImageView imageView = new ImageView(new Image(picture, BLOCK_SIZE, BLOCK_SIZE, false, true));
                    imageView.setLayoutX(j * BLOCK_SIZE);
                    imageView.setLayoutY(i * BLOCK_SIZE);

                    if (tmp[counter] == '5')
                        bushList.add(imageView);

                    if(tmp[counter] == '7')
                        waterList.add(imageView);

                    gamePane.getChildren().add(imageView);
                }

                counter++;
            }
        return positionMatrix;
    }

    /** Pushes bushes to front of the game pane. */
    public void bushToFront()
    {
        for(ImageView i : bushList)
        {
            i.toFront();
        }
    }

    /** Returns array list that contains positions of neutral tanks.
     * It is used to spawn tanks in game manager. */
    public ArrayList<Point> getNeutralList()
    {
        return neutralList;
    }

    /** Returns player one X position. */
    public int getPlayerOneX()
    {
        return playerOneX;
    }

    /** Returns player one Y position. */
    public int getPlayerOneY()
    {
        return playerOneY;
    }

    /** Returns base X position. */
    public int getBaseX()
    {
        return baseX;
    }

    /** Returns base Y position. */
    public int getBaseY()
    {
        return baseY;
    }

    /** Returns counter of neutral tanks. */
    public boolean getNeutralCounter()
    {
        return neutralCounter;
    }

    /** Returns list of brick objects. */
    public ArrayList<BrickBlock> getBrickList()
    {
        return brickList;
    }

    /** Returns list of water gifs. */
    public ArrayList<ImageView> getWaterList()
    {
        return waterList;
    }
}
