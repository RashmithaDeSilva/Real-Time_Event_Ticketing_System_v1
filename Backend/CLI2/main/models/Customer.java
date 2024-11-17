package realtime_event_ticketing_system.cli.models;

import realtime_event_ticketing_system.cli.dao.impl.SalesLogDAOImpl;
import java.sql.SQLException;
import java.util.Random;


public class Customer implements Runnable {
    private final int id;

    // Constructor
    public Customer() throws SQLException {
        Random random = new Random();
        this.id = random.nextInt(100) + 1;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        TicketPool.getInstance().removeTicket();
        SalesLogDAOImpl.getInstance().addLog("Buy 1 ticket from ticket pool [ID - " + id + "] Customer");
    }
}
