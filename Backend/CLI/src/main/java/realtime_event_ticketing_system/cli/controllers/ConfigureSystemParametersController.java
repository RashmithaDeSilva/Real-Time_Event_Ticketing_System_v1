package realtime_event_ticketing_system.cli.controllers;

import realtime_event_ticketing_system.cli.util.UserInputGetCollection;


public class ConfigureSystemParametersController {
    // To get inputs
    private final UserInputGetCollection uic = new UserInputGetCollection();

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
        return uic.getUserInputInt("Please select an option (0-3):> ");
    }

    // 1. System Configuration
    public void configureSystemParameters() {
        boolean exit = true;

        while (exit) {
            System.out.println();
            switch (configureSystemParametersMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 0:     // 0. Exit
                    exit = false;
                    break;

                case 1:     // 1. Total Ticket Update

                    break;

                case 2:     // 2. Tickets Released Update

                    break;

                case 3:     // 3. Back to Main Menu

                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

}
