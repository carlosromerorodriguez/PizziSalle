package salle.url.edu.models.pizzas.all_pizzas.five_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Tarragona extends Pizza {
    public Tarragona(String name) {
        super("Tarragona");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.TUNA, 1);
        getIngredients().put(Ingredient.PRAWNS, 1);
        getIngredients().put(Ingredient.ONION, 1);
        getIngredients().put(Ingredient.OLIVES, 1);
        getIngredients().put(Ingredient.HAM, 1);
    }
}
