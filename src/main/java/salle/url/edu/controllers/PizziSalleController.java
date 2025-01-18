package salle.url.edu.controllers;

import lombok.RequiredArgsConstructor;
import salle.url.edu.enums.Delegation;
import salle.url.edu.models.Customer;
import salle.url.edu.models.Order;
import salle.url.edu.models.delegations.GeneralDelegation;
import salle.url.edu.models.pizzas.Pizza;
import salle.url.edu.services.CustomerService;
import salle.url.edu.services.OrderService;
import salle.url.edu.services.PizzaService;

import java.sql.SQLException;
import java.util.Random;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class PizziSalleController {
    private final PizzaService pizzaService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final GeneralDelegation general;

    private final String resetColor = "\u001B[0m";
    private final String redColor = "\u001B[31m";
    private final String greenColor = "\u001B[32m";
    private final String yellowColor = "\u001B[33m";
    private final String blueColor = "\u001B[34m";
    private final String purpleColor = "\u001B[35m";
    private final String cyanColor = "\u001B[36m";

    public void run() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println(purpleColor + "\n🍕 Welcome to PizziSalle! The best pizzeria in town! 🍕" + resetColor);
        do {
            System.out.println(blueColor + "\n" + "=".repeat(60));
            System.out.println("MAIN MENU");
            System.out.println("=".repeat(60) + resetColor);
            System.out.println(cyanColor + "1. " + yellowColor + "Make an Order 🍕");
            System.out.println(cyanColor + "2. " + yellowColor + "Show Orders by Phone Number 📞");
            System.out.println(cyanColor + "3. " + yellowColor + "Log Out 🚪" + resetColor);
            System.out.print(greenColor + "\n-> Choose an option: " + resetColor);

            option = scanner.nextInt();
            scanner.nextLine(); // Clean buffer

            switch (option) {
                case 1:
                    System.out.println(greenColor + "✔️ Let's make a delicious pizza order!" + resetColor);
                    this.makeOrder();
                    break;
                case 2:
                    System.out.println(blueColor + "🔍 Let's find your orders by phone number." + resetColor);
                    String phoneNumber = customerService.askForPhone();
                    List<Order> orders = this.getOrdersByPhone(phoneNumber);
                    this.orderService.listOrders(orders, phoneNumber);
                    break;
                case 3:
                    System.out.println(redColor + "👋 See you soon! Thank you for choosing PizziSalle! 🍕" + resetColor);
                    break;
                default:
                    System.out.println(redColor + "❌ Error: Invalid option! Please try again." + resetColor);
            }
        } while (option != 3);
    }

    public void makeOrder() throws SQLException {
        // Generate a random delegation
        Delegation[] delegations = Delegation.values();
        String delegation = delegations[new Random().nextInt(delegations.length)].getName();
        // Show the welcome message
        pizzaService.setDelegation(delegation);
        pizzaService.showWelcomeMessage();

        // Manage the pizza order and get the customer info
        boolean newCustomer = customerService.askIfFirstOrder();
        Customer customer;
        int customerId;
        String phone = customerService.askForPhone();
        if (newCustomer) {
            customer = customerService.getCustomerInfo(phone);
        }
        else{
            customer = customerService.getCustomerInfoByPhone(phone);
            if (customer == null) {
                System.out.println("Customer not found, please enter all the information");
                customer = customerService.getCustomerInfo(phone);
            }
            else {
                System.out.println("Welcome back " + customer.getName() + "!");
            }

        }
        customerId = this.customerService.saveCustomer(customer);

        Pizza pizza = pizzaService.managePizzaOrder();

        // Once we now the age, we can ask for the beverage
        orderService.askForBeverage(pizza, customer.getAge());

        // Save the order in the database
        int orderId = orderService.saveOrder(customerId, delegation, pizza);

        // Save the pizza
        pizzaService.savePizza(orderId, pizza);

        // Notify the General Delegation of the new order (Observer pattern)
        general.notifyOrder(new Order(customer, delegation, pizza));
    }

    public List<Order> getOrdersByPhone(String phoneNumber) {
        Integer customerId = customerService.getCustomerIdByPhone(phoneNumber);
        if (customerId == null) {
            return null;
        }

        return orderService.getOrdersDetails(customerId);
    }
}
