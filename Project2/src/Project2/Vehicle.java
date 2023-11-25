package Project2;

public class Vehicle {
    private String make;
    private String model;
    private String color;
    private int odometer;

    public Vehicle(String make, String model, String color) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.odometer = 0;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getColor() {
        return this.color;
    }

    public int getOdometer() {
        return this.odometer;
    }

    public void drive(int kilometers) {
        this.odometer += kilometers;
    }
}
