package salle.url.edu.repositories;

import salle.url.edu.database.DatabaseConnection;
import salle.url.edu.models.Customer;
import salle.url.edu.services.CustomerService;

import java.sql.*;

public class CustomerRepository {
    private final Connection connection;

    public CustomerRepository() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public int saveCustomer(Customer customer) throws SQLException {
        int existingCustomerId = checkCustomerExists(customer.getPhone());
        if (existingCustomerId != -1) {
            return existingCustomerId;
        }

        String insertCustomerSQL = """
                INSERT INTO customers (name, age, phone, address, isFirstOrder)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(insertCustomerSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getName());
            stmt.setInt(2, customer.getAge());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getAddress());
            stmt.setBoolean(5, customer.isFirstOrder());
            stmt.executeUpdate();

            // Get the generated customerId
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve customer ID.");
            }
        }
    }

    private int checkCustomerExists(String phone) {
        String query = "SELECT id FROM customers WHERE phone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Integer findCustomerIdByPhone(String phoneNumber) {
        String query = "SELECT id FROM customers WHERE phone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, phoneNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Devuelve null si no se encuentra el cliente
    }
    public Customer findCustomerById(int customerId) {
        String query = "SELECT id, name, age, phone, address, isFirstOrder FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getBoolean("isFirstOrder")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer findCustomerByPhone(String phone) {
        String query = "SELECT id, name, age, phone, address, isFirstOrder FROM customers WHERE phone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getBoolean("isFirstOrder")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}