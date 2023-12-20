import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import View.ViewManager;

import java.util.ArrayList;
import java.util.List;
/** Main class that starts the whole application. */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        ViewManager manager = new ViewManager();

        primaryStage = manager.getMainStage();
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
