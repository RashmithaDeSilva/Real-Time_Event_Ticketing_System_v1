package main.dao;

import main.models.Vendor;
import java.util.List;


public interface VendorDAO {
    void addVendor(Vendor vendor);
    void updateVendor(Vendor vendor);
    void deleteVendor(int vendorId);
    Vendor getVendor(int vendorId);
    List<Vendor> getAllVendors();
    boolean existsVendor(int vendorId);
}
