package salle.url.edu.models.pizzas;

import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import salle.url.edu.enums.Beverage;
import salle.url.edu.enums.Crust;
import salle.url.edu.enums.Ingredient;

import java.util.*;

@ToString
@Data
public abstract class Pizza {
    private String name;
    private Map<Ingredient, Integer> ingredients;
    private Crust crust;
    private Beverage beverage;

    public Pizza(String name) {
        this.name = name;
        this.ingredients = new LinkedHashMap<>();
        this.ingredients.put(Ingredient.TOMATO_SAUCE, 1);
        this.ingredients.put(Ingredient.CHEESE, 1);
        this.crust = Crust.ORIGINAL;
    }

    protected abstract void addSpecificIngredients();

    public void addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient)) {
            ingredients.put(ingredient, ingredients.get(ingredient) + 1);
        } else {
            ingredients.put(ingredient, 1);
        }
    }
}