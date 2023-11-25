package Project2;

public class InternalCombustionEngineVehicle {
    private int currentLitersInTank;
    private int kilometersPerLiter;
    private int maxLitersInTank;

    public InternalCombustionEngineVehicle(String make, String model, String color, int kilometersPerLiter, int maxLitersInTank, int currentLitersInTank) {
        // No need for super() in this case, as InternalCombustionEngineVehicle doesn't explicitly extend another class
        this.kilometersPerLiter = kilometersPerLiter;
        this.maxLitersInTank = maxLitersInTank;
        this.currentLitersInTank = currentLitersInTank;
    }

    public void addGas(int litersOfGas) {
        // Implementation to add gas to the tank
        this.currentLitersInTank += litersOfGas;
        // Add logic to handle exceeding the maxLitersInTank if needed
    }

    public boolean drive(int kilometers) {
        // Implementation to drive the vehicle
        int gasNeeded = kilometers / this.kilometersPerLiter;
        if (gasNeeded <= this.currentLitersInTank) {
            this.currentLitersInTank -= gasNeeded;
            return true;
        } else {
            this.currentLitersInTank = 0;
            return false;
        }
    }

    public int getCurrentLitersInTank() {
        // Implementation to get the current liters in the tank
        return this.currentLitersInTank;
    }
}