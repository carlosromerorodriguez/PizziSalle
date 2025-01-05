package salle.url.edu.models.pizzas.decorators;

import salle.url.edu.enums.Crust;
import salle.url.edu.models.pizzas.Pizza;

public class CrustDecorator extends PizzaDecorator {

    public CrustDecorator(Pizza pizza, Crust crust) {
        super(pizza);
        setCrust(crust);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    protected void addSpecificIngredients() {
        // Nothing to do here
    }
}