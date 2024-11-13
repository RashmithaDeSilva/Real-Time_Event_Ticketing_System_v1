package realtime_event_ticketing_system.cli.controllers;

import realtime_event_ticketing_system.cli.dao.VendorDAO;
import realtime_event_ticketing_system.cli.dao.impl.VendorDAOImpl;
import realtime_event_ticketing_system.cli.util.UserInputGetCollection;


public class VendorManagementController {
    // To get inputs
    private final UserInputGetCollection uic = new UserInputGetCollection();

    // System config data access object
    private final static VendorDAO configDAO = VendorDAOImpl.getInstance();

    // Manage vendors menu
    private int manageVendorsMenu() {
        System.out.println("""
        ===== Manage Vendors =====
        1. Show All Vendors
        2. Add Vendor
        3. Update Vendor
        4. Remove Vendor
        5. Back to Main Menu
        ============================================
        """);
        return uic.getUserInputInt("Please select an option (1-3):> ");
    }

    // 1. Add Vendor
    private void addVendor() {

    }

    // 2. Remove Vendor
    private void removeVendor() {

    }

    // 3. Show All Vendors
    private void showAllVendors() {

    }

    // 4. Update Vendor
    private void updateVendor() {

    }

    // Manage Vendors
    public void vendorsManagement() {
        boolean exit = true;

        while (exit) {
            System.out.println();
            switch (manageVendorsMenu()) {
                case -1:    // For invalid input skip
                    break;

                case 1:     // 1. Show All Vendors
                   showAllVendors();
                    break;

                case 2:     // 2. Add Vendor
                    addVendor();
                    break;

                case 3:     // 3. Update Vendor
                    updateVendor();
                    break;

                case 4:     // 4. Remove Vendor
                    removeVendor();
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
