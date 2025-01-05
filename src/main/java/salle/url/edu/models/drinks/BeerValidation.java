package salle.url.edu.models.drinks;

public class BeerValidation implements BeverageValidation{
    @Override
    public boolean canConsume(int age) {
        return age >= 18;
    }
}
