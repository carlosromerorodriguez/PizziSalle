package salle.url.edu.models.pizzas.specific_pizzas;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class BarcelonaPizza extends Pizza {
    public BarcelonaPizza() {
        super("Barcelona");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.ONION, 1);
        getIngredients().put(Ingredient.BEEF, 1);
        getIngredients().put(Ingredient.BRIE, 1);
        getIngredients().put(Ingredient.HAM, 1);
        getIngredients().put(Ingredient.OLIVES, 1);
    }
}
