package salle.url.edu.models.pizzas.all_pizzas.three_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class BaconCrispy extends Pizza {
    public BaconCrispy() {
        super("Bacon Crispy");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.HAM, 1);
        getIngredients().put(Ingredient.CHICKEN, 1);
        getIngredients().put(Ingredient.BACON, 1);
    }
}
