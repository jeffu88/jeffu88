package Chapter2;

import java.awt.image.BandCombineOp;
import java.util.Scanner;

public class Chapter2 {


    public static void main(String[] args) {

        // declaring a variable
        int yourNumber;

        // assign the value 10 to the variable yourNumber
        yourNumber = 10;

        System.out.println("Your number is: " + yourNumber);

        // declare and assign - like this way
        int myFavoriteNumber = 42;

        System.out.println("My favorite number is: " + myFavoriteNumber);

        double numberWithADecimal = 7.7;

        yourNumber = 12;

        // a double can hold an int
        numberWithADecimal = 12;


        // java doesn't care about your variable names
        // people care your variable names
        int a = 10;
        int b = 20;
        int c = 30;

        // but an int can't hold a double
        // yourNumber = 7.7;

        numberWithADecimal = yourNumber;

        System.out.println("Number with a decimal's value is: " + numberWithADecimal);

        System.out.println("Your number is: " + yourNumber);

        // Please Excuse My Dear Aunt Sally
        // Parenthesis Exponents (Multiplication Division) (Additional Subtraction)

        System.out.println("What is 5 - 2 + 3");
        System.out.println(5 - 2 + 3);
        System.out.println("95% of people can't figure this one out: 9 / 3 * 3 +2");
        System.out.println(9 / 3 * 3 + 2);

        myFavoriteNumber = 10 + 2;
        myFavoriteNumber = myFavoriteNumber + 10;
        // combined assignment operator
        myFavoriteNumber += 10;

        // not a combined assignment operator - this will set the value to Positive 10
        myFavoriteNumber =+ 10;

        myFavoriteNumber = yourNumber * 3 + 6;

        System.out.println("My favorite number is now " + myFavoriteNumber);

        // integer division gives Integer results - % is modulus operator,give the remain
        System.out.println("5 / 2 == " + 5 / 2 + "with reminder if " + 5 % 2);

        // to get a double result from division, one or both need to be a double
        double answer = 5.0 / 2;

        System.out.println("5 / 2 with a decimal result is: " + answer);

        // to treat a value as a double, you can cast 'cast' it as double with (double)
        double myFavoriteNumberDividedByYourFavoriteNumber = (double)myFavoriteNumber / yourNumber;

        System.out.println(myFavoriteNumberDividedByYourFavoriteNumber);


        String firstName = "Jeff";
        String lastName = "Underwood";

        String fullName = firstName + " " + lastName;

        // we're not "adding" we're concatenating
        System.out.println(fullName);

        String jeffsNameAndFavoriteNumber = fullName + " " + myFavoriteNumber;

        System.out.println(jeffsNameAndFavoriteNumber);

        // the backslash is the 'escape' character - means the next letter is special
        System.out.println("My friend Steph said \"Hello Jeff\"");

        // \new line - goes down a line
        System.out.println("New Line = = \n \\n");

        // \t is a tab
        System.out.println("My Coffee Menu");
        System.out.println("$2\t\tCoffee");
        System.out.println("$3.5\tEspresso");
        System.out.println("$4\t\tLatte");

        System.out.println("My Coffee Menu");
        System.out.println("$2   Coffee");
        System.out.println("$3.5  Espresso");
        System.out.println("$4   Latte");

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter your name");
        String name = keyboard.nextLine();

        System.out.println("Hi there " + name);

        System.out.println("How many coffees do you want to buy today?");
        // don't use nextInt() - it causes problem later
        //int coffeesForToday = keyboard.nextInt();

        // I recommend Integer.parseInt
        int coffeesForToday = Integer.parseInt(keyboard.nextLine());

        System.out.println("How much does a coffee cost?");
        double costPerCoffee = Double.parseDouble(keyboard.nextLine());

        double totalCostForCoffee = coffeesForToday * costPerCoffee;

        System.out.println("How much money do you have");
        double moneyInPocket = Double.parseDouble(keyboard.nextLine());

        // if the expression inside the () is true, the if block runs from { to }
        if ( moneyInPocket >= totalCostForCoffee ) {
            System.out.println("That will cost you $" +totalCostForCoffee);
        } else { // if the if expression False or not True
            System.out.println("You don't have enough monet for that");

        }

        System.out.println("My Coffee Menu");
        System.out.println("$2   Coffee");
        System.out.println("$3.5  Espresso");
        System.out.println("$4   Latte");

        String order = keyboard.nextLine();

        // can't use == with strings
        //if ( order.equalsIgnoreCase(anotherString: "coffee")){
          // System.out.println("That's $1 for coffee");
    //    } //else if ( order.equalsIgnoreCase(anotherString: "espresso")){
           // System.out.println("That's $1.5 for espresso");
    //    } //else if ( order.equalsIgnoreCase(anotherString: "latte")){
            //System.out.println("That's $1.5 for latte");
    //    } //else{
            //System.out.println("I don't sell that");
    //    }

        double total = 0;

        String moreEntries = "y";

        // will check if it is true, and run if it is
       // while ( moreEntries.equalsIgnoreCase(anotherString: "y")){
            System.out.println("Enter a receipt");
            double value = Double.parseDouble(keyboard.nextLine());
        total += value;

        System.out.println("Do you have more receipts? (y/n)");
        moreEntries = keyboard.nextLine();




       System.out.println("That will cost you $" + coffeesForToday * costPerCoffee);

       System.out.println("Total receipts $" + total);


    }
}
