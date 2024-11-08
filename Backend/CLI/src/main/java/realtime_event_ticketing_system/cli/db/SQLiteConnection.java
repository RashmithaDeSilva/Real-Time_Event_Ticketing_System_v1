package realtime_event_ticketing_system.cli.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLiteConnection {
    private static SQLiteConnection instance;
    private Connection connection;

    // Private constructor for single architected
    private SQLiteConnection() {
        // Database credentials
        String url = "jdbc:sqlite:realtime_event_ticketing_system_db.db"; // DB URL

        try {
            // Connecting with database
            this.connection = DriverManager.getConnection(url);
            System.out.println("Database connected successfully.");

        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    // Get single instance
    public static SQLiteConnection getInstance() {
        if (instance == null) {
            instance = new SQLiteConnection();
        }
        return instance;
    }

    // Get connection
    public Connection getConnection() {
        return connection;
    }
}
