package salle.url.edu.services;

import salle.url.edu.models.pizzas.Pizza;
import salle.url.edu.models.pizzas.all_pizzas.no_ingredients.Margherita;
import salle.url.edu.models.pizzas.all_pizzas.three_ingredients.American;
import salle.url.edu.models.pizzas.all_pizzas.three_ingredients.BaconCrispy;
import salle.url.edu.models.pizzas.all_pizzas.three_ingredients.Traviata;
import salle.url.edu.models.pizzas.all_pizzas.two_ingredients.Hawaiian;
import salle.url.edu.models.pizzas.specific_pizzas.BarcelonaPizza;
import salle.url.edu.models.pizzas.specific_pizzas.GironaPizza;
import salle.url.edu.models.pizzas.specific_pizzas.LleidaPizza;
import salle.url.edu.models.pizzas.specific_pizzas.TarragonaPizza;

import java.util.Map;

public class PizzaFactory {
    private static final Map<String, Class<? extends Pizza>> SPECIAL_PIZZAS = Map.of(
            "Barcelona", BarcelonaPizza.class,
            "Girona", GironaPizza.class,
            "Tarragona", TarragonaPizza.class,
            "Lleida", LleidaPizza.class
    );

    public static Pizza createPizza(int whichPizza, String delegation) {
        try {
            return switch (whichPizza) {
                case 1 -> new Margherita();
                case 2 -> new Hawaiian();
                case 3 -> new BaconCrispy();
                case 4 -> new American();
                case 5 -> new Traviata();
                default -> {
                    // Create the special pizza based on the delegation
                    Class<? extends Pizza> pizzaClass = SPECIAL_PIZZAS.get(delegation);
                    if (pizzaClass == null) {
                        throw new IllegalArgumentException("No special pizza defined for delegation: " + delegation);
                    }
                    yield pizzaClass.getDeclaredConstructor().newInstance();
                }
            };
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating pizza", e);
        }
    }
}
