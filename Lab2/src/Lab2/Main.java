package Lab2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Scanner keyboard = new Scanner(System.in);

        // The welcome message
        System.out.println("Welcome To The Time Keeping System");

        System.out.println("Please enter your name");
        String name = keyboard.nextLine();

        System.out.println("Welcome " + name);

        // Hourly wage and hours worked this week
        System.out.print("Enter your hourly wage: $");
        double hourlyWage = scanner.nextDouble();

        System.out.print("Enter hours worked this week: ");
        double hoursWorked = scanner.nextDouble();

        double grossPay;
        double overtimePay = 0.0;

        // Calculate the gross pay.
        if (hoursWorked <= 40) {
            grossPay = hourlyWage * hoursWorked;
        } else {
            // Regular pay for the first 40 hours.
            grossPay = hourlyWage * 40;
            // Overtime pay for hours worked over 40.
            overtimePay = (hoursWorked - 40) * (hourlyWage * 1.5);
            grossPay += overtimePay;
        }

        // Estimate taxes owed with 10%
        double taxesOwed = 0.10 * grossPay;

        // Net pay after deducting taxes
        double netPay = grossPay - taxesOwed;

        // Results for week
        System.out.printf("Gross Pay: $%.2f%n", grossPay);
        System.out.printf("Taxes Owed (10%%): $%.2f%n", taxesOwed);
        System.out.printf("Net Pay: $%.2f%n", netPay);


    }
}