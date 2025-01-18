package salle.url.edu.services;

import lombok.RequiredArgsConstructor;
import salle.url.edu.enums.Beverage;
import salle.url.edu.models.Customer;
import salle.url.edu.models.Order;
import salle.url.edu.models.pizzas.Pizza;
import salle.url.edu.repositories.OrderRepository;
import salle.url.edu.utils.ValidationUtils;


import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

@RequiredArgsConstructor
public class OrderService {
    private final Scanner scanner = new Scanner(System.in);
    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;
    private final CustomerService customerService;

    private final String redColor = "\u001B[31m";
    private final String resetColor = "\u001B[0m";

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

    public List<Order> getOrdersDetails(int customerId) {
        List<Integer> orderIds = orderRepository.findOrdersByCustomerId(customerId);
        List<Order> orders = new ArrayList<>();

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("ERROR: Client couldn't be found.");
            return orders;
        }

        for (Integer orderId : orderIds) {
            List<String> pizzaNames = pizzaService.getOrderDetailsByOrderId(orderId);
            String delegation = orderRepository.getDelegationByOrderId(orderId); // Method to obtain the delegation

            for (String pizzaName : pizzaNames) {
                Pizza pizza = PizzaFactory.createPizzaByName(pizzaName, delegation);
                orders.add(new Order(customer, delegation, pizza));
            }
        }
        return orders;
    }

    public void listOrders(List<Order> orders, String phoneNumber) {
        if (orders == null || orders.isEmpty()) {
            System.out.println(redColor + "❌ ERROR: We couldn't find any orders associated with the phone number: " + phoneNumber + resetColor);
            return;
        }

        System.out.println("\n📋 Here are your orders for phone number: " + phoneNumber);
        System.out.println("-".repeat(60));
        System.out.printf("%-5s | %-20s | %-15s | %-15s\n", "No.", "Pizza Name 🍕", "Delegation 📍", "Client 🧑‍🍳");
        System.out.println("-".repeat(60));

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.printf("%-5d | %-20s | %-15s | %-15s\n",
                    i + 1,
                    order.getPizza().getName(),
                    order.getDelegation(),
                    order.getCustomer().getName());
        }
        System.out.println("-".repeat(60));
        System.out.println("Thank you for choosing our pizzeria! 🍕");
    }
}
