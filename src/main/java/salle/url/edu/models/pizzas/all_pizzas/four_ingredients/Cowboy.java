package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Cowboy extends Pizza {
    public Cowboy() {
        super("Cowboy");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.BBQ_SAUCE);
        addIngredient(Ingredient.CHEDDAR);
        addIngredient(Ingredient.ROQUEFORT);
        addIngredient(Ingredient.BACON);
    }
}
