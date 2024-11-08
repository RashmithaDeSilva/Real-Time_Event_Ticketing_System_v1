package src;

import src.db.MySQLConnection;
import java.sql.Connection;
import java.util.Scanner;


public class main {
    // To get inputs
    private final Scanner scanner = new Scanner(System.in);

    // Database connection
    private final Connection connection = MySQLConnection.getInstance().getConnection();


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
        2. Manage Vendors
        3. Manage Tickets
        4. Monitor Real-Time Status
        5. View Sales Log
        0. Exit
        ============================================
        """);
        return getUserInputInt("Please select an option (1-6):> ");
    }

    // Configure system parameters menu
    private int configureSystemParametersMenu() {
        System.out.println("""
        ===== Real-Time Event Ticketing System =====
        1. Total Ticket Update
        2. Tickets Released Update
        3. Back to Main Menu
        0. Exit
        ============================================
        """);
        return getUserInputInt("Please select an option (1-6):> ");
    }

    // 1. System Configuration
    private void configureSystemParameters() {

    }

    // 2. Manage Vendors
    private void manageVendors() {

    }

    // 3. Manage Tickets
    private void manageTickets() {

    }

    // 4. Monitor Real-Time Status
    private void monitorRealTimeStatus() {

    }

    // 5. Sales Log
    private void salesLog() {

    }


    // Main method
    public static void main(String[] args) {
        main main = new main();
        boolean exit = true;

        while (exit) {
            switch (main.mainMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 0:     // 0. Exit
                    exit = false;
                    break;

                case 1:     // 1. Configure System Parameters
                    main.configureSystemParameters();
                    break;

                case 2:     // 2. Manage Vendors
                    main.manageVendors();
                    break;

                case 3:     // 3. Manage Tickets
                    main.manageTickets();
                    break;

                case 4:     // 4. Monitor Real-Time Status
                    main.monitorRealTimeStatus();
                    break;

                case 5:     // 5. Sales Log
                    main.salesLog();
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }

            System.out.println();
        }
    }
}
