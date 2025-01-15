package salle.url.edu.models.delegations;

import lombok.Getter;
import salle.url.edu.models.Order;

@Getter
public class SpecificDelegation extends AbstractDelegation {
    public SpecificDelegation(String name) {
        super(name);
    }

    @Override
    protected void notifyOrder(Order order) {
        System.out.println(name + " delegation has been notified about a new order from customer " + order.getCustomer().getName());
    }
}
