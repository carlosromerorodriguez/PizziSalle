package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Burger extends Pizza {
    public Burger() {
        super("Burger");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.MINIBURGERS);
        addIngredient(Ingredient.EGG);
        addIngredient(Ingredient.BACON);
        addIngredient(Ingredient.ONION);
    }
}
