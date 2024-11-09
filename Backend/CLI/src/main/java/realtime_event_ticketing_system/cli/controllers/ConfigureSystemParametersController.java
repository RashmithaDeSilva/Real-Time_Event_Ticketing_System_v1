package realtime_event_ticketing_system.cli.controllers;

import realtime_event_ticketing_system.cli.dao.SystemConfigDAO;
import realtime_event_ticketing_system.cli.dao.impl.SystemConfigDAOImpl;
import realtime_event_ticketing_system.cli.util.UserInputGetCollection;

import java.sql.SQLException;


public class ConfigureSystemParametersController {
    // To get inputs
    private final UserInputGetCollection uic = new UserInputGetCollection();

    // System config data access object
    private final static SystemConfigDAO configDAO = SystemConfigDAOImpl.getInstance();

    // Configure system parameters menu
    private int configureSystemParametersMenu() {
        System.out.println("""
        ===== Configure System Parameters =====
        1. Show Status
        2. Set Total Tickets
        3. Set Ticket Release Rate
        4. Set Customer Retrieval Rate
        5. Set Max Ticket Capacity
        6. Back to Main Menu
        ============================================
        """);
        return uic.getUserInputInt("Please select an option (1-3):> ");
    }

    // 1. Show Status
    private void showStatus() {
        try {
            System.out.println(
                    "\n===== Show Status =====\n" +
                            "Tickets Total Right Now: " +
                            configDAO.findConfigValue("max_ticket_capacity") + "\n" +
                            "Tickets Released Right Now (per seconds): " +
                            configDAO.findConfigValue("ticket_release_rate") + "\n" +
                            "============================================"
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 2. Set Total Tickets
    private void setTotalTickets() {
        try {
            System.out.println(
            "\n===== Total Ticket Update =====\n" +
            "Tickets Total Right Now: " + configDAO.findConfigValue("max_ticket_capacity")
            );

            int newTickets = uic.getUserInputInt("New total tickets max capacity:> ");

            if (newTickets >= 0) {
                configDAO.updateConfigValue("max_ticket_capacity", newTickets);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3. Set Ticket Release Rate
    private void setTicketReleaseRate() {
        try {
            System.out.println(
                    "\n===== Tickets Released Update =====\n" +
                    "Tickets Released Right Now (per seconds): " +
                    configDAO.findConfigValue("ticket_release_rate")
            );

            int newTickets = uic.getUserInputInt("New tickets released (per seconds):> ");

            if (newTickets >= 0) {
                configDAO.updateConfigValue("ticket_release_rate", newTickets);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 4. Set Customer Retrieval Rate
    private void setCustomerRetrievalRate() {}

    // 5. Set Max Ticket Capacity
    private void setMaxTicketCapacity() {}

    // System Configuration
    public void configureSystemParameters() {
        boolean exit = true;

        while (exit) {
            System.out.println();
            switch (configureSystemParametersMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 1:     // 1. Show Status
                    showStatus();
                    break;

                case 2:     // 2. Set Total Tickets
                    setTotalTickets();
                    break;

                case 3:     // 3. Set Ticket Release Rate
                    setTicketReleaseRate();
                    break;

                case 4:     // 4. Set Customer Retrieval Rate
                    setCustomerRetrievalRate();
                    break;

                case 5:     // 5. Set Max Ticket Capacity
                    setMaxTicketCapacity();
                    break;

                case 6:     // 6. Back to Main Menu
                    exit = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

}
