package salle.url.edu.models.pizzas.all_pizzas.six_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class SixCheeses extends Pizza {
    public SixCheeses() {
        super("Six Cheeses");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.MOZZARELLA, 1);
        getIngredients().put(Ingredient.GOAT_CHEESE, 1);
        getIngredients().put(Ingredient.BRIE, 1);
        getIngredients().put(Ingredient.ROQUEFORT, 1);
        getIngredients().put(Ingredient.CHEDDAR, 1);
        getIngredients().put(Ingredient.EMMENTAL, 1);
    }
}
