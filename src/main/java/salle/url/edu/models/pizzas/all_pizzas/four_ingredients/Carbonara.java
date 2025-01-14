package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Carbonara extends Pizza {
    public Carbonara(String name) {
        super("Carbonara");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.CARBONARA_SAUCE);
        addIngredient(Ingredient.BACON);
        addIngredient(Ingredient.ONION);
        addIngredient(Ingredient.MUSHROOMS);
    }
}
