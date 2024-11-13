package realtime_event_ticketing_system.cli.models;

import realtime_event_ticketing_system.cli.dao.SystemConfigDAO;
import realtime_event_ticketing_system.cli.dao.impl.SystemConfigDAOImpl;

import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;


public class TicketPool {
    private static int totalTickets;
    private static int maxCapacity;
    private final ReentrantLock lock = new ReentrantLock();
    private static volatile TicketPool ticketPool;

    // System config data access object
    private static final SystemConfigDAO configDAO = SystemConfigDAOImpl.getInstance();

    // Private constructor for Singleton pattern
    private TicketPool() {}

    // Singleton instance with double-checked locking
    public static TicketPool getInstance() {
        if (ticketPool == null) {
            synchronized (TicketPool.class) {
                if (ticketPool == null) {
                    ticketPool = new TicketPool();
                    try {
                        ticketPool.setMaxCapacity(configDAO.findConfigValue("max_ticket_capacity"));
                        ticketPool.setTotalTickets(configDAO.findConfigValue("total_tickets"));

                    } catch (SQLException e) {
                        throw new RuntimeException("Failed to initialize ticket pool from database.", e);
                    }
                }
            }
        }
        return ticketPool;
    }

    // Synchronized method to add tickets
    public void addTickets(int count) {
        lock.lock();
        try {
            if (totalTickets + count <= maxCapacity) {
                configDAO.updateConfigValue("total_tickets", totalTickets + count);
                totalTickets += count;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating total tickets in database.", e);

        } finally {
            lock.unlock();
        }
    }

    // Synchronized method to remove a ticket
    public void removeTicket() {
        lock.lock();
        try {
            if (totalTickets > 0) {
                configDAO.updateConfigValue("total_tickets", totalTickets);
                totalTickets -= 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating ticket count in database.", e);

        } finally {
            lock.unlock();
        }
    }

    // Method to retrieve the current ticket count
    public int getTotalTickets() {
        lock.lock();
        try {
            return totalTickets;
        } finally {
            lock.unlock();
        }
    }

    // Getter and setter methods for capacity and total tickets
    public int getMaxCapacity() {
        lock.lock();
        try {
            return maxCapacity;
        } finally {
            lock.unlock();
        }
    }

    public void setMaxCapacity(int maxCapacity) {
        lock.lock();
        try {
            TicketPool.maxCapacity = maxCapacity;
        } finally {
            lock.unlock();
        }
    }

    public void setTotalTickets(int totalTickets) {
        lock.lock();
        try {
            TicketPool.totalTickets = totalTickets;
        } finally {
            lock.unlock();
        }
    }
}
