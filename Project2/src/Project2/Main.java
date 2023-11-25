package Project2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        // Creating an instance of the Vehicle class
        Vehicle car = new Vehicle("Toyota", "Camry", "Blue");

        // Displaying initial vehicle information
        System.out.println("Make: " + car.getMake());
        System.out.println("Model: " + car.getModel());
        System.out.println("Color: " + car.getColor());
        System.out.println("Odometer: " + car.getOdometer() + " km");

        // Driving the vehicle for 100 kilometers
        car.drive(100);

        // Displaying updated vehicle information after driving
        System.out.println("Odometer after driving: " + car.getOdometer() + " km");

        // Creating an instance of the InternalCombustionEngineVehicle class
        InternalCombustionEngineVehicle gasCar = new InternalCombustionEngineVehicle("Ford", "Mustang", "Red", 10, 50, 30);

        // Adding gas to the tank
        gasCar.addGas(20);

        // Driving the gas car for 50 kilometers
        boolean driveResult = gasCar.drive(50);

        // Displaying gas car information after driving
        System.out.println("Gas Car Current Liters in Tank: " + gasCar.getCurrentLitersInTank() + " liters");
        System.out.println("Gas Car Drive Result: " + (driveResult ? "Success" : "Failure"));

        // Creating an instance of the ElectricVehicle class
        ElectricVehicle ev = new ElectricVehicle("Tesla", "Model S", "Silver", 5, 50, 20);

        // Charging the electric vehicle with 10 kilowatts
        ev.charge(10);

        // Driving the electric vehicle for 30 kilometers
        boolean electricDriveResult;
        if (ev.drive(30)) electricDriveResult = true;
        else electricDriveResult = false;

        // Displaying electric vehicle information after driving
        System.out.println("Electric Vehicle Current Kilowatts in Battery: " + ev.getCurrentKilowattsInBattery() + " kWh");
        System.out.println("Electric Vehicle Drive Result: " + (electricDriveResult ? "Success" : "Failure"));
    }
}