package realtime_event_ticketing_system.cli.models;


import java.util.Random;

public class Customer implements Runnable {
    private final int customerId;

    // Constructor
    public Customer() {
        Random random = new Random();
        this.customerId = random.nextInt(100) + 1;
    }

    @Override
    public void run() {
        TicketPool.getInstance().removeTicket();
    }
}
