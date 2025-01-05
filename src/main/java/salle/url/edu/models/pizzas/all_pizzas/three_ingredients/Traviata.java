package salle.url.edu.models.pizzas.all_pizzas.three_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Traviata extends Pizza {
    public Traviata() {
        super("Traviata");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.BACON, 1);
        getIngredients().put(Ingredient.SAUSAGE, 1);
        getIngredients().put(Ingredient.ONION, 1);
    }
}
