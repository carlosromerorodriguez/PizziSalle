package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Coast extends Pizza {
    public Coast(String name) {
        super("Coast");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.TUNA);
        addIngredient(Ingredient.ANCHOVIES);
        addIngredient(Ingredient.PRAWNS);
        addIngredient(Ingredient.PINEAPPLE);
    }
}
