package Project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InternalCombustionEngineVehicle {


    public class InternalCombustionEngineVehicleTest {

        @Test
        public void testAddGas() {
            InternalCombustionEngineVehicle car = new InternalCombustionEngineVehicle("Ford", "Mustang", "Red", 10, 50, 30);

            // Test adding 20 liters of gas
            car.addGas(20);
            assertEquals(50, car.getCurrentLitersInTank()); // Ensure it doesn't exceed maxLitersInTank
        }

        @Test
        public void testDriveWithEnoughGas() {
            InternalCombustionEngineVehicle car = new InternalCombustionEngineVehicle("Ford", "Mustang", "Red", 10, 50, 30);

            // Test driving for 100 kilometers with enough gas
            boolean result = car.drive(100);
            assertTrue(result);
            assertEquals(20, car.getCurrentLitersInTank()); // Ensure gas is consumed correctly
        }

        @Test
        public void testDriveWithoutEnoughGas() {
            InternalCombustionEngineVehicle car = new InternalCombustionEngineVehicle("Ford", "Mustang", "Red", 10, 50, 20);

            // Test driving for 100 kilometers without enough gas
            boolean result = car.drive(100);
            assertFalse(result);
            assertEquals(0, car.getCurrentLitersInTank()); // Ensure gas is depleted
        }
    }

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