package salle.url.edu.models.pizzas.decorators;

import salle.url.edu.models.pizzas.Pizza;
import salle.url.edu.enums.Crust;
import salle.url.edu.enums.Ingredient;

import java.util.Map;

public abstract class PizzaDecorator extends Pizza {
    protected final Pizza pizza;


    // PATTERN: Decorator Pattern - {Structural Pattern}
    //          This class is the base class for all the decorators
    //          that will be used to decorate the pizzas

    public PizzaDecorator(Pizza pizza) {
        super(pizza.getName());
        this.pizza = pizza;
    }

    @Override
    public String getName() {
        return pizza.getName();
    }

    @Override
    public Map<Ingredient, Integer> getIngredients() {
        return pizza.getIngredients();
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        pizza.addIngredient(ingredient);
    }

    @Override
    public Crust getCrust() {
        return pizza.getCrust();
    }

    @Override
    public void setCrust(Crust crust) {
        pizza.setCrust(crust);
    }
}
