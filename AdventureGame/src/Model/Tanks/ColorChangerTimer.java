package Model.Tanks;

import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;
/** Timer that is responsible for changing damage tank color. */
public class ColorChangerTimer {
    Timer timer;
    /** Creating the timer and modifying sprite color. */
    public ColorChangerTimer(ImageView sprite) {
        timer = new Timer();
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1.0);     //changing saturation to avoid mixing colours

        Blend hitChange = new Blend(
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput(
                        0,
                        0,
                        sprite.getImage().getWidth(),
                        sprite.getImage().getHeight(),
                        Color.RED       //changing color to red
                )
        );
        sprite.setEffect(hitChange);
        timer.schedule(new colourDeleter(sprite), 175); //creating timer to disable effect after certain time
    }

    class colourDeleter extends TimerTask {
        ImageView sprite;

        public colourDeleter(ImageView sprite) {
            super();
            this.sprite = sprite;
        }

        public void run() {
            sprite.setEffect(null);
            timer.cancel();
        }
    }
}
