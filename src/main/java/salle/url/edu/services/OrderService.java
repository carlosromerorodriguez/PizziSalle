package salle.url.edu.services;

import lombok.RequiredArgsConstructor;
import salle.url.edu.enums.Beverage;
import salle.url.edu.models.Customer;
import salle.url.edu.models.Order;
import salle.url.edu.models.delegations.GeneralDelegation;
import salle.url.edu.models.pizzas.Pizza;
import salle.url.edu.repositories.CustomerRepository;
import salle.url.edu.repositories.OrderRepository;
import salle.url.edu.repositories.PizzaRepository;
import salle.url.edu.utils.ValidationUtils;
import salle.url.edu.models.pizzas.all_pizzas.no_ingredients.Margherita;
import salle.url.edu.models.pizzas.all_pizzas.two_ingredients.Hawaiian;
import salle.url.edu.models.pizzas.all_pizzas.three_ingredients.BaconCrispy;
import salle.url.edu.models.pizzas.all_pizzas.three_ingredients.American;
import salle.url.edu.models.pizzas.all_pizzas.three_ingredients.Traviata;


import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

@RequiredArgsConstructor
public class OrderService {

    // PATTERN: Patron Strategy - {Behavioral Pattern}
    //         This class is a concrete strategy that implements the BeverageValidation interface
    //         and is used to validate if a person can consume a beer based on the age
    //         is used to represent the different strategies to validate if a person can consume a beverage
    //         based on the age

    private final Scanner scanner = new Scanner(System.in);
    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;
    private final CustomerService customerService;

    String redColor = "\u001B[31m";
    String resetColor = "\u001B[0m";

    public int saveOrder(int customerId, String delegation, Pizza pizza) throws SQLException {
        // Save the order and get the orderId
        return this.orderRepository.saveOrder(customerId, delegation);
    }

    public void askForBeverage(Pizza pizza, int age) {
        System.out.println("Which beverage would you like to add? (1. Water, 2. Soda, 3. Beer)");

        do {
            System.out.print("\n-> Choose an option: ");
            String line = scanner.nextLine();

            if (ValidationUtils.checkInt(1, 3, line)) {
                int choice = Integer.parseInt(line);
                Beverage beverage = Beverage.values()[choice - 1];

                if (!beverage.canConsume(age)) {
                    System.out.println(redColor + "You are not allowed to consume beer." + resetColor);
                    continue;
                }

                pizza.setBeverage(beverage);
                System.out.println("Beverage added: " + beverage.getName());
                return;
            } else {
                System.out.println(redColor + "ERROR: Option must be between 1 and 3." + resetColor);
            }
        } while (true);
    }
    public List<Integer> getOrderIdsByCustomerId(int customerId) {
        return orderRepository.findOrdersByCustomerId(customerId);
    }

    public List<Order> getOrdersDetails(int customerId) {
        List<Integer> orderIds = orderRepository.findOrdersByCustomerId(customerId);
        List<Order> orders = new ArrayList<>();

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Error: No se encontró información del cliente.");
            return orders;
        }

        for (Integer orderId : orderIds) {
            List<String> pizzaNames = pizzaService.getOrderDetailsByOrderId(orderId);
            String delegation = orderRepository.getDelegationByOrderId(orderId); // Método para obtener la delegación

            for (String pizzaName : pizzaNames) {
                Pizza pizza = createPizzaFromName(pizzaName, delegation);

                if (pizza != null) {
                    orders.add(new Order(customer, delegation, pizza));
                } else {
                    System.out.println("Error: No se pudo crear la pizza '" + pizzaName + "'");
                }
            }
        }
        return orders;
    }
    private Pizza createPizzaFromName(String pizzaName, String delegation) {
        switch (pizzaName.toLowerCase()) {
            case "margherita":
                return new Margherita();
            case "hawaiian":
                return new Hawaiian();
            case "bacon crispy":
                return new BaconCrispy();
            case "american":
                return new American();
            case "traviata":
                return new Traviata();
            default:
                return PizzaFactory.createPizza(0, delegation); // Crea una pizza específica según la delegación
        }
    }
}
