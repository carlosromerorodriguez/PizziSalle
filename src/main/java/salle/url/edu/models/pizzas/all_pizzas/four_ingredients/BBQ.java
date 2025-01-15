package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class BBQ extends Pizza {
    public BBQ() {
        super("BBQ");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.BBQ_SAUCE);
        addIngredient(Ingredient.BEEF);
        addIngredient(Ingredient.BACON);
        addIngredient(Ingredient.CHICKEN);
    }
}
