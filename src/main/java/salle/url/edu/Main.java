package salle.url.edu;

import salle.url.edu.database.DatabaseConnection;
import salle.url.edu.models.Order;
import salle.url.edu.models.delegations.GeneralDelegation;
import salle.url.edu.models.delegations.SpecificDelegation;
import salle.url.edu.repositories.CustomerRepository;
import salle.url.edu.repositories.OrderRepository;
import salle.url.edu.repositories.PizzaRepository;
import salle.url.edu.services.CustomerService;
import salle.url.edu.services.OrderService;
import salle.url.edu.controllers.PizziSalleController;
import salle.url.edu.services.PizzaService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Initialize database
            DatabaseConnection db = DatabaseConnection.getInstance();
            db.initializeDatabase();

            // 2. Initialize the general delegation
            GeneralDelegation general = getGeneralDelegation();

            // 3. Start the application with a menu
            PizziSalleController pizziSalleController = getPizziSalleController(general);
            runMenu(pizziSalleController);
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    private static GeneralDelegation getGeneralDelegation() {
        GeneralDelegation general = new GeneralDelegation("PizziSalle General");

        SpecificDelegation barcelona = new SpecificDelegation("Barcelona");
        SpecificDelegation girona = new SpecificDelegation("Girona");
        SpecificDelegation lleida = new SpecificDelegation("Lleida");
        SpecificDelegation tarragona = new SpecificDelegation("Tarragona");

        general.addDelegation(barcelona);
        general.addDelegation(girona);
        general.addDelegation(lleida);
        general.addDelegation(tarragona);

        return general;
    }

    private static PizziSalleController getPizziSalleController(GeneralDelegation general) {
        // Initialize repositories
        CustomerRepository customerRepository = new CustomerRepository();
        OrderRepository orderRepository = new OrderRepository();
        PizzaRepository pizzaRepository = new PizzaRepository();

        // Initialize services
        CustomerService customerService = new CustomerService(customerRepository);
        PizzaService pizzaService = new PizzaService(pizzaRepository);
        OrderService orderService = new OrderService(orderRepository, pizzaService, customerService);

        return new PizziSalleController(pizzaService, orderService, customerService, general);
    }

    private static void runMenu(PizziSalleController pizziSalleController) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Hacer un nuevo pedido");
            System.out.println("2. Ver órdenes por número de teléfono");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    try {
                        pizziSalleController.run();
                    } catch (SQLException e) {
                        System.out.println("Error al procesar el pedido: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Introduce tu número de teléfono: ");
                    String phoneNumber = scanner.nextLine();
                    List<Order> orders = pizziSalleController.getOrdersByPhone(phoneNumber);

                    if (orders == null || orders.isEmpty()) {
                        System.out.println("No se encontraron órdenes para este número de teléfono.");
                    } else {
                        System.out.println("\nTus órdenes:");
                        for (Order order : orders) {
                            System.out.println("Orden #" + order.getPizza().getName() +
                                    " - Delegación: " + order.getDelegation() +
                                    " - Cliente: " + order.getCustomer().getName());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 3);
    }
}
