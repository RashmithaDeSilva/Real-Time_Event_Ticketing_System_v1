package realtime_event_ticketing_system.cli.models;

import java.util.ArrayList;


public class Vendor {
    private String vendorName;
    private String password;
    private ArrayList<Ticket> tickets;


    // Nun argument constructor
    public Vendor() {
    }

    // Full argument constructor
    public Vendor(String vendorName, String password, ArrayList<Ticket> tickets) {
        this.vendorName = vendorName;
        this.password = password;
        this.tickets = tickets;
    }

    // Get vendor name
    public String getVendorName() {
        return vendorName;
    }

    // Set vendor name
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    // Get tickets
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    // Set tickets
    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    // Add ticket
    public void addTicket(Ticket ticket) {
        if (tickets != null) {
            tickets.add(ticket);

        } else {
            tickets = new ArrayList<>();
            tickets.add(ticket);
        }
    }

    // Remove ticket
    public void removeTicket(Ticket ticket) {
        if (tickets != null) {
            tickets.remove(ticket);
        }
    }

    // Set password
    public void setPassword(String password) {
        this.password = password;
    }
}
