package Project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ElectricVehicle {


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
