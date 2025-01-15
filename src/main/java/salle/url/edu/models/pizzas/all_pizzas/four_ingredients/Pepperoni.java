package salle.url.edu.models.pizzas.all_pizzas.four_ingredients;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class Pepperoni extends Pizza {
    public Pepperoni() {
        super("Pepperoni");
        addSpecificIngredients();
    }

    @Override
    protected void addSpecificIngredients() {
        addIngredient(Ingredient.PEPPERONI);
        addIngredient(Ingredient.HAM);
        addIngredient(Ingredient.BEEF);
        addIngredient(Ingredient.BACON);
    }
}
