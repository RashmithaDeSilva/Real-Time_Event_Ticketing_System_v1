package main.models;

import main.dao.impl.SalesLogDAOImpl;

import java.sql.SQLException;
import java.util.Random;


public class Customer implements Runnable {
    private final int id;

    // Constructor
    public Customer() throws SQLException {
        Random random = new Random();
        this.id = random.nextInt(100) + 1;
    }

    @Override
    public void run() {
        if (TicketPool.getInstance().removeTicket()) {
            new SalesLogDAOImpl().addLog("Buy 1 ticket from ticket pool [ID - " + id + "] Customer");
        }
    }
}
