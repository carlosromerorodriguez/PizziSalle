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

import java.sql.SQLException;
import java.util.Scanner;

@RequiredArgsConstructor
public class OrderService {
    private final Scanner scanner = new Scanner(System.in);
    private final OrderRepository orderRepository;

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
                    System.out.println("You are not allowed to consume beer.");
                    continue;
                }

                pizza.setBeverage(beverage);
                System.out.println("Beverage added: " + beverage.getName());
                return;
            } else {
                System.out.println("ERROR: Option must be between 1 and 3.");
            }
        } while (true);
    }
}
