package salle.url.edu.database;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Data
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final Connection connection;

    private DatabaseConnection() throws SQLException {
        String url = "jdbc:sqlite:src/main/resources/pizzisalle.db";
        connection = DriverManager.getConnection(url);
    }

    // PATTERN: Singleton used for the DatabaseConnection class to ensure only one instance is created,
    //          which helps to avoid multiple connections to the database
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void initializeDatabase() {
        try (Statement stmt = connection.createStatement()) {
            // Customers database table
            createCustomersTable(stmt);

            // Orders database table
            createOrdersTable(stmt);

            // Pizza orders database table
            createPizzaOrdersTable(stmt);
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    private void createCustomersTable(Statement stmt) throws SQLException {
        String createCustomersTable = """
                    CREATE TABLE IF NOT EXISTS customers (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        age INTEGER NOT NULL,
                        phone TEXT NOT NULL,
                        address TEXT NOT NULL,
                        isFirstOrder BOOLEAN NOT NULL
                    );
                    """;
        stmt.execute(createCustomersTable);
    }

    private void createOrdersTable(Statement stmt) throws SQLException {
        String createOrdersTable = """
                    CREATE TABLE IF NOT EXISTS orders (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        customerId INTEGER NOT NULL,
                        delegation TEXT NOT NULL,
                        FOREIGN KEY (customerId) REFERENCES customers (id)
                    );
                    """;
        stmt.execute(createOrdersTable);
    }

    private void createPizzaOrdersTable(Statement stmt) throws SQLException {
        String createOrderPizzasTable = """
                    CREATE TABLE IF NOT EXISTS order_pizzas (
                        orderId INTEGER NOT NULL,
                        pizzaName TEXT NOT NULL,
                        ingredients TEXT,
                        crustType TEXT,
                        drink TEXT,
                        FOREIGN KEY (orderId) REFERENCES orders (id)
                    );
                    """;
        stmt.execute(createOrderPizzasTable);
    }
}