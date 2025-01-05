package salle.url.edu.models.pizzas.all_pizzas.three_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class American extends Pizza {
    public American() {
        super("American");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.FRANKFURT, 1);
        getIngredients().put(Ingredient.BACON, 1);
        getIngredients().put(Ingredient.EGG, 1);
    }
}
