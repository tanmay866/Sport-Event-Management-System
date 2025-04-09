
import java.util.Scanner;
import javax.swing.*;

public class SportEventApplication {

    public static void main(String[] args) {
        System.out.println("==== Sport Event Management System ====");
        System.out.println("1. Run Console Application");
        System.out.println("2. Run GUI Application");
        System.out.print("Enter your choice (1-2): ");

        Scanner scanner = new Scanner(System.in);
        int choice = 1; // Default to console

        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException | java.util.NoSuchElementException e) {
            System.out.println("Invalid input. Defaulting to console application.");
        }

        switch (choice) {
            case 1 -> {
                System.out.println("Starting console application...");
                SportsEventManagement.main(args);
            }
            case 2 -> {
                System.out.println("Starting GUI application...");
                SwingUtilities.invokeLater(() -> new SportEventGUI());
            }
            default -> {
                System.out.println("Invalid choice. Defaulting to console application.");
                SportsEventManagement.main(args);
            }
        }
    }
}
