package Week6;

public class Chair {
    // list of attributes
    private String color;
    private boolean hasArms;
    private int heightInCentimeters;
    private int minimumHeightInCentimeters;
    private int maximumHeightInCentimeters;

    public int getMinimumHeightInCentimeters() {
        return minimumHeightInCentimeters;
    }

    public void setMinimumHeightInCentimeters(int minimumHeightInCentimeters) {
        this.minimumHeightInCentimeters = minimumHeightInCentimeters;
    }

    public int getMaximumHeightInCentimeters() {
        return maximumHeightInCentimeters;
    }

    public void setMaximumHeightInCentimeters(int maximumHeightInCentimeters) {
        this.maximumHeightInCentimeters = maximumHeightInCentimeters;
    }

    public String getColor() {
        return color;
    }

    public boolean hasArms() {
        return hasArms;
    }

    public void setHasArms(boolean hasArms) {
        this.hasArms = hasArms;
    }

    public int getHeightInCentimeters() {
        return heightInCentimeters;
    }

    public void setHeightInCentimeters(int heightInCentimeters) {
        this.heightInCentimeters = heightInCentimeters;
    }

    public void setColor(String color) {
        this.color = color;
  }

  public void adjustChairHeight(int centimetersToAdjust){
        heightInCentimeters += centimetersToRaise;
        if (heightInCentimeters > maximumHeightInCentimeters) {
            heightInCentimeters = maximumHeightInCentimeters;
        } else if ( heightInCentimeters < minimumHeightInCentimeters) {
            heightInCentimeters = minimumHeightInCentimeters;
        }
    }
}
