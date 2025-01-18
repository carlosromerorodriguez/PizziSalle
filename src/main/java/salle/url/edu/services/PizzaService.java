package salle.url.edu.services;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import salle.url.edu.enums.Crust;
import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;
import salle.url.edu.models.pizzas.decorators.CrustDecorator;
import salle.url.edu.repositories.PizzaRepository;
import salle.url.edu.utils.ValidationUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Setter
public class PizzaService {
    private final Scanner scanner = new Scanner(System.in);
    private String delegation;
    private final PizzaRepository pizzaRepository;

    private final String resetColor = "\u001B[0m";
    private final String redColor = "\u001B[31m";
    private final String greenColor = "\u001B[32m";
    private final String yellowColor = "\u001B[33m";
    private final String blueColor = "\u001B[34m";
    private final String purpleColor = "\u001B[35m";
    private final String cyanColor = "\u001B[36m";

    public void showWelcomeMessage() {
        System.out.println("\nWelcome to PizziSalle " + this.delegation + " delegation!");
    }

    public Pizza managePizzaOrder() {
        Pizza basePizza = selectBasePizza();

        System.out.print("-> Please, select a crust (1. Original, 2. Thin, 3. Sicilian): ");
        basePizza = selectCrust(basePizza);

        System.out.println("-> Add extra ingredients: ");
        addExtraIngredients(basePizza);

        System.out.println("\n\u001B[32mPizza created successfully!\u001B[0m\n");

        return basePizza;
    }

    private void addExtraIngredients(Pizza pizza) {
        while (true) {
            Ingredient[] ingredients = Ingredient.values();
            showIngredientsMenu(pizza, ingredients);

            System.out.print("\n-> Add an ingredient (type " + greenColor + "'done' "+ resetColor +  " to finish): ");
            String ingredientInput = scanner.nextLine();

            if ("done".equalsIgnoreCase(ingredientInput)) break;

            int idxIngredient = askForIngredientInput(ingredients.length, ingredientInput);
            Ingredient ingredient = Ingredient.getIngredient(ingredients[idxIngredient - 1].getName());

            checkIngredient(pizza, ingredient);
        }
    }

    private void checkIngredient(Pizza pizza, Ingredient ingredient) {
        if (ingredient == null) {
            System.out.println(redColor + "Invalid ingredient. Please try again." + resetColor);
        } else if (pizza.getIngredients().containsKey(ingredient) && pizza.getIngredients().get(ingredient) >= 10) {
            System.out.println(redColor + "You cannot add more than 10 of the same ingredient." + resetColor);
        } else {
            System.out.println(greenColor + "Adding " + ingredient.getName() + " to the pizza..." + resetColor);
            pizza.addIngredient(ingredient);
        }
    }

    private int askForIngredientInput(int max, String ingredientInput) {
        do {
            if (!ValidationUtils.checkInt(1, max, ingredientInput)) {
                System.out.print(redColor + "Invalid ingredient. Please try again: " + resetColor);
                ingredientInput = scanner.nextLine();
            } else {
                return Integer.parseInt(ingredientInput);
            }
        } while (true);
    }

    private void showIngredientsMenu(Pizza pizza, Ingredient[] ingredients) {
        System.out.println("Current ingredients:");
        pizza.getIngredients().forEach((ingredient, quantity) -> System.out.printf("\t%-15s: %d\n", ingredient.getName(), quantity));
        System.out.println("\nAvailable ingredients:");

        for (int i = 0; i < ingredients.length; i++) {
            if (i % 4 == 0 && i != 0) System.out.println();
            System.out.printf("\t%-25s", (i + 1) + ". " + ingredients[i].getName());
        }
        System.out.println();
    }

    private Pizza selectBasePizza() {
        int pizzaChoice = showMenu();

        return PizzaFactory.createPizza(pizzaChoice, this.delegation);
    }

