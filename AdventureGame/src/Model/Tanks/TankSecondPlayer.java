package Model.Tanks;

import Model.MapElements.Base;
import Model.MapElements.BrickBlock;
import View.DataBaseConnector;
import View.GameViewManager;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
/** TankSecondPlayer is responsible for visualization and animation of second player tank. */
public class TankSecondPlayer extends Tank{

    private static final String TANK_SPRITE_URL= "Model/Resources/tankSprites/tankBlue.png";
    private final static String HEART_SPRITE_FULL = "Model/Resources/tankSprites/heart_full_blue.png";
    private final static String HEART_SPRITE_EMPTY = "Model/Resources/tankSprites/heart_empty.png";
    private List<ImageView> lifePointIndicator;

    /** Constructor that initializes tank object giving access to all needed variables. */
    public TankSecondPlayer(AnchorPane gamePane, int spawnPosX, int spawnPosY,
                            List<Tank> tankList, String[][] collisionMatrix, Base base, DataBaseConnector dataBaseConnector,
                            ArrayList<BrickBlock> brickList, ArrayList<ImageView> waterList, GameViewManager gameViewManager) {

        super("PLAYER", gamePane, spawnPosX, spawnPosY, TANK_SPRITE_URL, tankList, collisionMatrix,
                5, base,dataBaseConnector, brickList, waterList, gameViewManager);

        lifePointIndicator = new ArrayList<>();
        createLifeIndicator();
    }
    ///////////////////////////////////MOVEMENT////////////////////////////
    void startTankMovement(boolean isLeftKeyPressed, boolean isRightKeyPressed, boolean isUpKeyPressed, boolean isDownKeyPressed) {
        //Checking if only one key is pressed
        if(isLeftKeyPressed && !isRightKeyPressed && !isUpKeyPressed && !isDownKeyPressed) {
            if(angle == 90)
                fullSpin=true;
            else
                fullSpin=false;

            directionOfMovement = 'L';  //giving direction to continue movement
            allowedToMove = checkIfLeftEmpty();
            if (moveTankLeftOneIteration()) {
                positionMatrix[currentX-1][currentY]=Integer.toString(ID);
                positionMatrix[currentX][currentY]=null;
                currentX--;
            }
            moveIterator = BLOCK_SIZE/5 - 1;           //moveIterator is set to 9, so continueTankMovement will be called in next frame instead of startTankMovement
        }

        if(isRightKeyPressed && !isLeftKeyPressed && !isUpKeyPressed && !isDownKeyPressed) {
            if(angle == -90)
                fullSpin=true;
            else
                fullSpin=false;

            directionOfMovement = 'R';  //giving direction to continue movement
            allowedToMove = checkIfRightEmpty();
            if (moveTankRightOneIteration()) {
                positionMatrix[currentX+1][currentY]=Integer.toString(ID);
                positionMatrix[currentX][currentY]=null;
                currentX++;
            }
            moveIterator = BLOCK_SIZE/5 - 1;            //moveIterator is set to 9, so continueTankMovement will be called in next frame instead of startTankMovement
        }

        if(isUpKeyPressed && !isDownKeyPressed && !isLeftKeyPressed && !isRightKeyPressed) {
            if(angle == -180 || angle == 180)
                fullSpin=true;
            else
                fullSpin=false;

            directionOfMovement = 'U';  //giving direction to continue movement
            allowedToMove = checkIfUpEmpty();
            if (moveTankUpOneIteration()) {
                positionMatrix[currentX][currentY-1]=Integer.toString(ID);
                positionMatrix[currentX][currentY]=null;
                currentY--;
            }
            moveIterator = BLOCK_SIZE/5 - 1;           //moveIterator is set to 9, so continueTankMovement will be called in next frame instead of startTankMovement
        }

        if(isDownKeyPressed && !isUpKeyPressed && !isLeftKeyPressed && !isRightKeyPressed) {
            if(angle == 0)
                fullSpin=true;
            else
                fullSpin=false;

            directionOfMovement = 'D';  //giving direction to continue movement
            allowedToMove = checkIfDownEmpty();
            if (moveTankDownOneIteration()) {
                positionMatrix[currentX][currentY+1]=Integer.toString(ID);
                positionMatrix[currentX][currentY]=null;
                currentY++;
            }
            moveIterator = BLOCK_SIZE/5 - 1;           //moveIterator is set to 9, so continueTankMovement will be called in next frame instead of startTankMovement
        }
    }

    private void continueTankMovement() {   //function to continue movement started by pressing button, that make sure tank moves only by 50 pixels
        moveIterator--;

        if(directionOfMovement =='L')
            moveTankLeftOneIteration();

        if(directionOfMovement =='R')
            moveTankRightOneIteration();

        if(directionOfMovement == 'U')
            moveTankUpOneIteration();

        if(directionOfMovement == 'D')
            moveTankDownOneIteration();
    }

    /** Method responsible for start and continue tank movement. The move iterator is responsible for
     * maintaining position on the middle of the map block after completing movement. */
    public void moveTank(boolean isLeftKeyPressed, boolean isRightKeyPressed, boolean isUpKeyPressed, boolean isDownKeyPressed) {
        if(moveIterator>0)
            continueTankMovement();
        else
            startTankMovement(isLeftKeyPressed, isRightKeyPressed, isUpKeyPressed, isDownKeyPressed);
    }

    ///////////////////////////////////SHOOTING////////////////////////////
    /** Method that is responsible for spawning projectiles and creating cooldown.
     * If the tank is destroyed it also hides all of the maintaining projectiles.*/
    public void moveProjectiles(boolean isShootKeyPressed) {
        if(isShootKeyPressed && shootDelayTimer.getCanShoot()) {
            shoot();
            shootDelayTimer.afterShootDelay(400);
        }

        for (int x = 0; x<listOfActiveProjectiles.size(); x++) {
            listOfActiveProjectiles.get(x).moveProjectile();
            if( listOfActiveProjectiles.get(x).getHitConfirmed())
                listOfActiveProjectiles.remove(x);  //if projectile hit anything it is deleted
        }
    }

    ///////////////////////////////////DAMAGE////////////////////////////
    void takeDamage() {
        lifePoints--;
        lifeIndicatorEmptyHeart(); //empty one heart
        playHitSound();
        hitAnimation();
    }

    private void createLifeIndicator() {
        ImageView heart;
        for (int iterator = 0; iterator < lifePoints; iterator++) {
            heart = new ImageView(HEART_SPRITE_FULL);
            heart.setLayoutX(BLOCK_SIZE*(iterator)+8);
            heart.setLayoutY(8);
            lifePointIndicator.add(heart);
            gamePane.getChildren().add(heart);
        }
    }

    private void lifeIndicatorEmptyHeart() {
        Image emptyHeart = new Image(HEART_SPRITE_EMPTY);
        if(lifePoints<=0)
            Platform.runLater(()->lifePointIndicator.get(0).setImage(emptyHeart));
        else {
            try {
                Platform.runLater(() -> lifePointIndicator.get(lifePoints).setImage(emptyHeart));
            }
            catch (ArrayIndexOutOfBoundsException e) {

            }
        }
    }
    /** Method that is used to move life indicator to the front of the game pane. */
    public void heartsToFront()
    {
        for(ImageView heart: lifePointIndicator)
        {
            heart.toFront();
        }
    }
}
