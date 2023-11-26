package Project3;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static class Ticket {
        int ticketNumber;
        String purchaserName;
        int verificationCode;

        public Ticket(int ticketNumber) {
            this.ticketNumber = ticketNumber;
            this.purchaserName = "";
            this.verificationCode = 0;
        }
    }

    static class ShowTicketManager {
        private ArrayList<Ticket> tickets;

        public ShowTicketManager() {
            this.tickets = new ArrayList<>();
        }

        public void createShowFile(String showName, int numberOfTickets) {
            try (PrintWriter writer = new PrintWriter(showName + ".txt")) {
                for (int i = 1; i <= numberOfTickets; i++) {
                    writer.println(i + ", , 0");
                    tickets.add(new Ticket(i));
                }
                System.out.println("Show file created successfully.");
            } catch (FileNotFoundException e) {
                System.err.println("Error creating show file: " + e.getMessage());
            }
        }

        public void sellTickets(String showName) {
            try (Scanner scanner = new Scanner(new File(showName + ".txt"))) {
                while (scanner.hasNextLine()) {
                    String[] ticketInfo = scanner.nextLine().split(", ");
                    int ticketNumber = Integer.parseInt(ticketInfo[0]);
                    String purchaserName = ticketInfo[1];
                    int verificationCode = Integer.parseInt(ticketInfo[2]);

                    Ticket ticket = new Ticket(ticketNumber);
                    ticket.purchaserName = purchaserName;
                    ticket.verificationCode = verificationCode;

                    tickets.add(ticket);
                }

                System.out.println("Enter desired ticket number:");
                Scanner input = new Scanner(System.in);
                int desiredTicketNumber = input.nextInt();

                Ticket desiredTicket = tickets.get(desiredTicketNumber - 1);

                if (desiredTicket.purchaserName.equals("") || desiredTicket.verificationCode == 0) {
                    System.out.println("Enter your name:");
                    input.nextLine(); // Consume newline
                    String purchaserName = input.nextLine();
                    int randomCode = new Random().nextInt(900000) + 100000;

                    desiredTicket.purchaserName = purchaserName;
                    desiredTicket.verificationCode = randomCode;

                    System.out.println("Ticket sold successfully.");
                } else {
                    System.out.println("Sorry, the selected ticket is already sold. Choose a different seat.");
                }

            } catch (FileNotFoundException e) {
                System.err.println("Error reading show file: " + e.getMessage());
            }
        }

        public void printReport(String showName) {
            System.out.println("Ticket Report for " + showName + ":");
            for (Ticket ticket : tickets) {
                System.out.println("Ticket #: " + ticket.ticketNumber +
                        ", Purchaser Name: " + ticket.purchaserName +
                        ", Verification Code: " + ticket.verificationCode);
            }
        }
    }

    public static void main(String[] args) {
        ShowTicketManager ticketManager = new ShowTicketManager();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Create Show");
            System.out.println("2. Sell Tickets");
            System.out.println("3. Print Report");
            System.out.println("4. Exit");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter show name:");
                    input.nextLine(); // Consume newline
                    String showName = input.nextLine();
                    System.out.println("Enter number of tickets:");
                    int numberOfTickets = input.nextInt();
                    ticketManager.createShowFile(showName, numberOfTickets);
                    break;

                case 2:
                    System.out.println("Enter show name to sell tickets:");
                    input.nextLine(); // Consume newline
                    String sellShowName = input.nextLine();
                    ticketManager.sellTickets(sellShowName);
                    break;

                case 3:
                    System.out.println("Enter show name to print report:");
                    input.nextLine(); // Consume newline
                    String reportShowName = input.nextLine();
                    ticketManager.printReport(reportShowName);
                    break;

                case 4:
                    System.out.println("Exiting program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
