package MidTerm;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To The CIS1500 Midterm Luncheon");
        Scanner scanner = new Scanner(System.in);

        int chickenCount = 0;
        int beefCount = 0;
        int vegetarianCount = 0;
        int totalGuests = 0;

        boolean done = false;

        while (!done) {
            System.out.println("Enter the choice of the guest (chicken, beef, vegetarian), or 'done' to finish:");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("chicken")) {
                chickenCount++;
                totalGuests++;
            } else if (choice.equals("beef")) {
                beefCount++;
                totalGuests++;
            } else if (choice.equals("vegetarian")) {
                vegetarianCount++;
                totalGuests++;
            } else if (choice.equals("done")) {
                done = true;
            } else {
                System.out.println("Invalid choice. Please enter 'chicken', 'beef', 'vegetarian', or 'done'.");
            }
        }

        System.out.println("RSVP Summary:");
        System.out.println("Total Guests: " + totalGuests);
        System.out.println("Chicken: " + chickenCount + " guests");
        System.out.println("Beef: " + beefCount + " guests");
        System.out.println("Vegetarian: " + vegetarianCount + " guests");
        System.out.println("Thank You and Enjoy!");
    }
}