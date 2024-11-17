package realtime_event_ticketing_system.cli;

import java.sql.Connection;

import realtime_event_ticketing_system.cli.controllers.*;
import realtime_event_ticketing_system.cli.db.SQLiteConnection;
import realtime_event_ticketing_system.cli.util.UserInputGetCollection;


public class Main {

    // To get inputs
    private final UserInputGetCollection userInputGetCollection = new UserInputGetCollection();

    // 1. Configure System Parameters
    private final ConfigureSystemParametersController configureSystemParametersController = new ConfigureSystemParametersController();

    // 2. Manage Vendors
    private final VendorManagementController vendorManagementController = new VendorManagementController();

    // 3. Manage Tickets
    private final TicketManagementController ticketManagementController = new TicketManagementController();

    // 4. Sales Log
    private final SalesLogController salesLogController = new SalesLogController();

    // Database connection
    private final Connection connection = SQLiteConnection.getInstance().getConnection();

    // Main menu
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
        System.out.println(main.connection != null ? "Database connected successfully." : "");
        boolean exit = main.connection != null;

        while (exit) {
            System.out.println();
            switch (main.mainMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 0:     // 0. Exit
                    exit = !main.userInputGetCollection.getUserInputString("Are you sure you want to exit? (y/n):> ")
                            .equalsIgnoreCase("y");
                    if (!exit) main.ticketManagementController.stopSystem();
                    break;

                case 1:     // 1. Configure System Parameters
                    main.configureSystemParametersController.configureSystemParameters();
                    break;

                case 2:     // 2. Manage Vendors
                    main.vendorManagementController.vendorsManagement();
                    break;

                case 3:     // 3. Manage Tickets
                    main.ticketManagementController.ticketManagement();
                    break;

                case 4:     // 4. Sales Log
                    main.salesLogController.salesLog();
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}