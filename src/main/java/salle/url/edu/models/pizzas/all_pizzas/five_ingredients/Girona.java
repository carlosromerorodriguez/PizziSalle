package salle.url.edu.models.pizzas.all_pizzas.five_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Girona extends Pizza {
    public Girona(String name) {
        super("Girona");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.BEEF, 1);
        getIngredients().put(Ingredient.HAM, 1);
        getIngredients().put(Ingredient.MUSHROOMS, 1);
        getIngredients().put(Ingredient.CHICKEN, 1);
        getIngredients().put(Ingredient.OLIVES, 1);
    }
}
