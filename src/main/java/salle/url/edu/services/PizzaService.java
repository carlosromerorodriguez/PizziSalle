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
import java.util.Scanner;

@RequiredArgsConstructor
@Setter
public class PizzaService {
    private final Scanner scanner = new Scanner(System.in);
    private String delegation;
    private final PizzaRepository pizzaRepository;

    String redColor = "\u001B[31m";
    String resetColor = "\u001B[0m";
    String greenColor = "\u001B[32m";

    public void showWelcomeMessage() {
        System.out.println("Welcome to PizziSalle " + this.delegation + " delegation!");
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

            System.out.print("-> Add an ingredient (type " + greenColor + "'done' "+ resetColor +  "finish): ");
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
        System.out.println("-".repeat(20));
        System.out.println("Menu");
        System.out.println("-".repeat(20));
        System.out.println("These are the available pizzas (all contains tomato sauce \uD83C\uDF45 and cheese \uD83E\uDDC0 by default):");

        // No ingredients pizzas
        System.out.println("\uD83C\uDF55 No ingredients");
        System.out.println("\t1. Margherita");

        // 2 ingredient pizzas
        System.out.println("\uD83C\uDF55 2 ingredients");
        System.out.println("\t2. Hawaiian (Ham & Pineapple)");

        // 3 ingredient pizzas
        System.out.println("\uD83C\uDF55 3 ingredients");
        System.out.println("\t3. Bacon Crispy (Ham, Chicken & Bacon)");
        System.out.println("\t4. American (Frankfurt, bacon & egg)");
        System.out.println("\t5. Traviata (Bacon, sausage & onion)");

        // TODO: Add more pizzas to the menu

        // Special pizzas
        System.out.println("\uD83C\uDF81 Special pizzas");
        System.out.println("\t6. " + this.delegation + "Pizza");

        System.out.print("\n-> Please, select a pizza by typing its number: ");

        do {
            String line = scanner.nextLine();
            if (ValidationUtils.checkInt(1, 6, line)) {
                return Integer.parseInt(line);
            } else {
                System.out.print(redColor + "ERROR: Please, select a valid pizza number: " + resetColor);
            }
        } while (true);
    }

    public void savePizza(int orderId, Pizza pizza) throws SQLException {
        this.pizzaRepository.savePizza(orderId, pizza);
    }
}
