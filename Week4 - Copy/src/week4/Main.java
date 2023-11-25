package week4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your age");
        int age = Integer.parseInt(keyboard.nextLine());

        System.out.println("Are you Male or Female (M/F): ");
        String gender = keyboard.nextLine();

        // and &&
        // true && true == true
        // true && true == false
        // false && true == false
        // false && false == false

        if ((age < 25) && gender.equalsIgnoreCase("n")){
            System.out.println("Your insurance rate is $250/month");
        }  else if ( age < 25) {
            System.out.println("Your insurance rate is $200/month");
        }  else {
            System.out.println("Your insurance rate is $150/month");
        }

        // || OR - shift backslash key above enter key - pipe
        // true || true == true
        // true || false == true
        // false || true == true
        // false || false == false

        System.out.println("Please enter your annual income:");
        int annualIncome = Integer.parseInt(keyboard.nextLine());

        System.out.println("Do you have any previous bankruptcies? (y/n)");
        String previousBankruptcies = keyboard.nextLine();

        if ( annualIncome > 1_000_000 ||
                ( annualIncome > 50_000 &&
                        previousBankruptcies.equalsIgnoreCase("n")) ){
            System.out.println("Here is your loan");
        }   else{
            System.out.println("No loan for you!");
        }

        // not !
        // !true == false
        // !false == true
        System.out.println("Do you want to play again? (y/n)");
        String playAgain = keyboard.nextLine();

        // while play again is NOT equal to N
        while ( !playAgain.equalsIgnoreCase("n")){
        System.out.println("Do you want to play again? (y/n)");
            playAgain = keyboard.nextLine();
        }
        System.out.println("Enter your favorite color");
        String favoriteColor = keyboard.nextLine().toLowerCase();

        if ( favoriteColor.equalsIgnoreCase("blue")) {
            System.out.println("That's my favorite too!");
        }   else if ( favoriteColor.equalsIgnoreCase("red")) {
            System.out.println("Red is my oldest son's favorite ");
        }   else if ( favoriteColor.equalsIgnoreCase("purple")) {
            System.out.println("This is my middle daughter's favorite color!");
        }   else if ( favoriteColor.equalsIgnoreCase("yellow")) {
            System.out.println("That is my oldest daughter's favorite color");
        }   else{
            System.out.println("I don't have a kid who likes that color");
        }
        switch ((favoriteColor)){
            case "blue":
                System.out.println("That's my favorite too!");
                break; // ends the switch
            case "red":
                System.out.println("Red is my oldest son's favorite ");
                break;
            case "purple":
                System.out.println("This is my middle daughter's favorite color!");
                break;
            case "yellow":
                System.out.println("That is my oldest daughter's favorite color");
                break;
            default:
                System.out.println("I don't have a kid who likes that color");

        }

        System.out.println("Enter your favorite number");
        int favoriteNumber = Integer.parseInt(keyboard.nextLine());

        if (favoriteNumber > 0 && favoriteNumber < 10) {
            System.out.println("You like single digits");
        } else if ( favoriteNumber >= 10 && favoriteNumber < 100 ) {
            System.out.println("You like two digit numbers");
        } else {
            System.out.println("You like big numbers");
        }

        switch (favoriteNumber){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                System.out.println("You like single digits");

        }

        int number = 0;

        // pre-test loop
        while ( number < 10){
            System.out.println(number);
            number++;
        }

        int anotherNumber = 0;
        do{
            System.out.println(anotherNumber);
            anotherNumber++;
        } while (anotherNumber < 10);

        //System.out.println("Do you want the blue pill or the red pull?");
        //String pillColor = keyboard.nextLine();

//        // infinite loop below  --ctrl / comments all out
//        int thirdNumber = 0;
//        while ( thirdNumber < 10);{
//            System.out.println(thirdNumber);
//            thirdNumber++;
//        }


        // validation loop - only ends when we have a valid input ^ about not needed
        String pillColor = "";

        while (!pillColor.equalsIgnoreCase("blue") &&
        !pillColor.equalsIgnoreCase("red")){
            System.out.println("Do you want the blue pill or the red  pill?");
            pillColor = keyboard.nextLine(); 
        }

        // declaration; test; update;
        for ( int value = 0; value < 10; value++ ){
            System.out.println(value);
        }

        System.out.println("Enter the width of a rectangle");
        int width = Integer.parseInt(keyboard.nextLine());

        System.out.println("Enter the height of a rectangle");
        int height = Integer.parseInt(keyboard.nextLine());

        for ( int currentRow = 0; currentRow < height; currentRow++ ){
            for ( int currentColumn = 0; currentColumn < width; currentColumn ++){
                // System.out.print doesn't add a newLine after printing
                System.out.print("*");
            }
            System.out.println();
            7
        }

    }
}