    private Pizza selectCrust(Pizza pizza) {
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "1": return new CrustDecorator(pizza, Crust.ORIGINAL);
                case "2": return new CrustDecorator(pizza, Crust.THIN);
                case "3": return new CrustDecorator(pizza, Crust.SICILIAN);
                default: System.out.println(redColor + "Invalid choice. Please select again." + resetColor);
            }
        }
    }

    public int showMenu() {
        System.out.println(blueColor + "=".repeat(50));
        System.out.println("🍕 Welcome to PizziSalle Menu 🍕");
        System.out.println("=".repeat(50) + resetColor);
        System.out.println(greenColor + "These are the available pizzas (all contain tomato sauce 🍅 and cheese 🧀 by default):" + resetColor);

        // No ingredients pizzas
        System.out.println(cyanColor + "🍕 No ingredients" + resetColor);
        System.out.println("\t" + yellowColor + "1. Margherita" + resetColor);

        // 2 ingredient pizzas
        System.out.println(cyanColor + "🍕 2 ingredients" + resetColor);
        System.out.println("\t" + yellowColor + "2. Hawaiian (Ham & Pineapple)" + resetColor);

        // 3 ingredient pizzas
        System.out.println(cyanColor + "🍕 3 ingredients" + resetColor);
        System.out.println("\t" + yellowColor + "3. Bacon Crispy (Ham, Chicken & Bacon)" + resetColor);
        System.out.println("\t" + yellowColor + "4. American (Frankfurt, bacon & egg)" + resetColor);
        System.out.println("\t" + yellowColor + "5. Traviata (Bacon, sausage & onion)" + resetColor);

        // 4 ingredient pizzas
        System.out.println(cyanColor + "🍕 4 ingredients" + resetColor);
        System.out.println("\t" + yellowColor + "6. Burger (Miniburgers, egg, bacon & onion)" + resetColor);
        System.out.println("\t" + yellowColor + "7. Castellera (Onion, tuna, peperoni & olives)" + resetColor);
        System.out.println("\t" + yellowColor + "8. Cowboy (BBQ Sauce, cheddar, roquefort cheese & bacon)" + resetColor);
        System.out.println("\t" + yellowColor + "9. Texas (BBQ Sauce, onion, tomato slices & chicken)" + resetColor);
        System.out.println("\t" + yellowColor + "10. Coast (Tuna, anchovies, prawns & pineapple)" + resetColor);
        System.out.println("\t" + yellowColor + "11. BBQ (BBQ Sauce, beef, bacon & chicken)" + resetColor);
        System.out.println("\t" + yellowColor + "12. Diablo (Ham, beef, bacon & chicken)" + resetColor);
        System.out.println("\t" + yellowColor + "13. Carbonara (Carbonara sauce, bacon, onion & mushrooms)" + resetColor);
        System.out.println("\t" + yellowColor + "14. Spanish (Jamón Serrano, brie, olives & mushrooms)" + resetColor);
        System.out.println("\t" + yellowColor + "15. 4 Cheeses (Mozzarella, emmental, roquefort & cheddar)" + resetColor);
        System.out.println("\t" + yellowColor + "16. Pepperoni (Pepperoni, ham, beef & bacon)" + resetColor);

        // 5 ingredient pizzas
        System.out.println(cyanColor + "🍕 5 ingredients" + resetColor);
        System.out.println("\t" + yellowColor + "17. Vegetal (Onion, bell pepper, tomato slices, artichoke & mushrooms)" + resetColor);

        // 6 ingredient pizzas
        System.out.println(cyanColor + "🍕 6 ingredients" + resetColor);
        System.out.println("\t" + yellowColor + "18. 6 Cheeses (Mozzarella, goat cheese, brie, emmental, roquefort & cheddar)" + resetColor);
        System.out.println("\t" + yellowColor + "19. Mallorca (Goat cheese, emmental, cheddar, brie, sobrassada & olives)" + resetColor);
        System.out.println("\t" + yellowColor + "20. Carbonara Deluxe (Carbonara sauce, bacon, onion, mushrooms, goat cheese & honey)" + resetColor);

        // Special pizzas
        System.out.println(cyanColor + "🎁 Special pizzas" + resetColor);
        System.out.println("\t" + yellowColor + "21. " + this.delegation + " Pizza" + resetColor);

        System.out.print(greenColor + "\n-> Please, select a pizza by typing its number: " + resetColor);

        do {
            String line = scanner.nextLine();
            if (ValidationUtils.checkInt(1, 21, line)) {
                return Integer.parseInt(line);
            } else {
                System.out.print(redColor + "❌ ERROR: Please, select a valid pizza number: " + resetColor);
            }
        } while (true);
    }

    public List<String> getOrderDetailsByOrderId(int orderId) {
        return pizzaRepository.findPizzasByOrderId(orderId);
    }

    public void savePizza(int orderId, Pizza pizza) throws SQLException {
        this.pizzaRepository.savePizza(orderId, pizza);
    }
}
