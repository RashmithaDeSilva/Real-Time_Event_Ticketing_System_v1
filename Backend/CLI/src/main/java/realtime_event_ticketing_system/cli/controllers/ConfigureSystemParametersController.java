package realtime_event_ticketing_system.cli.controllers;

import realtime_event_ticketing_system.cli.dao.SystemConfigDAO;
import realtime_event_ticketing_system.cli.dao.impl.SystemConfigDAOImpl;
import realtime_event_ticketing_system.cli.util.UserInputGetCollection;

import java.sql.SQLException;


public class ConfigureSystemParametersController {
    // To get inputs
    private final UserInputGetCollection uic = new UserInputGetCollection();

    // System config data access object
    private final static SystemConfigDAO configDAO = new SystemConfigDAOImpl();

    // Configure system parameters menu
    private int configureSystemParametersMenu() {
        System.out.println("""
        ===== Configure System Parameters =====
        1. Total Ticket Update
        2. Tickets Released Update
        3. Back to Main Menu
        ============================================
        """);
        return uic.getUserInputInt("Please select an option (0-3):> ");
    }

    // 1. Total Ticket Update
    private void totalTicketUpdate() {
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

    // 2. Tickets Released Update
    private void ticketsReleasedUpdate() {
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

    // System Configuration
    public void configureSystemParameters() {
        boolean exit = true;

        while (exit) {
            System.out.println();
            switch (configureSystemParametersMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 1:     // 1. Total Ticket Update
                    totalTicketUpdate();
                    break;

                case 2:     // 2. Tickets Released Update
                    ticketsReleasedUpdate();
                    break;

                case 3:     // 3. Back to Main Menu
                    exit = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

}
