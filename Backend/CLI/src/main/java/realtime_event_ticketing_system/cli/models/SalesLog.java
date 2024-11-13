package realtime_event_ticketing_system.cli.models;

public class SalesLog {
    private int id;
    private String timeAndDate;
    private String log;


    public SalesLog() {
    }

    public SalesLog(int id, String timeAndDate, String log) {
        this.id = id;
        this.timeAndDate = timeAndDate;
        this.log = log;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
