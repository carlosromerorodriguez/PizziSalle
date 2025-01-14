package salle.url.edu.models.pizzas.all_pizzas.six_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class CarbonaraDeluxe extends Pizza {
    public CarbonaraDeluxe(String name) {
        super("Carbonara Deluxe");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        getIngredients().put(Ingredient.CARBONARA_SAUCE, 1);
        getIngredients().put(Ingredient.BACON, 1);
        getIngredients().put(Ingredient.MUSHROOMS, 1);
        getIngredients().put(Ingredient.ONION, 1);
        getIngredients().put(Ingredient.GOAT_CHEESE, 1);
        getIngredients().put(Ingredient.HONEY, 1);
    }
}
