package Project0;

import java.util.Scanner;

public class Project0 {
    public static void main(String[] args) {

        System.out.println("Hello And Welcome To The Online Yard Store!");


        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter your name");
        String name = keyboard.nextLine();

        System.out.println("Hello Valued Customer And Welcome Back " + name);

        System.out.println("What Is The Length And The Width?");
        keyboard = new Scanner(System.in);
        double length = Double.parseDouble(keyboard.nextLine());
        double width = Double.parseDouble(keyboard.nextLine());

        System.out.println("How Far Apart Are The Fence Posts?");
        double postDistance = Double.parseDouble(keyboard.nextLine());

        System.out.println("How Many Board Posts Are Required?");
        double boardsAcrossPost = Double.parseDouble(keyboard.nextLine());

        System.out.println("Enter the cost of each post:");
        double postCost = Double.parseDouble(keyboard.nextLine());

        System.out.println("Enter the cost of each board:");
        double boardCost = Double.parseDouble(keyboard.nextLine());

        // Calculate the fence details using the JavaScript code
        calculateFence(length, width, postDistance, boardsAcrossPost, postCost, boardCost);

        // Existing code...
    }

    // Function to calculate the fence details
    static void calculateFence(double length, double width, double postDistance, double boardsAcrossPost, double postCost, double boardCost) {
        if (postDistance < length) {
            System.out.println("Error: The post distance is not evenly divisible by the length. Please try again.");
            return;
        }

        if (length > postDistance) {
            System.out.println("Error: The combined board length across posts is less than the length. Please try again.");
            return;
        }

        int totalPosts = (int) (2 * (length + width) / postDistance);
        int totalBoards = (int) Math.ceil(2 * (length + width) / (boardsAcrossPost * length));
        double totalPostCost = totalPosts * postCost;
        double totalBoardCost = totalBoards * boardCost;
        double projectCost = totalPostCost + totalBoardCost;

        System.out.println("Total Number of Posts Required: " + totalPosts);
        System.out.println("Total Number of Boards Required: " + totalBoards);
        System.out.println("Total Cost of Posts: $" + totalPostCost);
        System.out.println("Total Cost of Boards: $" + totalBoardCost);
        System.out.println("Grand Total for the Project: $" + projectCost);
    }
}