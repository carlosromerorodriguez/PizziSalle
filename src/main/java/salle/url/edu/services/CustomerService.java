package salle.url.edu.services;

import lombok.RequiredArgsConstructor;
import salle.url.edu.enums.Delegation;
import salle.url.edu.models.Customer;
import salle.url.edu.repositories.CustomerRepository;
import salle.url.edu.utils.ValidationUtils;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

@RequiredArgsConstructor
public class CustomerService {
    private final Scanner scanner = new Scanner(System.in);
    private final CustomerRepository customerRepository;

    private final String redColor = "\u001B[31m";
    private final String resetColor = "\u001B[0m";
    private final String yellowColor = "\u001B[33m";

    public Customer getCustomerInfo() {
        String phone = this.askForPhone();
        String name = this.askForName();
        int age = this.askForAge();
        String address = this.askForDeliveryAddress();

        return new Customer(name, age, phone, address, true);
    }

    public Customer getCustomerInfo(String phone) {
        String name = this.askForName();
        int age = this.askForAge();
        String address = this.askForDeliveryAddress();

        return new Customer(name, age, phone, address, true);
    }

    private String askForName() {
        do {
            System.out.print("Enter your name: ");
            String line = scanner.nextLine();
            if (ValidationUtils.checkName(line)) {
                return line;
            } else {
                System.out.println(redColor + "ERROR: Name must be between 3 and 50 characters and only letters." + resetColor);
            }
        } while (true);
    }

    private int askForAge() {
        do {
            System.out.print("Enter your age: ");
            String line = scanner.nextLine();
            if (ValidationUtils.checkInt(10, 100, line)) {
                return Integer.parseInt(line);
            } else {
                System.out.println(redColor + "ERROR: Age must be between 10 and 100." + resetColor);
            }
        } while (true);
    }

    public String askForPhone() {
        do {
            System.out.print("Enter your phone number: ");
            String line = scanner.nextLine();
            if (ValidationUtils.checkPhone(line)) {
                return line;
            } else {
                System.out.println(redColor + "ERROR: Phone number must be 9 digits." + resetColor);
            }
        } while (true);
    }

    private String askForDeliveryAddress() {
        do {
            System.out.print("Enter your delivery address: ");
            String line = scanner.nextLine();
            if (ValidationUtils.checkAddress(line)) {
                return line;
            } else {
                System.out.println(redColor + "ERROR: Address must be between 10 and 100 characters." + resetColor);
            }
        } while (true);
    }

    public boolean askIfFirstOrder() {
        System.out.println("\n" + yellowColor + "Is this your first order?" + resetColor);
        System.out.println("1- Yes");
        System.out.println("2- No");

        do {
            System.out.print("\n-> Choose an option: ");
            String line = scanner.nextLine();
            if (ValidationUtils.checkInt(1, 2, line)) {
                return Integer.parseInt(line) == 1;
            } else {
                System.out.println(redColor + "ERROR: Option must be between 1 and 2." + resetColor);
            }
        } while (true);
    }

    public int saveCustomer(Customer customer) throws SQLException {
        return this.customerRepository.saveCustomer(customer);
    }

    public Customer getCustomerById(int customerId) {
        return customerRepository.findCustomerById(customerId);
    }

    public Integer getCustomerIdByPhone(String phoneNumber) {
        return customerRepository.findCustomerIdByPhone(phoneNumber);
    }

    public Customer getCustomerInfoByPhone(String phone) {
        return customerRepository.findCustomerByPhone(phone);
    }
}
