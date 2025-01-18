package salle.url.edu;

import salle.url.edu.database.DatabaseConnection;
import salle.url.edu.models.delegations.GeneralDelegation;
import salle.url.edu.models.delegations.SpecificDelegation;
import salle.url.edu.repositories.CustomerRepository;
import salle.url.edu.repositories.OrderRepository;
import salle.url.edu.repositories.PizzaRepository;
import salle.url.edu.services.CustomerService;
import salle.url.edu.services.OrderService;
import salle.url.edu.controllers.PizziSalleController;
import salle.url.edu.services.PizzaService;

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
            pizziSalleController.run();
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
}
