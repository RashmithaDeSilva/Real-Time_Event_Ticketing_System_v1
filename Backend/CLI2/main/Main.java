package main;

import main.controllers.ConfigureSystemParametersController;
import main.controllers.VendorManagementController;
import main.db.SQLiteConnection;
import main.util.UserInputGetCollection;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {

    // To get inputs
    private final UserInputGetCollection userInputGetCollection = new UserInputGetCollection();

    // Database connection
    private final Connection connection = SQLiteConnection.getInstance().getConnection();

    // 1. Configure System Parameters
    private final ConfigureSystemParametersController configureSystemParametersController = new ConfigureSystemParametersController();

    // 2. Manage Vendors
    private final VendorManagementController vendorManagementController = new VendorManagementController();

    // main.Main menu
    private int mainMenu() {
        System.out.println("""
        ===== Real-Time Event Ticketing System =====
        1. Configure System Parameters
        2. Vendors Management
        3. Ticket Management
        4. View Sales Log
        0. Exit
        ============================================
        """);
        return userInputGetCollection.getUserInputInt("Please select an option (0-5):> ");
    }

    public static void main(String[] args) {
        Main main = new Main();
        boolean exit = false;
        try {
            System.out.println(main.connection != null && !main.connection.isClosed()
                    ? "Database connected successfully." : "Database connection failed.");
            exit = main.connection != null && !main.connection.isClosed();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (exit) {
            System.out.println();
            switch (main.mainMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 0:     // 0. Exit
//                    exit = !main.userInputGetCollection.getUserInputString("Are you sure you want to exit? (y/n):> ")
//                            .equalsIgnoreCase("y");
//                    if (!exit) main.ticketManagementController.stopSystem();
                    try {
                        main.connection.close();

                    } catch (SQLException e) {
                        System.out.println("DB connection failed.\n" + e.getMessage());
                    }
                    exit = false;
                    break;

                case 1:     // 1. Configure System Parameters
                    main.configureSystemParametersController.configureSystemParameters();
                    break;

                case 2:     // 2. Manage Vendors
                    main.vendorManagementController.vendorsManagement();
                    break;

                case 3:     // 3. Manage Tickets
//                    main.ticketManagementController.ticketManagement();
                    break;

                case 4:     // 4. Sales Log
//                    main.salesLogController.salesLog();
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}
