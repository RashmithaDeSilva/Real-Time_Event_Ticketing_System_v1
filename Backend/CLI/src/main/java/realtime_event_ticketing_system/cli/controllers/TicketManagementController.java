package realtime_event_ticketing_system.cli.controllers;

import realtime_event_ticketing_system.cli.dao.SystemConfigDAO;
import realtime_event_ticketing_system.cli.dao.impl.SystemConfigDAOImpl;
import realtime_event_ticketing_system.cli.dao.impl.VendorDAOImpl;
import realtime_event_ticketing_system.cli.models.Customer;
import realtime_event_ticketing_system.cli.models.TicketPool;
import realtime_event_ticketing_system.cli.models.Vendor;
import realtime_event_ticketing_system.cli.util.UserInputGetCollection;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TicketManagementController {
    // To get inputs
    private final UserInputGetCollection uic = new UserInputGetCollection();

    // System config data access object
    private final SystemConfigDAO configDAO = SystemConfigDAOImpl.getInstance();

    // Ticket pool
    private final TicketPool ticketPool = TicketPool.getInstance();

    // Initialize ScheduledExecutorService
    private final ScheduledExecutorService executorServiceVendor = Executors.newScheduledThreadPool(4);

    // Initialize ScheduledExecutorService
    private final ScheduledExecutorService executorServiceCustomer = Executors.newScheduledThreadPool(1);

    public TicketManagementController() {
        try {
            if (configDAO.findConfigValue("system_status") == 1) {
                startSystem();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Start system
    public void startSystem() throws SQLException {
        startSystemForVendors();
        startSystemForCustomers();
    }

    // Start system for vendors
    public void startSystemForVendors() throws SQLException {
        List<Vendor> vendors = VendorDAOImpl.getInstance().getAllVendors();
        System.out.println(vendors);
        for (Vendor v : vendors) {
            System.out.println(v.toString());
//            v.start(executorServiceVendor);
        }
    }

    // Start system for customers
    public void startSystemForCustomers() throws SQLException {
        // Schedule a new customer task at a fixed rate (retrievalRateSec)
//        executorServiceCustomer.scheduleAtFixedRate(() -> {
//            try {
//                Customer customer = new Customer();
//                new Thread(customer).start(); // Run each customer in its own thread
//
//            } catch (SQLException e) {
//                System.err.println("Error creating a new customer: " + e.getMessage());
//            }
//        }, 0, SystemConfigDAOImpl.getInstance()
//                .findConfigValue("customer_retrieval_rate"), TimeUnit.SECONDS);

    }

    // Stop system
    public void stopSystem() {
        try {
            stopSystemForVendors();
            stopSystemForCustomers();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Stop system for vendors
    public void stopSystemForVendors() throws SQLException {
        // Add a shutdown hook to gracefully stop the executor service
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executorServiceCustomer.shutdown();
            try {
                if (!executorServiceCustomer.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorServiceCustomer.shutdownNow();
                }

            } catch (InterruptedException e) {
                executorServiceCustomer.shutdownNow();
            }
        }));
    }

    // Stop system for customers
    public void stopSystemForCustomers() throws SQLException {
        // Add a shutdown hook to gracefully stop the executor service
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executorServiceVendor.shutdown();
            try {
                if (!executorServiceVendor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorServiceVendor.shutdownNow();
                }

            } catch (InterruptedException e) {
                executorServiceVendor.shutdownNow();
            }
        }));
    }

    // Restart system
    public void restartSystem() throws SQLException {
        restartSystemForVendors();
        restartSystemForCustomers();
    }

    // Restart system for vendors
    public void restartSystemForVendors() throws SQLException {
        stopSystemForVendors();
        startSystemForVendors();
    }

    // Restart system for customers
    public void restartSystemForCustomers() throws SQLException {
        stopSystemForCustomers();
        startSystemForCustomers();
    }

    // Ticket parameters menu
    private int configureSystemParametersMenu() {
        System.out.println("""
        ===== Ticket Management =====
        1. Show Status
        2. Start System
        3. Stop System
        4. Restart System
        5. Back to Main Menu
        ============================================
        """);
        return uic.getUserInputInt("Please select an option (1-3):> ");
    }

    // 1. Show Status
    private void showStatus() {
        try {
            System.out.println(
                "\n===== Show Status =====\n" +
                (configDAO.findConfigValue("system_status") == 1
                ? "System is up and running"
                : "System is stop") +
                "============================================"
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 2. Start System
    private void setStartSystem() {
        try {
            configDAO.updateConfigValue("system_status", 1);
            startSystem();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3. Stop System
    private void setStopSystem() {
        try {
            configDAO.updateConfigValue("system_status", 0);
            stopSystem();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 4. Restart System
    private void setRestartSystem() {
        try {
            configDAO.updateConfigValue("system_status", 1);
            restartSystem();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Manage Tickets
    public void ticketManagement() {
        boolean exit = true;

        while (exit) {
            System.out.println();
            switch (configureSystemParametersMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 1:     // 1. Show Status
                    showStatus();
                    break;

                case 2:     // 2. Start System
                    setStartSystem();
                    break;

                case 3:     // 3. Stop System
                    setStopSystem();
                    break;

                case 4:     // 4. Restart System
                    setRestartSystem();
                    break;

                case 5:     // 5. Back to Main Menu
                    exit = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}
