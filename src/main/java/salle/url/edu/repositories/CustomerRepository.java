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
}