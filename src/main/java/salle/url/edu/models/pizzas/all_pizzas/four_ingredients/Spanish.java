package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Spanish extends Pizza {
    public Spanish(String name) {
        super("Spanish");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.HAM);
        addIngredient(Ingredient.BRIE);
        addIngredient(Ingredient.OLIVES);
        addIngredient(Ingredient.MUSHROOMS);
    }
}
