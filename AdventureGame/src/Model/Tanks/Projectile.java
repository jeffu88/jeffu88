package Model.Tanks;

import Model.MapElements.Base;
import Model.MapElements.BrickBlock;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
/** Class that is responsible for every projectile object. */
public class Projectile
{
    private AnchorPane gamePane;
    private String[][] positionMatrix;
    private List<Tank> tankList;
    private char directionOfMovement;
    private ImageView projectileSprite;
    private int currentX;
    private int currentY;
    private int moveIterator;
    private boolean hitConfirmed;
    private Base base;
    private ArrayList<BrickBlock> brickList;
    private ArrayList<ImageView> waterList;

    private final static String PROJECTILE_SPRITE = "Model/Resources/Projectiles/shotThin.png";
    /** Creates projectile and modifies the position of sprite depends on direction of the tank movement. */
    public Projectile(AnchorPane gamePane, int spawnPosArrayX, int spawnPosArrayY, String[][] positionMatrix, char directionOfMovement,
                      List<Projectile> projectileList, List<Tank> tankList, Base base, ArrayList<BrickBlock> brickList,ArrayList<ImageView> waterList)
    {
        this.gamePane = gamePane;
        this.positionMatrix = positionMatrix;
        this.directionOfMovement = directionOfMovement;
        this.tankList = tankList;
        currentX = spawnPosArrayX;
        currentY = spawnPosArrayY;
        this.brickList = brickList;
        this.waterList = waterList;

        projectileSprite = new ImageView(PROJECTILE_SPRITE);  //loading sprite
        if (directionOfMovement == 'L')
        {          //Depending on direction, rotate and move sprite to good location
            projectileSprite.setRotate(-90);
            projectileSprite.setLayoutY(spawnPosArrayY * 50 + 12);
            projectileSprite.setLayoutX(spawnPosArrayX * 50 - 5);
        } else if (directionOfMovement == 'R')
        {
            projectileSprite.setRotate(90);
            projectileSprite.setLayoutY(spawnPosArrayY * 50 + 12);
            projectileSprite.setLayoutX(spawnPosArrayX * 50 + 50);
        } else if (directionOfMovement == 'D')
        {
            projectileSprite.setRotate(-180);
            projectileSprite.setLayoutX(spawnPosArrayX * 50 + 21);
            projectileSprite.setLayoutY(spawnPosArrayY * 50 + 35);
        } else
        {
            projectileSprite.setLayoutX(spawnPosArrayX * 50 + 21);
            projectileSprite.setLayoutY(spawnPosArrayY * 50 - 10);
        }
        projectileList.add(this);
        Platform.runLater(()->{gamePane.getChildren().add(projectileSprite);});
        try
        {
            Thread.sleep(1);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        this.base = base;
        moveIterator = 0;
        hitConfirmed = false;
    }
    /** */
    public void hideProjectile()
    {
        Platform.runLater(()->{gamePane.getChildren().remove(projectileSprite);});
        try
        {
            Thread.sleep(1);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    /** Boolean value that indicates if the projectile hit obstacles/tank. */
    public boolean getHitConfirmed()
    {
        return hitConfirmed;
    }

    ///////////////////////////////////DAMAGE SYSTEM////////////////////////////
    /** Method that is responsible for finding the hit tank and deal damage. */
    public boolean damageTank(int positionArrayX, int positionArrayY)
    {
        int IDToFind;
        //Geting hit tank ID from positionMatrix
        if (positionArrayX >= 0 && positionArrayX < Tank.GAME_WIDTH / Tank.BLOCK_SIZE && positionArrayY >= 0 && positionArrayY < Tank.GAME_HEIGHT / Tank.BLOCK_SIZE)
        {
            try
            {
                IDToFind = Integer.parseInt(positionMatrix[positionArrayX][positionArrayY]); //Checking if hit object is Tank
            } catch (NumberFormatException e)
            { //if not function returns false
                if (positionMatrix[positionArrayX][positionArrayY] == "Base")
                {
                    base.takeDamage();
                    return true;
                }
                else if (positionMatrix[positionArrayX][positionArrayY] == "Brick")
                {
                    for(BrickBlock i : brickList)
                    {
                        if(i.getCurrentX() == positionArrayX && i.getCurrentY() == positionArrayY)
                        {
                            i.takeDamage();
                            return true;
                        }
                    }
                    return false;
                }
                else
                    return false;
            }

            for (Tank tank : tankList)
            {  //Looking hit tank in tanks list, by it`s ID
                if (IDToFind == tank.getID())
                    tank.takeDamage();  //when found get damage
            }
            return true;
        } else
            return false;
    }

    //////////////////////////////COLLISION SYSTEM//////////////////////////////////
    protected boolean checkIfDownEmpty()
    {
        if (currentY < Tank.GAME_HEIGHT / Tank.BLOCK_SIZE - 1)
        {
            if (positionMatrix[currentX][currentY + 1] == null)
                return true;
            else
                return false;
        } else
            return false;
    }

    protected boolean checkIfUpEmpty()
    {
        if (currentY > 0)
        {
            if (positionMatrix[currentX][currentY - 1] == null)
                return true;
            else
                return false;
        } else
            return false;
    }

    protected boolean checkIfRightEmpty()
    {
        if (currentX < Tank.GAME_WIDTH / Tank.BLOCK_SIZE - 1)
        {
            if (positionMatrix[currentX + 1][currentY] == null)
                return true;
            else
                return false;
        } else
            return false;
    }

    protected boolean checkIfLeftEmpty()
    {
        if (currentX > 0)
        {
            if (positionMatrix[currentX - 1][currentY] == null)
                return true;
            else
                return false;
        } else
            return false;
    }
    /** */
    public void moveProjectile()
    {
        if (moveIterator == 0)
            startMovement();
        else
            continueMovement();
    }

    ////////////////////////////PROJECTILES MOVEMENT/////////////////////////////////////
    private void startMovement()
    {
        if (directionOfMovement == 'R')
        {
            projectileSprite.setLayoutX(projectileSprite.getLayoutX() + 10);

            if (!checkIfRightEmpty())
            {  //if next block is not empty, that means the projectile hit something
                hitConfirmed = true;
                hideProjectile();
                damageTank(currentX + 1, currentY);
            } else
                currentX++;         //if next block is empty, the projectile moves on
        } else if (directionOfMovement == 'L')
        {
            projectileSprite.setLayoutX(projectileSprite.getLayoutX() - 10);

            if (!checkIfLeftEmpty())
            {
                hitConfirmed = true;
                hideProjectile();
                damageTank(currentX - 1, currentY);
            } else
                currentX--;
        } else if (directionOfMovement == 'U')
        {
            projectileSprite.setLayoutY(projectileSprite.getLayoutY() - 10);

            if (!checkIfUpEmpty())
            {
                hitConfirmed = true;
                hideProjectile();
                damageTank(currentX, currentY - 1);
            } else
                currentY--;
        } else if (directionOfMovement == 'D')
        {
            projectileSprite.setLayoutY(projectileSprite.getLayoutY() + 10);

            if (!checkIfDownEmpty())
            {
                hitConfirmed = true;
                hideProjectile();
                damageTank(currentX, currentY + 1);
            } else
                currentY++;
        }
        moveIterator = Tank.BLOCK_SIZE / 10 - 1;
    }

    private void continueMovement()
    {
        moveIterator--;
        if (directionOfMovement == 'R')
        {
            projectileSprite.setLayoutX(projectileSprite.getLayoutX() + 10);
        } else if (directionOfMovement == 'L')
        {
            projectileSprite.setLayoutX(projectileSprite.getLayoutX() - 10);
        } else if (directionOfMovement == 'U')
        {
            projectileSprite.setLayoutY(projectileSprite.getLayoutY() - 10);
        } else if (directionOfMovement == 'D')
        {
            projectileSprite.setLayoutY(projectileSprite.getLayoutY() + 10);
        }

    }

}
