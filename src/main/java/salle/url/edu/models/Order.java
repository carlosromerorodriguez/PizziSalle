package salle.url.edu.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import salle.url.edu.models.pizzas.Pizza;

@AllArgsConstructor
@Data
public class Order {
    private Customer customer;
    private String delegation;
    private Pizza pizza;
}