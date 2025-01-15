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

@RequiredArgsConstructor
public class PizziSalleController {
    private final PizzaService pizzaService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final GeneralDelegation general;

    public void run() throws SQLException {
        // Generate a random delegation
        Delegation[] delegations = Delegation.values();
        //String delegation = delegations[new Random().nextInt(delegations.length)].getName();
    String delegation = Delegation.BARCELONA.getName();
        // Show the welcome message
        pizzaService.setDelegation(delegation);
        pizzaService.showWelcomeMessage();

        // Manage the pizza order and get the customer info
        Customer customer = customerService.getCustomerInfo();
        Pizza pizza = pizzaService.managePizzaOrder();

        // Save the client into database and get the customerId
        int customerId = this.customerService.saveCustomer(customer);

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
