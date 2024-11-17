package main.models;

import main.dao.impl.SalesLogDAOImpl;
import main.dao.impl.SystemConfigDAOImpl;

import java.sql.SQLException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Vendor implements Runnable {
    private int id;
    private String vendorName;
    private int ticketsPerRelease;
    private int releaseRateSec;
    private final SystemConfigDAOImpl configDAO = new SystemConfigDAOImpl();


    @Override
    public void run() {
        try {
            checkVendorDetails();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TicketPool.getInstance().addTickets(ticketsPerRelease);
        new SalesLogDAOImpl().addLog("Add " + ticketsPerRelease +
                " tickets into ticket pool [ID - " + id + "] Vendor " + vendorName);
    }

    // Method to start vendor's periodic ticket release
    public void start(ScheduledExecutorService executorService) throws SQLException {
        checkVendorDetails();
        int globuleReleaseRateSec = configDAO.findConfigValue("ticket_release_rate");
        executorService.scheduleAtFixedRate(this, 0,
                Math.max(globuleReleaseRateSec, releaseRateSec), TimeUnit.SECONDS);
    }

    private void checkVendorDetails() throws SQLException {
        if (id == 0 || vendorName == null || ticketsPerRelease == 0 || releaseRateSec == 0) {
            throw new IllegalStateException("Before start or run vendor setup vendor details");
        }
        
        int globalTicketsPreRelease = configDAO.findConfigValue("ticket_release_rate");
        if (globalTicketsPreRelease > releaseRateSec) {
            setReleaseRateSec(globalTicketsPreRelease);
        }
    }

    // Nun argument constructor
    public Vendor() {
    }

    // Full argument constructor
    public Vendor(int id, String vendorName, int ticketsPerRelease, int releaseRateSec) {
        this.id = id;
        this.vendorName = vendorName;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseRateSec = releaseRateSec;
    }

    public Vendor(String vendorName, int ticketsPerRelease, int releaseRateSec) {
        this.vendorName = vendorName;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseRateSec = releaseRateSec;
    }

    // Get id
    public int getId() {
        return id;
    }

    // Set id
    public void setId(int id) {
        this.id = id;
    }

    // Get vendor name
    public String getVendorName() {
        return vendorName;
    }

    // Set vendor name
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    // Get tickets per release
    public int getTicketsPerRelease() {
        return ticketsPerRelease;
    }

    // Set tickets per release
    public void setTicketsPerRelease(int ticketsPerRelease) {
        this.ticketsPerRelease = ticketsPerRelease;
    }

    // Get release rate sec
    public int getReleaseRateSec() {
        return releaseRateSec;
    }

    // Set release rate sec
    public void setReleaseRateSec(int releaseRateSec) {
        this.releaseRateSec = releaseRateSec;
    }
}
