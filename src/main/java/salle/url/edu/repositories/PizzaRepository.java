package salle.url.edu.repositories;

import salle.url.edu.database.DatabaseConnection;
import salle.url.edu.models.pizzas.Pizza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PizzaRepository {
    private final Connection connection;

    public PizzaRepository() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }
    }

    public void savePizza(int orderId, Pizza pizza) throws SQLException {
        String insertPizzaSQL = """
                INSERT INTO order_pizzas (orderId, pizzaName, ingredients, crustType, drink)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(insertPizzaSQL)) {
            stmt.setInt(1, orderId);
            stmt.setString(2, pizza.getName());
            stmt.setString(3, String.join(", ", pizza.getIngredients().keySet().stream().map(Enum::name).toList()));
            stmt.setString(4, pizza.getCrust().name());
            stmt.setString(5, pizza.getBeverage().getName());
            stmt.executeUpdate();
        }
    }
}
