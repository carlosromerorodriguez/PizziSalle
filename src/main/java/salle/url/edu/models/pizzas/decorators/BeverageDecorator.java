package salle.url.edu.models.pizzas.decorators;

import salle.url.edu.enums.Beverage;
import salle.url.edu.models.pizzas.Pizza;

public class BeverageDecorator extends PizzaDecorator {
    private final Beverage beverage;

    public BeverageDecorator(Pizza pizza, Beverage beverage) {
        super(pizza);
        this.beverage = beverage;
        setBeverage(beverage);
    }

    @Override
    public String getName() {
        return super.getName() + " (" + beverage.getName() + ")";
    }

    @Override
    public void addSpecificIngredients() {
        // Nothing to do here
    }
}
