package Week6;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        Chair jeffsChair = new Chair ();
        jeffsChair.setColor("blue");
        jeffsChair.setHasArms(true);
        jeffsChair.setHeightInCentimeters(30);
        jeffsChair.setMinimumHeightInCentimeters(20);
        jeffsChair.getMaximumHeightInCentimeters(40);
        System.out.println("Jeff's chair color is: " + jeffsChair.getColor());
        System.out.println("Jeff's chair has arms: " + jeffsChair.hasArms());
        System.out.println("Jeff's chair height in cm is: " + jeffsChair.getHeightInCentimeters());


        System.out.println("Hello and welcome!");
        System.out.println("Do You Want To Play A Game?!");

        Chair jaspersChair = new Chair();
        jaspersChair.setColor("skyblue");
        jaspersChair.setHasArms(false);
        jaspersChair.setHeightInCentimeters(30);

        System.out.println("Jasper's chair color is: " + jaspersChair.getColor());
        System.out.println("Jasper's chair has arms: " + jaspersChair.hasArms());
        System.out.println("Jasper's chair height in cm is: " + jaspersChair.getHeightInCentimeters());


    }
}