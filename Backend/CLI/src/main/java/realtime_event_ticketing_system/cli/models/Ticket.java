package realtime_event_ticketing_system.cli.models;

import realtime_event_ticketing_system.cli.enums.TicketTypes;


public class Ticket {
    private int id;
    private int vendorId;
    private String ticketName;
    private TicketTypes ticketType;
    private int availability;
    private double price;
    private String description;

    // Private nun argument constructor
    private Ticket() {
    }

    // Full argument constructor
    public Ticket(int id, int vendorId, String ticketName, TicketTypes ticketType,
                  int availability, double price, String description) {
        this.id = id;
        this.vendorId = vendorId;
        this.ticketName = ticketName;
        this.ticketType = ticketType;
        this.availability = availability;
        this.price = price;
        this.description = description;
    }

    // Get ticket id
    public int getId() {
        return id;
    }

    // Get vendor id
    public int getVendorId() {
        return vendorId;
    }

    // Get ticket name
    public String getTicketName() {
        return ticketName;
    }

    // Set ticket name
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    // Get ticket type
    public TicketTypes getTicketType() {
        return ticketType;
    }

    // Set ticket type
    public void setTicketType(TicketTypes ticketType) {
        this.ticketType = ticketType;
    }

    // Get availability
    public int getAvailability() {
        return availability;
    }

    // Set availability
    public void setAvailability(int availability) {
        this.availability = availability;
    }

    // Get price
    public double getPrice() {
        return price;
    }

    // Set price
    public void setPrice(double price) {
        this.price = price;
    }

    // Get description
    public String getDescription() {
        return description;
    }

    // Set description
    public void setDescription(String description) {
        this.description = description;
    }
}
