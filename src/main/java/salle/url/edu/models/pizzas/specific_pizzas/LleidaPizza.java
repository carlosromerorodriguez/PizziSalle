package salle.url.edu.models.pizzas.specific_pizzas;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class LleidaPizza extends Pizza {
    public LleidaPizza() {
        super("Lleida");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.BBQ_SAUCE, 1);
        getIngredients().put(Ingredient.BEEF, 1);
        getIngredients().put(Ingredient.CHICKEN, 1);
        getIngredients().put(Ingredient.MUSHROOMS, 1);
        getIngredients().put(Ingredient.OLIVES, 1);
    }
}
