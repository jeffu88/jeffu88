package Lab3;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your age: ");
        String[] choices = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};
        String playAgain;

        do {
            // User's choice
            System.out.println("Choose your move: (1) Rock, (2) Paper, (3) Scissors, (4) Lizard, (5) Spock");
            int playerChoice = 0;

            while (playerChoice < 1 || playerChoice > 5) {
                System.out.print("Enter your choice (1-5): ");
                playerChoice = keyboard.nextInt();
            }

            // Generate a random choice for the computer
            int computerChoice = (int) (Math.random() * 5) + 1;

            // Display choices
            System.out.println("You chose: " + choices[playerChoice - 1]);
            System.out.println("Computer chose: " + choices[computerChoice - 1]);

            // Determine the result
            int result = (playerChoice - computerChoice + 5) % 5;

            if (result == 0) {
                System.out.println("It's a draw!");
            } else if (result == 1 || result == 3) {
                System.out.println("You win!");
            } else {
                System.out.println("Computer wins!");
            }

            // Ask if the player wants to play again
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = keyboard.next().toLowerCase();
        } while (playAgain.equals("yes"));

        System.out.println("Thanks for playing!");
    }
}