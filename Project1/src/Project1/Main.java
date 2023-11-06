package Project1;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;

        do {
            int distanceFromSurface = 10;
            int xAxisTilt = random.nextInt(21) - 10; // Random value between -10 and 10
            int yAxisTilt = random.nextInt(21) - 10; // Random value between -10 and 10
            boolean selfDestructActivated = false;

            System.out.println("The Lunar Lander Simulator!");
            System.out.println("Welcome to the 1979 Space age");
            System.out.println("You are starting 10 units above the lunar surface.");
            System.out.println("Try landing safely, happy travels!");
            System.out.println("Your initial x-axis tilt: " + xAxisTilt);
            System.out.println("Your initial y-axis tilt: " + yAxisTilt);

            while (distanceFromSurface > 0) {
                System.out.println("Commands: x+, x-, y+, y-, thruster, do nothing, self destruct");
                System.out.print("Enter a command: ");
                String command = scanner.next();

                if (selfDestructActivated) {
                    System.out.print("Enter self-destruct cancellation code: ");
                    String cancelCode = scanner.next();
                    if (cancelCode.equals("cancel")) {
                        selfDestructActivated = false;
                    } else {
                        System.out.println("Invalid cancellation code. Self-destruct is irreversible.");
                    }
                } else {
                    switch (command) {
                        case "x+":
                            xAxisTilt++;
                            break;
                        case "x-":
                            xAxisTilt--;
                            break;
                        case "y+":
                            yAxisTilt++;
                            break;
                        case "y-":
                            yAxisTilt--;
                            break;
                        case "thruster":
                            distanceFromSurface += 2;
                            break;
                        case "do nothing":
                            break;
                        case "self destruct":
                            System.out.println("Self-destruct sequence initiated. Enter 'cancel' to abort.");
                            selfDestructActivated = true;
                            break;
                        default:
                            System.out.println("Invalid command. Valid commands are: x+, x-, y+, y-, thruster, do nothing, self destruct");
                            break;
                    }
                }

                distanceFromSurface--;

                if (distanceFromSurface == 0 && (xAxisTilt != 0 || yAxisTilt != 0)) {
                    System.out.println("You crashed! Game over.");
                }
            }

            if (xAxisTilt == 0 && yAxisTilt == 0) {
                System.out.println("Congratulations! You've successfully landed the lunar lander!");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("yes");
        } while (playAgain);

        System.out.println("Thanks for playing Lunar Lander Simulator!");
        System.out.println("Please return soon for future travels!");
    }
}