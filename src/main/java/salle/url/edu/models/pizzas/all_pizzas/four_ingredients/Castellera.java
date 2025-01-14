package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Castellera extends Pizza {
    public Castellera(String name) {
        super("Castellera");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.ONION);
        addIngredient(Ingredient.TUNA);
        addIngredient(Ingredient.PEPPERONI);
        addIngredient(Ingredient.OLIVES);
    }
}
