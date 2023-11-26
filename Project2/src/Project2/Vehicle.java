package Project2;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

class VehicleTest {

    @Test
    public void testGetMake() {
        Vehicle car = new Vehicle("Toyota", "Camry", "Blue");
        assertEquals("Toyota", car.getMake());
    }

    @Test
    public void testGetModel() {
        Vehicle car = new Vehicle("Toyota", "Camry", "Blue");
        assertEquals("Camry", car.getModel());
    }

    @Test
    public void testGetColor() {
        Vehicle car = new Vehicle("Toyota", "Camry", "Blue");
        assertEquals("Blue", car.getColor());
    }

    @Test
    public void testGetOdometer() {
        Vehicle car = new Vehicle("Toyota", "Camry", "Blue");
        assertEquals(0, car.getOdometer());
    }

    @Test
    public void testDrive() {
        Vehicle car = new Vehicle("Toyota", "Camry", "Blue");

        // Test driving for 100 kilometers
        car.drive(100);
        assertEquals(100, car.getOdometer());

        // Test driving for another 50 kilometers
        car.drive(50);
        assertEquals(150, car.getOdometer());


    }
}
