package salle.url.edu.models.pizzas.all_pizzas.five_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Vegetal extends Pizza {
    public Vegetal(String name) {
        super("Vegetal");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.ONION, 1);
        getIngredients().put(Ingredient.BELL_PEPPER, 1);
        getIngredients().put(Ingredient.TOMATO_SLICES, 1);
        getIngredients().put(Ingredient.ARTICHOKE, 1);
        getIngredients().put(Ingredient.MUSHROOMS, 1);
    }
}
