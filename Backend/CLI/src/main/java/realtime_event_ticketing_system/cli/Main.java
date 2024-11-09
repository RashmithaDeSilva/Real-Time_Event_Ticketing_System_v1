package realtime_event_ticketing_system.cli;

import java.sql.Connection;
import java.util.ArrayList;

import realtime_event_ticketing_system.cli.controllers.*;
import realtime_event_ticketing_system.cli.db.SQLiteConnection;
import realtime_event_ticketing_system.cli.util.TableFormatter;
import realtime_event_ticketing_system.cli.util.UserInputGetCollection;


public class Main {
    // Database connection
    private final Connection connection = SQLiteConnection.getInstance().getConnection();

    // To get inputs
    private final UserInputGetCollection uic = new UserInputGetCollection();

    // 1. Configure System Parameters
    private final ConfigureSystemParametersController csp = new ConfigureSystemParametersController();

    // 2. Manage Vendors
    private final VendorManagementController mvc = new VendorManagementController();

    // 3. Manage Tickets
    private final TicketManagementController mt = new TicketManagementController();

    // 4. Sales Log
    private final SalesLogController slc = new SalesLogController();


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
        return uic.getUserInputInt("Please select an option (0-5):> ");
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.connection != null ? "Database connected successfully." : "");
        boolean exit = true;

        // Correctly initializing arrays
//        String[] columns = new String[]{"id", "name", "description"};
//        int[] columnWidths = new int[]{3, 5, 15};
//        ArrayList<Object> dataset = new ArrayList<>();
//
//        // Creating an instance of TableFormatter with correct parameters
//        TableFormatter tableFormatter = new TableFormatter(columns, columnWidths, dataset);

        while (exit) {
            System.out.println();
            switch (main.mainMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 0:     // 0. Exit
                    exit = false;
                    break;

                case 1:     // 1. Configure System Parameters
                    main.csp.configureSystemParameters();
                    break;

                case 2:     // 2. Manage Vendors
                    main.mvc.vendorsManagement();
                    break;

                case 3:     // 3. Manage Tickets
                    main.mt.ticketManagement();
                    break;

                case 4:     // 4. Sales Log
                    main.slc.salesLog();
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}