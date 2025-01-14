package salle.url.edu.models.pizzas.all_pizzas.five_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Barcelona extends Pizza {
    public Barcelona(String name) {
        super("Barcelona");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.ONION, 1);
        getIngredients().put(Ingredient.BEEF, 1);
        getIngredients().put(Ingredient.BRIE, 1);
        getIngredients().put(Ingredient.HAM, 1);
        getIngredients().put(Ingredient.OLIVES, 1);
    }
}
