package realtime_event_ticketing_system.cli.dao;

import realtime_event_ticketing_system.cli.models.Vendor;

import java.util.List;

public interface VendorDAO {
    void addVendor(Vendor vendor);
    void updateVendor(Vendor vendor);
    void deleteVendor(int vendorId);
    Vendor getVendor(int vendorId);
    List<Vendor> getAllVendors();
    List<Vendor> getAllVendorsWithTicket();
}
