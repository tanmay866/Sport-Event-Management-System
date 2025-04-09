// SportsEventManagement.java

import java.util.Scanner;

public class SportsEventManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner for user input

        // Creating events with gender constraints
        Event cricket = new Event("Cricket", "Male");
        Event badminton = new Event("Badminton", "Female");
        Event kabaddi = new Event("Kabaddi", "Both");
        Event cycling = new Event("Cycling", "Both");

        // Loop for adding participants
        while (true) {
            System.out.println("Enter participant name (or type 'exit' to finish):");
            String name = sc.nextLine();
            if (name.equalsIgnoreCase("exit")) // Exit condition
            {
                break;
            }

            System.out.println("Enter participant gender (Male/Female):");
            String gender = sc.nextLine(); // Read gender

            System.out.println("Enter participant score:");
            int score = Integer.parseInt(sc.nextLine()); // Read score

            // Prompt user to select an event
            System.out.println("Select An Event:");
            System.out.println("Enter 1 For Cricket");
            System.out.println("Enter 2 For Badminton");
            System.out.println("Enter 3 For Kabaddi");
            System.out.println("Enter 4 For Cycling");

            System.out.println("Enter Your Choice:");
            int eventNumber = Integer.parseInt(sc.nextLine()); // Read event choice as string and parse to int

            // Create a new participant
            Participant participant = new Participant(name, gender, score);
            // Add the participant to the selected event
            switch (eventNumber) {
                case 1 ->
                    cricket.addParticipant(participant);
                case 2 ->
                    badminton.addParticipant(participant);
                case 3 ->
                    kabaddi.addParticipant(participant);
                case 4 ->
                    cycling.addParticipant(participant);
                default ->
                    System.out.println("Invalid event number. Participant not added.");
            }
        }

        // Display details for each event using switch-case
        while (true) {
            System.out.println("Select an event to display details:");
            System.out.println("1. Cricket");
            System.out.println("2. Badminton");
            System.out.println("3. Kabaddi");
            System.out.println("4. Cycling");
            System.out.println("5. Exit");

            int choice = Integer.parseInt(sc.nextLine()); // Read user choice as string and parse to int
            switch (choice) {
                case 1 ->
                    cricket.displayEventDetails();
                case 2 ->
                    badminton.displayEventDetails();
                case 3 ->
                    kabaddi.displayEventDetails();
                case 4 ->
                    cycling.displayEventDetails();
                case 5 -> {
                    System.out.println("Exiting...");
                    sc.close(); // Properly close the scanner
                    return; // Exit the program
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
