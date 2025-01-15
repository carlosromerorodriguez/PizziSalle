package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Texas extends Pizza {

    public Texas() {
        super("Texas");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.BBQ_SAUCE);
        addIngredient(Ingredient.ONION);
        addIngredient(Ingredient.TOMATO_SLICES);
        addIngredient(Ingredient.CHICKEN);
    }
}
