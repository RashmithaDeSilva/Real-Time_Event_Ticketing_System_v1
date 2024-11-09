package realtime_event_ticketing_system.cli.models;


public class Vendor {
    private int id;
    private String vendorName;
    private Ticket ticket;

    // Nun argument constructor
    public Vendor() {
    }

    // Full argument constructor
    public Vendor(int id, String vendorName, Ticket ticket) {
        this.id = id;
        this.vendorName = vendorName;
        this.ticket = ticket;
    }

    // Get vendor name
    public String getVendorName() {
        return vendorName;
    }

    // Set vendor name
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    // Get id
    public int getId() {
        return id;
    }

    // Set id
    public void setId(int id) {
        this.id = id;
    }

    // Get ticket
    public Ticket getTicket() {
        return ticket;
    }

    // Set ticket
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
