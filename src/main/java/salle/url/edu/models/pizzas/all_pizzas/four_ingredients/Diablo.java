package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Diablo extends Pizza {
    public Diablo() {
        super("Diablo");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.HAM);
        addIngredient(Ingredient.BEEF);
        addIngredient(Ingredient.BACON);
        addIngredient(Ingredient.CHICKEN);
    }
}
