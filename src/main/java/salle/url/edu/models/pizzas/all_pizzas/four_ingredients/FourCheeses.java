package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class FourCheeses extends Pizza {
    public FourCheeses() {
        super("Four Cheeses");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.MOZZARELLA);
        addIngredient(Ingredient.EMMENTAL);
        addIngredient(Ingredient.ROQUEFORT);
        addIngredient(Ingredient.CHEDDAR);
    }
}
