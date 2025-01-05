package salle.url.edu.models.pizzas.specific_pizzas;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class TarragonaPizza extends Pizza {
    public TarragonaPizza() {
        super("Tarragona");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.TUNA, 1);
        getIngredients().put(Ingredient.PRAWNS, 1);
        getIngredients().put(Ingredient.ONION, 1);
        getIngredients().put(Ingredient.HAM, 1);
        getIngredients().put(Ingredient.OLIVES, 1);
    }
}
