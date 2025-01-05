package salle.url.edu.models.pizzas.all_pizzas.no_ingredients;

import salle.url.edu.models.pizzas.Pizza;

public class Margherita extends Pizza {
    public Margherita() {
        super("Margherita");
    }

    @Override
    protected void addSpecificIngredients() {
        // Doesn't have any specific ingredients
    }
}
