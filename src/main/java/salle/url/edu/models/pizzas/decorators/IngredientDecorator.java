package salle.url.edu.models.pizzas.decorators;

import salle.url.edu.enums.Ingredient;
import salle.url.edu.models.pizzas.Pizza;

public class IngredientDecorator extends PizzaDecorator {
    private final Ingredient ingredient;

    public IngredientDecorator(Pizza pizza, Ingredient ingredient) {
        super(pizza);
        this.ingredient = ingredient;
        addIngredient(ingredient);
    }

    @Override
    public String getName() {
        return super.getName() + " + Extra " + ingredient.getName();
    }

    @Override
    public void addSpecificIngredients() {
        // Nothing to do here
    }
}