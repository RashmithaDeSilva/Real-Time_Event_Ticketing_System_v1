package realtime_event_ticketing_system.cli.models;

public class SystemConfig {
    private int maxTicketCapacity;

    public SystemConfig() {
    }

    public SystemConfig(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
}
