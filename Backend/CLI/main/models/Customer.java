package main.models;

import main.controllers.TicketManagementController;
import main.dao.impl.SalesLogDAOImpl;
import main.dao.impl.SystemConfigDAOImpl;

import java.sql.SQLException;
import java.util.Random;


public class Customer implements Runnable {
    private final int id;
    private final boolean vip;

    // Constructor
    public Customer() throws SQLException {
        Random random = new Random();
        this.id = random.nextInt(100) + 1;
        this.vip = random.nextInt(2) + 1 == 1;
    }

    @Override
    public void run() {
        try {
            if (new SystemConfigDAOImpl().findConfigValue("system_status") == 1 ) {
                TicketPool.getInstance().reloadSetMaxCapacity();
                TicketPool.getInstance().reloadSetTotalTickets();

                if (TicketPool.getInstance().removeTicket()) {
                    new SalesLogDAOImpl().addLog(vip
                            ? "Buy 1 ticket from ticket pool [ID - " + id + "] VIP Customer"
                            : "Buy 1 ticket from ticket pool [ID - " + id + "] Customer");
                }

            } else {
                new TicketManagementController().stopSystem();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isVip() {
        return vip;
    }
}
