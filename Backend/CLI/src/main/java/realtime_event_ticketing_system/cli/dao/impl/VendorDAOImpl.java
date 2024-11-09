package realtime_event_ticketing_system.cli.dao.impl;

import realtime_event_ticketing_system.cli.dao.VendorDAO;
import realtime_event_ticketing_system.cli.db.SQLiteConnection;
import realtime_event_ticketing_system.cli.models.Ticket;
import realtime_event_ticketing_system.cli.models.Vendor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class VendorDAOImpl implements VendorDAO {

    private static VendorDAOImpl instance;
    private static Connection connection;

    private VendorDAOImpl() {
        try (Statement stmt = connection.createStatement()) {

            // Create the table if it doesn't exist
            stmt.execute("CREATE TABLE IF NOT EXISTS vendors (id INTEGER PRIMARY KEY AUTOINCREMENT, vendor_name VARCHAR(255) NOT NULL);");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Public method to provide global access to the instance
    public static VendorDAOImpl getInstance() {
        if (instance == null) {
            connection = SQLiteConnection.getInstance().getConnection();
            instance = new VendorDAOImpl();
        }
        return instance;
    }

    @Override
    public void addVendor(Vendor vendor) {
        String query = "INSERT INTO vendors(name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vendor.getVendorName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateVendor(Vendor vendor) {
        String query = "UPDATE vendors SET vendor_name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vendor.getVendorName());
            preparedStatement.setString(2, vendor.getVendorName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteVendor(int vendorId) {
        String query = "DELETE FROM vendors WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vendorId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Vendor getVendor(int vendorId) {
        Vendor vendor = new Vendor();
        String query = "SELECT * FROM vendors WHERE id = ?";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                vendor.setId(resultSet.getInt("id"));
                vendor.setVendorName(resultSet.getString("vendor_name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return vendor;
    }

    @Override
    public List<Vendor> getAllVendors() {
        List<Vendor> vendors = new ArrayList<>();
        String query = "SELECT * FROM vendors;";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                vendors.add(new Vendor(resultSet.getInt("id"),
                        resultSet.getString("name"), null));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return vendors;
    }

    @Override
    public List<Vendor> getAllVendorsWithTicket() {
        List<Vendor> vendors = new ArrayList<>();
        String query = "SELECT * FROM vendors, tickets WHERE vendors.id = tickets.vendor_id;";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println(resultSet);
//                vendors.add(new Vendor(resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        new Ticket()));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return vendors;
    }
}
