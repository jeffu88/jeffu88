package Model.MapElements;

import Model.SpriteAnimation;
import Model.Tanks.ColorChangerTimer;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/** Class that represents the base that players have to protect. */
public class Base {
    private AnchorPane gamePane;
    private int lifePoints;
    private int currentX;
    private int currentY;
    private ImageView baseSprite;
    private ImageView baseExplosion;
    private AudioClip sounds;

    private final static String BASE_SPRITE = "Model/MapElements/MapContent/base_sprite.png";
    final static String EXPLOSION_SPRITE_SHEET = "Model/Resources/tankSprites/TankExplosionSpriteSheet.png";
    final static String BASE_EXPLOSION_SOUND = "../Resources/TankSounds/tank_explosion_sound.wav";
    private final static int BLOCK_SIZE = 50;
    /** Creates base at given place on the map. */
    public Base(AnchorPane gamePane, int spawnX, int spawnY, int lifePoints, String[][] collisionMatrix)
    {
        this.gamePane = gamePane;
        currentX = spawnX;
        currentY = spawnY;

        if (lifePoints<1)
            this.lifePoints = 5;
        else
            this.lifePoints = lifePoints;

        collisionMatrix[spawnX][spawnY] = "Base";       //Adding object to collision matrix
        collisionMatrix[spawnX+1][spawnY] = "Base";
        collisionMatrix[spawnX][spawnY+1] = "Base";
        collisionMatrix[spawnX+1][spawnY+1] = "Base";

        baseSprite = new ImageView(BASE_SPRITE);
        baseSprite.setClip(new ImageView(BASE_SPRITE));
        baseSprite.setLayoutX(50*spawnX);
        baseSprite.setLayoutY(50*spawnY);

        gamePane.getChildren().add(baseSprite);
    }
    /** */
    public int getLifePoints() {
        return lifePoints;
    }
    /** */
    public int getCurrentX() {
        return currentX;
    }
    /** */
    public int getCurrentY() {
        return currentY;
    }
    /** Method that is responsible for taking damage and playing hit animation. */
    public void takeDamage() {
        if(lifePoints>0) {
            hitAnimation();
            lifePoints--;
        }

        if(lifePoints==0) {
            baseDestruction();
        }
    }

    private void baseDestruction() {
        playExplosionSound();
        baseDestructionAnimation();
        Platform.runLater(()->gamePane.getChildren().remove(baseSprite));
    }

    private void baseDestructionAnimation() {
        baseExplosion = new ImageView(EXPLOSION_SPRITE_SHEET);
        baseExplosion.setFitWidth(2*BLOCK_SIZE);
        baseExplosion.setFitHeight(2*BLOCK_SIZE);
        baseExplosion.setLayoutX(baseSprite.getLayoutX() + BLOCK_SIZE/2); //placing animation
        baseExplosion.setLayoutY(baseSprite.getLayoutY());
        baseExplosion.setViewport(new Rectangle2D(0, 0, 128, 128)); //preventing from showing whole sprite sheet

        final Animation explosionAnimation = new SpriteAnimation(
                baseExplosion,
                Duration.millis(1000),
                12, 12,
                0, 0,
                128, 128
        );
        explosionAnimation.setCycleCount(1);
        explosionAnimation.setOnFinished(event -> gamePane.getChildren().remove(baseExplosion));    //removing sprite after animation is done
        Platform.runLater(()->gamePane.getChildren().add(baseExplosion));
        explosionAnimation.play();
    }

    void playExplosionSound() {
        sounds = new AudioClip(this.getClass().getResource(BASE_EXPLOSION_SOUND).toExternalForm());
        sounds.setCycleCount(1);
        sounds.play(0.8);
    }
    void hitAnimation() {
        ColorChangerTimer timer = new ColorChangerTimer(baseSprite);
    }
}
