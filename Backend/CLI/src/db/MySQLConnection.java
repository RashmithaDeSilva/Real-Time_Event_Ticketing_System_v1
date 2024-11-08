package src.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLConnection {
    private static MySQLConnection instance;
    private Connection connection;

    // Private constructor for single architected
    private MySQLConnection() {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/event_ticketing_db"; // DB URL
        String user = "root"; // DB user name
        String password = "12345"; // DB password

        try {
            // Connecting with database
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully.");

        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    // Get single instance
    public static MySQLConnection getInstance() {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    // Get connection
    public Connection getConnection() {
        return connection;
    }
}
