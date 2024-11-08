package realtime_event_ticketing_system.cli.dao;

import java.sql.SQLException;


public interface SystemConfigDAO {
    int findConfigValue(String configKey) throws SQLException;
    void updateConfigValue(String configKey, int configValue) throws SQLException;
}
