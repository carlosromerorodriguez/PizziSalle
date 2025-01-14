package salle.url.edu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public enum Ingredient {
    TOMATO_SAUCE("Tomato sauce"),
    BBQ_SAUCE("BBQ sauce"),
    CHEESE("Cheese"),
    HAM("Ham"),
    PINEAPPLE("Pineapple"),
    CHICKEN("Chicken"),
    BACON("Bacon"),
    FRANKFURT("Frankfurt"),
    EGG("Egg"),
    SAUSAGE("Sausage"),
    ONION("Onion"),
    BEEF("Beef"),
    BRIE("Brie"),
    OLIVES("Olives"),
    TUNA("Tuna"),
    PRAWNS("Prawns"),
    MUSHROOMS("Mushrooms"),
    ROQUEFORT("Roquefort"),
    MINIBURGERS("Miniburgers"),
    ANCHOVIES("Anchovies"),
    ARTICHOKE("Artichoke"),
    BELL_PEPPER("Bell pepper"),
    GOAT_CHEESE("Goat cheese"),
    SOBRASSADA("Sobrassada"),
    CHEDDAR("Cheddar"),
    EMMENTAL("Emmental"),
    HONEY("Honey"),
    PEPPERONI("Pepperoni"),
    TOMATO_SLICES("Tomato slices"),
    MOZZARELLA("Mozzarella"),
    CARBONARA_SAUCE("Carbonara sauce");

    private final String name;

    public static Ingredient getIngredient(String ingredientInput) {
        for (Ingredient ingredient : Ingredient.values()) {
            if (ingredient.getName().equalsIgnoreCase(ingredientInput)) {
                return ingredient;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
