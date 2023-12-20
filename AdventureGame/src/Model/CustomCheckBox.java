package Model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/** CustomCheckBox creates checbox that is used to select map. */
public class CustomCheckBox extends VBox
{
    private ImageView circleImage;
    private ImageView mapImage;
    private String circleNotChoosen = "Model/Resources/MenuContent/circle_empty.png";
    private String circleChoosen = "Model/Resources/MenuContent/circle_not_empty.png";
    private MAP map;
    private boolean isMapChoosen;
    /** Initializes the checkbox with layout. */
    public CustomCheckBox(MAP map)
    {
        circleImage = new ImageView(circleNotChoosen);
        circleImage.setFitHeight(50);
        circleImage.setFitWidth(50);
        this.map = map;
        mapImage = new ImageView(map.getMapUrl());
        mapImage.setFitWidth(300);
        mapImage.setFitHeight(300);
        InfoLabel infoLabel = new InfoLabel(map.getMapDBName(),0,0,52);
        infoLabel.setAlignment(Pos.CENTER);
        isMapChoosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(0);
        this.getChildren().add(circleImage);
        this.getChildren().add(infoLabel);
        this.getChildren().add(mapImage);
    }

    public String getMapName()
    {
        return map.getMapDBName();
    }

    public boolean CheckisMapChoosen()
    {
        return isMapChoosen;
    }

    public void setIsMapChoosen(boolean isMapChoosen)
    {
        this.isMapChoosen = isMapChoosen;
        String image = this.isMapChoosen ? circleChoosen : circleNotChoosen;
        circleImage.setImage(new Image(image,50,50,false,true));
    }


}
