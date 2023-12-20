package Model.MapElements;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
/** Basic obstacle that is able to be damaged and destroyed.*/
public class BrickBlock
{
    private AnchorPane gamePane;
    private int lifePoints;
    private int currentX;
    private int currentY;
    private ImageView currentSprite;
    private String[][] collisionMatrix;

    private static final String BRICK1 = "Model/MapElements/MapPieces/Brick1.png";
    private static final String BRICK2 = "Model/MapElements/MapPieces/Brick2.png";
    private static final String BRICK3 = "Model/MapElements/MapPieces/Brick3.png";
    /** Creating block object and placing it on the exact place on the map.*/
    public BrickBlock(AnchorPane gamePane, int spawnX, int spawnY, int lifePoints, String[][] collisionMatrix)
    {
        this.gamePane = gamePane;
        currentX = spawnX;
        currentY = spawnY;
        this.collisionMatrix = collisionMatrix;

        if (lifePoints < 1)
            this.lifePoints = 3;
        else
            this.lifePoints = lifePoints;

        currentSprite = null;
        switch (lifePoints)
        {
            case 1:
            {
                currentSprite = new ImageView(BRICK1);
                break;
            }

            case 2:
            {
                currentSprite = new ImageView(BRICK2);
                break;
            }

            case 3:
            {
                currentSprite = new ImageView(BRICK3);
                break;
            }
        }
        collisionMatrix[spawnX][spawnY] = "Brick";
        currentSprite.setLayoutX(50 * spawnX);
        currentSprite.setLayoutY(50 * spawnY);
        currentSprite.setFitWidth(50);
        currentSprite.setFitHeight(50);

        gamePane.getChildren().add(currentSprite);
    }
    /** */
    public int getLifePoints()
    {
        return lifePoints;
    }
    /** */
    public int getCurrentX()
    {
        return currentX;
    }
    /** */
    public int getCurrentY()
    {
        return currentY;
    }
    /** Method that is responsible for taking damage and chagning sprite after hit. */
    public void takeDamage()
    {
        if (lifePoints > 0)
        {
            lifePoints--;

            switch (lifePoints)
            {
                case 1:
                {
                    currentSprite.setImage(new Image(BRICK1));
                    break;
                }

                case 2:
                {
                    currentSprite.setImage(new Image(BRICK2));
                    break;
                }
            }
        }

        if (lifePoints == 0)
        {
            collisionMatrix[currentX][currentY] = null;
            brickDestruction();
        }
    }

    private void brickDestruction()
    {
        Platform.runLater(()->{gamePane.getChildren().remove(currentSprite);});
    }
}
