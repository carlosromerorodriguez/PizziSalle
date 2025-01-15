package salle.url.edu.models.delegations;

import salle.url.edu.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeneralDelegation extends AbstractDelegation {
    private final List<AbstractDelegation> subDelegations = new ArrayList<>();

    // PATTERN: Composite Pattern - {Structural Pattern}
    //          This class is a composite class that contains a list of sub-delegations
    //          and implements the AbstractDelegation interface to notify the order
    //          to all the sub-delegations when a new order is received
    //          is used to represent the hierarchy of delegations with the "General Delegation"
    //          as root and the specific delegations (Barcelona, Girona, etc.) as leaves.

    public GeneralDelegation(String name) {
        super(name);
    }

    public void addDelegation(AbstractDelegation delegation) {
        subDelegations.add(delegation);
    }

    @Override
    public void notifyOrder(Order order) {
        System.out.println("\nGeneral Delegation has been notified about a new order from customer " + order.getCustomer().getName());
        for (AbstractDelegation delegation : subDelegations) {
            if (Objects.equals(order.getDelegation(), delegation.getName())){
                delegation.notifyOrder(order);
            }
        }
    }
}
