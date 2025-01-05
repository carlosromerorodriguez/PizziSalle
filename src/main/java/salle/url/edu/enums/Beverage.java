package salle.url.edu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import salle.url.edu.models.drinks.BeerValidation;
import salle.url.edu.models.drinks.BeverageValidation;
import salle.url.edu.models.drinks.DefaultValidation;

@AllArgsConstructor
@Getter
public enum Beverage {
    WATER("Water", new DefaultValidation()),
    SODA("Soda", new DefaultValidation()),
    BEER("Beer", new BeerValidation());

    private final String name;
    private final BeverageValidation validation;

    public boolean canConsume(int age) {
        return validation.canConsume(age);
    }

    @Override
    public String toString() {
        return name;
    }
}
