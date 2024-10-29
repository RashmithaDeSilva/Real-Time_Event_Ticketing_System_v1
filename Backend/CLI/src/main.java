package src;


import src.db.MySQLConnection;
import java.sql.Connection;
import java.util.Scanner;


public class main {
    // To get inputs
    Scanner scanner = new Scanner(System.in);

    // Get integer user inputs
    private int getUserInputInt(String prompt) {
        try {
            System.out.print(prompt);
            return scanner.nextInt();

        } catch (Exception e) {
            System.out.println("Invalid input type input expect Integer");
            return -1;
        }
    }

    // Get float user inputs
    private double getUserInputDouble(String prompt) {
        try {
            System.out.print(prompt);
            return scanner.nextDouble();

        } catch (Exception e) {
            System.out.println("Invalid input type input expect Float");
            return -1;
        }
    }

    // Get string user inputs
    private String getUserInputString(String prompt) {
        try {
            System.out.print(prompt);
            return scanner.nextLine();

        } catch (Exception e) {
            System.out.println("Invalid input type input expect String");
            return null;
        }
    }

    // Main menu
    private int mainMenu() {
        System.out.println("""
        ===== Real-Time Event Ticketing System =====
        1. Configure System Parameters
        2. Add/Remove Vendors
        3. Set Ticket Types and Prices
        4. Start Ticket Handling
        5. Stop Ticket Handling
        6. View Real-Time Ticket Status by Vendor and Type
        7. View Sales Log
        8. Exit
        ============================================
        """);
        return getUserInputInt("Please select an option (1-6):> ");
    }

    private void configureSystemParameters() {

    }

    // Main method
    public static void main(String[] args) {
        Connection connection = MySQLConnection.getInstance().getConnection();
        main main = new main();
        boolean exit = true;

        while (exit) {
            switch (main.mainMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 1:     // 1. Configure System Parameters
                    main.configureSystemParameters();
                    break;

                case 2:     // 2. Start Ticket Handling
                    break;

                case 3:     // 3. Stop Ticket Handling
                    break;

                case 4:     // 4. View Real-Time Ticket Status
                    break;

                case 5:     // Inject language or reference
                    break;

                case 6:     // 6. Exit
                    exit = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }

            System.out.println();
        }
    }
}
