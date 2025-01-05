package salle.url.edu.models.pizzas.all_pizzas.two_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Hawaiian extends Pizza {
    public Hawaiian() {
        super("Hawaiian");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.HAM, 1);
        getIngredients().put(Ingredient.PINEAPPLE, 1);
    }
}
