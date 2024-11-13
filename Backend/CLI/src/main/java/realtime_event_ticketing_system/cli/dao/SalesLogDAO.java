package realtime_event_ticketing_system.cli.dao;

import realtime_event_ticketing_system.cli.models.SalesLog;
import java.util.List;


public interface SalesLogDAO {
    void addLog(String log);
    List<SalesLog> getAllLogs();
}
