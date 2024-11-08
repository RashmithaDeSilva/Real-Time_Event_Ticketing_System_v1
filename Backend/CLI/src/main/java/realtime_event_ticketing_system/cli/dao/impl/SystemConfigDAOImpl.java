package realtime_event_ticketing_system.cli.dao.impl;

import realtime_event_ticketing_system.cli.dao.SystemConfigDAO;
import realtime_event_ticketing_system.cli.db.SQLiteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SystemConfigDAOImpl implements SystemConfigDAO {

    @Override
    public int findConfigValue(String configKey) throws SQLException {
        String query = "SELECT config_value FROM system_config WHERE config_key = ?";

        try (Connection connection = SQLiteConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, configKey);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("config_value");

            } else {
                System.out.println("No matching config key found.");
                return -1;
            }
        }
    }

    @Override
    public void updateConfigValue(String configKey, int configValue) throws SQLException {
        String query = "UPDATE system_config SET config_value = ? WHERE config_key = ?";

        try (Connection connection = SQLiteConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, configValue);
            preparedStatement.setString(2, configKey);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Config value updated successfully.");

            } else {
                System.out.println("No matching config key found.");
            }
        }
    }
}