package salle.url.edu.services;

import salle.url.edu.models.pizzas.Pizza;
import salle.url.edu.models.pizzas.all_pizzas.five_ingredients.Vegetal;
import salle.url.edu.models.pizzas.all_pizzas.four_ingredients.*;
import salle.url.edu.models.pizzas.all_pizzas.no_ingredients.Margherita;
import salle.url.edu.models.pizzas.all_pizzas.six_ingredients.CarbonaraDeluxe;
import salle.url.edu.models.pizzas.all_pizzas.six_ingredients.Mallorca;
import salle.url.edu.models.pizzas.all_pizzas.six_ingredients.SixCheeses;
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

    // PATTERN: Factory Method - {Creational Pattern}
    //          used to create a Pizza object because the Pizza class is abstract,
    //          and we need to create instances of its subclasses (e.g. Margherita, Pepperoni, etc.)

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
                case 6 -> new Burger();
                case 7 -> new Castellera();
                case 8 -> new Cowboy();
                case 9 -> new Texas();
                case 10 -> new Coast();
                case 11 -> new BBQ();
                case 12 -> new Diablo();
                case 13 -> new Carbonara();
                case 14 -> new Spanish();
                case 15 -> new FourCheeses();
                case 16 -> new Pepperoni();
                case 17 -> new Vegetal();
                case 18 -> new SixCheeses();
                case 19 -> new Mallorca();
                case 20 -> new CarbonaraDeluxe();

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

    public static Pizza createPizzaByName(String pizzaName, String delegation) {
        return switch (pizzaName.toLowerCase()) {
            case "margherita" -> new Margherita();
            case "hawaiian" -> new Hawaiian();
            case "bacon crispy" -> new BaconCrispy();
            case "american" -> new American();
            case "traviata" -> new Traviata();
            case "burger" -> new Burger();
            case "castellera" -> new Castellera();
            case "cowboy" -> new Cowboy();
            case "texas" -> new Texas();
            case "coast" -> new Coast();
            case "bbq" -> new BBQ();
            case "diablo" -> new Diablo();
            case "carbonara" -> new Carbonara();
            case "spanish" -> new Spanish();
            case "4 cheeses" -> new FourCheeses();
            case "pepperoni" -> new Pepperoni();
            case "vegetal" -> new Vegetal();
            case "6 cheeses" -> new SixCheeses();
            case "mallorca" -> new Mallorca();
            case "carbonara deluxe" -> new CarbonaraDeluxe();
            default -> PizzaFactory.createPizza(0, delegation); // Creates a pizza based on the delegation
        };
    }
}
