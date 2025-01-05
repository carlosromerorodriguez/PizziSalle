package salle.url.edu.models.drinks;

public class DefaultValidation implements BeverageValidation {
    @Override
    public boolean canConsume(int age) {
        return true;
    }
}
