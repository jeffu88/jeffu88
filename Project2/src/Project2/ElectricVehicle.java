package Project2;

public class ElectricVehicle {
    private final int maxKilowattsInBattery;
    private int currentKilowattsInBattery;
    private int kilometersPerKilowattHour;

    public ElectricVehicle(String make, String model, String color, int kilometersPerKilowattHour, int maxKilowattsInBattery, int currentKilowattsInBattery) {
        // Constructor implementation
        // Initialize other fields as needed
        this.kilometersPerKilowattHour = kilometersPerKilowattHour;
        this.maxKilowattsInBattery = maxKilowattsInBattery;
        this.currentKilowattsInBattery = currentKilowattsInBattery;
    }

    public void charge(int kilowattsToCharge) {
        // Implementation to charge the electric vehicle
        this.currentKilowattsInBattery += kilowattsToCharge;
        // Add logic to handle exceeding the maxKilowattsInBattery if needed
    }

    public boolean drive(int kilometers) {
        // Implementation to drive the electric vehicle
        int kilowattsNeeded = kilometers / this.kilometersPerKilowattHour;
        if (kilowattsNeeded <= this.currentKilowattsInBattery) {
            this.currentKilowattsInBattery -= kilowattsNeeded;
            return true;
        } else {
            this.currentKilowattsInBattery = 0;
            return false;
        }
    }

    public int getCurrentKilowattsInBattery() {
        // Implementation to get the current kilowatts in the battery
        return this.currentKilowattsInBattery;
    }
}
