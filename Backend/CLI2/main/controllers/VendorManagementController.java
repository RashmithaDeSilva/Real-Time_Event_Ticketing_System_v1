package main.controllers;

import main.dao.VendorDAO;
import main.dao.impl.VendorDAOImpl;
import main.models.Vendor;
import main.util.UserInputGetCollection;
import main.util.validation.VendorValidation;

import java.util.List;


public class VendorManagementController {
    // To get inputs
    private final UserInputGetCollection uic = new UserInputGetCollection();

    // System config data access object
    private final static VendorDAO configDAO = new VendorDAOImpl();

    // Vendor validation
    private final static VendorValidation validation = new VendorValidation();

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
        System.out.println("\n===== Add Vendor =====\n");
        Vendor vendor = new Vendor();

        while (true) {
            String vendorName = uic.getUserInputString("Please enter a vendor name:> ");
            if (validation.validateVendorName(vendorName)) {
                vendor.setVendorName(vendorName);
                break;
            }
        }

        while (true) {
            int ticketsPerRelease = uic.getUserInputInt("Please enter a tickets per release:> ");
            if (validation.validateVendorTicketsPerRelease(ticketsPerRelease)) {
                vendor.setTicketsPerRelease(ticketsPerRelease);
                break;
            }
        }

        while (true) {
            int releaseRateSec = uic.getUserInputInt("Please enter a release rate:> ");
            if (validation.validateVendorReleaseRateSec(releaseRateSec)) {
                vendor.setReleaseRateSec(releaseRateSec);
                break;
            }
        }

        configDAO.addVendor(vendor);
        System.out.printf("Successfully added vendor: %s\n", vendor.getVendorName());
    }

    // 2. Remove Vendor
    private void removeVendor() {
        System.out.println("\n===== Remove Vendor =====\n");
        while (true) {
            int vendorId = uic.getUserInputInt("Please enter a vendor id:> ");
            if (validation.existsVendor(vendorId)) {
                configDAO.deleteVendor(vendorId);
                System.out.printf("Successfully deleted vendor with ID %d\n", vendorId);
                break;
            }
        }
    }

    // 3. Show All Vendors
    private void showAllVendors() {
        System.out.println("\n===== Show All Vendors =====\n");
        List<Vendor> vendors = configDAO.getAllVendors();
        System.out.println("+-----+-------------------------+");
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
