package salle.url.edu.repositories;

import salle.url.edu.database.DatabaseConnection;
import salle.url.edu.models.pizzas.Pizza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepository {
    private final Connection connection;

    public OrderRepository() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }
    }

    public int saveOrder(int customerId, String delegation) throws SQLException {
        String insertOrderSQL = """
                INSERT INTO orders (customerId, delegation)
                VALUES (?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, delegation);
            stmt.executeUpdate();

            // Get the generated orderId
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve order ID.");
            }
        }
    }
}