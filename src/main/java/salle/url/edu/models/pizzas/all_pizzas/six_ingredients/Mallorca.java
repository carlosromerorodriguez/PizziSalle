package salle.url.edu.models.pizzas.all_pizzas.six_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Mallorca extends Pizza {
    public Mallorca() {
        super("Mallorca");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.GOAT_CHEESE, 1);
        getIngredients().put(Ingredient.BRIE, 1);
        getIngredients().put(Ingredient.CHEDDAR, 1);
        getIngredients().put(Ingredient.EMMENTAL, 1);
        getIngredients().put(Ingredient.SOBRASSADA, 1);
        getIngredients().put(Ingredient.OLIVES, 1);
    }
}
