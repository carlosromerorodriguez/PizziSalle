package salle.url.edu.models.delegations;

import salle.url.edu.models.Order;

import java.util.ArrayList;
import java.util.List;

public class GeneralDelegation extends AbstractDelegation {
    private final List<AbstractDelegation> subDelegations = new ArrayList<>();

    public GeneralDelegation(String name) {
        super(name);
    }

    public void addDelegation(AbstractDelegation delegation) {
        subDelegations.add(delegation);
    }

    @Override
    public void notifyOrder(Order order) {
        System.out.println("General Delegation notified about the new order: " + order.getCustomer().getName());
        for (AbstractDelegation delegation : subDelegations) {
            delegation.notifyOrder(order);
        }
    }
}
