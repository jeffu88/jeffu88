package PP.Rectangular.PP;

import java.util.Scanner;

public class Project0 {
    public static void main(String[] args) {
        // \t is a tab
        System.out.println("Rectangular Fence Market");
        System.out.println("$25   7ft board");
        System.out.println("$75  15ft board");
        System.out.println("$9    3ft board");
        System.out.println("$19.99  board post");

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Customer Name");
        String name = keyboard.nextLine();

        System.out.println("Hi And Welcome To The Rectangular Fence Market " + name);

        System.out.println("What Is The Length And The Width?");
        String fenceL = keyboard.nextLine();

        System.out.println("How Far Apart Are The Fence Post?");
        String fenceP = keyboard.nextLine();

        System.out.println("How Many Board Post Are Required?");
        String fencePP = keyboard.nextLine();

        System.out.println("How many coffees do you want to buy today?");
        // don't use nextInt() - it causes problems later
        // int coffeesForToday = keyboard.nextInt();

        // I always recommend Integer.parseInt
        int coffeesForToday = Integer.parseInt(keyboard.nextLine());

        System.out.println("How much does a coffee cost?");
        double costPerCoffee = Double.parseDouble(keyboard.nextLine());

        double totalCostForCoffee =  coffeesForToday * costPerCoffee;

        System.out.println("How much money do you have?");
        double moneyInPocket = Double.parseDouble(keyboard.nextLine());

        // if the expression inside the ( ) is true, the if block runs from { to }
        if ( moneyInPocket >= totalCostForCoffee ) {
            System.out.println("That will cost you $" + totalCostForCoffee);
        } else { // if the if expression is False or not True
            System.out.println("You don't have enough money for that");
        }

        System.out.println("What did you want to order?");
        System.out.println("My Coffee Menu:");
        System.out.println("$1    Coffee");
        System.out.println("$1.5  Espresso");
        System.out.println("$2    Latte");

        String order = keyboard.nextLine();

        // can't use == with strings
        if ( order.equalsIgnoreCase("coffee")){
            System.out.println("That's $1 for coffee");
        } else if ( order.equalsIgnoreCase("espresso")){
            System.out.println("That's $1.5 for espresso");
        }else if ( order.equalsIgnoreCase("latte")){
            System.out.println("That's $2 for latte");
        } else{
            System.out.println("I don't sell that");
        }

        double total = 0;

        String moreEntries = "y";

        // will check if it's true, and run if it is
        while ( moreEntries.equalsIgnoreCase("y")){
            System.out.println("Enter a receipt");
            double value = Double.parseDouble(keyboard.nextLine());
            total += value;

            System.out.println("Do you have more receipts? (y/n)");
            moreEntries = keyboard.nextLine();


        } // after the loop gets to the end, it goes back to the while express, and if it's true, runs again

        System.out.println("Total receipts $" + total);



    }
}
