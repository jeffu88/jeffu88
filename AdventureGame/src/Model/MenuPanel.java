package Model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
/** MenuPanel is a subscene of main scene. */
public class MenuPanel extends SubScene {

    private final static String FONT_PATH = "src/Model/Resources/Fonts/HeartbitXX.ttf";
    private final static String PANEL_IMAGE = "Model/Resources/MenuContent/PanelImage.png";
    private boolean isHidden;
    /** Constructor that initializes all the details of panel. */
    public MenuPanel(double X, double Y) {
        super(new AnchorPane(), 400, 300);
        prefWidth(400);
        prefHeight(300);

        BackgroundImage panelBackground = new BackgroundImage(new Image(PANEL_IMAGE, 400, 300, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root = (AnchorPane) this.getRoot(); //getting mainPane to set background

        root.setBackground(new Background(panelBackground));

        setLayoutX(X); //creating panel outside of the scene to create appearing animation possible
        setLayoutY(Y);

        isHidden = true;
    }
    /** Method that used to animate movement of the panel. */
    public void movePanel() { //animation of sliding
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden) { //if hidden move left
            transition.setToX(-450);
            isHidden = false;
        }
        else {//if shown move right
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }
    /** Returns variable that is used to synchronize animation with the other panels */
    public boolean isHid() {return isHidden;}
    /** Return pane that allows adding additional elements to the panel. */
    public AnchorPane getPane() { return (AnchorPane) this.getRoot(); }
}
