//package main.dao.impl;
//
//import realtime_event_ticketing_system.cli.dao.VendorDAO;
//import realtime_event_ticketing_system.cli.db.SQLiteConnection;
//import realtime_event_ticketing_system.cli.models.Vendor;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class VendorDAOImpl implements VendorDAO {
//
//    private static VendorDAOImpl instance;
//    private static Connection connection;
//
//    private VendorDAOImpl() {
//        try (Statement stmt = connection.createStatement()) {
//
//            // Create the table if it doesn't exist
//            stmt.execute("CREATE TABLE IF NOT EXISTS vendors (\n" +
//                    "    vendor_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//                    "    vendor_name TEXT NOT NULL,\n" +
//                    "    tickets_per_release INTEGER NOT NULL, \n" +
//                    "    release_rate_sec INTEGER NOT NULL  \n" +
//                    ");");
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Public method to provide global access to the instance
//    public static VendorDAOImpl getInstance() {
//        if (instance == null) {
//            connection = SQLiteConnection.getInstance().getConnection();
//            instance = new VendorDAOImpl();
//        }
//        return instance;
//    }
//
//    @Override
//    public void addVendor(Vendor vendor) {
//        String query = "INSERT INTO vendors(name) VALUES (?)";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setString(1, vendor.getVendorName());
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public void updateVendor(Vendor vendor) {
//        String query = "UPDATE vendors SET vendor_name = ? WHERE id = ?";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setString(1, vendor.getVendorName());
//            preparedStatement.setString(2, vendor.getVendorName());
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public void deleteVendor(int vendorId) {
//        String query = "DELETE FROM vendors WHERE id = ?";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setInt(1, vendorId);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public Vendor getVendor(int vendorId) {
//        Vendor vendor = new Vendor();
//        String query = "SELECT * FROM vendors WHERE id = ?";
//
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//
//            while (resultSet.next()) {
//                vendor.setId(resultSet.getInt("vendor_id"));
//                vendor.setVendorName(resultSet.getString("vendor_name"));
//                vendor.setTicketsPerRelease(resultSet.getInt("tickets_per_release"));
//                vendor.setReleaseRateSec(resultSet.getInt("release_rate_sec"));
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return vendor;
//    }
//
//    @Override
//    public List<Vendor> getAllVendors() {
//        List<Vendor> vendors = new ArrayList<>();
//        String query = "SELECT * FROM vendors;";
//
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//
//            while (resultSet.next()) {
//                vendors.add(new Vendor(
//                        resultSet.getInt("vendor_id"),
//                        resultSet.getString("vendor_name"),
//                        resultSet.getInt("tickets_per_release"),
//                        resultSet.getInt("release_rate_sec")
//                ));
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return vendors;
//    }
//}
