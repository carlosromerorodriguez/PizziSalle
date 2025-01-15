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
        System.out.println("\nWelcome to PizziSalle!");
        do {
            System.out.println("\n1. Make an order");
            System.out.println("2. Show orders by phone number");
            System.out.println("3. Log out");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Clean buffer

            switch (option) {
                case 1:
                    try {
                        pizziSalleController.run();
                    } catch (SQLException e) {
                        System.out.println("Error processing the order: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Give us your phone number: ");
                    String phoneNumber = scanner.nextLine();
                    List<Order> orders = pizziSalleController.getOrdersByPhone(phoneNumber);

                    if (orders == null || orders.isEmpty()) {
                        System.out.println("We couldn't find any orders associated to that phone number.");
                    } else {
                        System.out.println("\nYour orders:");
                        for (Order order : orders) {
                            System.out.println("Order " + order.getPizza().getName() +
                                    " - Delegation: " + order.getDelegation() +
                                    " - Client: " + order.getCustomer().getName());
                        }
                    }
                    break;
                case 3:
                    System.out.println("...");
                    break;
                default:
                    System.out.println("Error. Invalid option!");
            }
        } while (option != 3);
    }
}
