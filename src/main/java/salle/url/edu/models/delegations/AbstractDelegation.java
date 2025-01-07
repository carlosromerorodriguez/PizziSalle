package salle.url.edu.models.delegations;

import lombok.AllArgsConstructor;
import lombok.Data;
import salle.url.edu.models.Order;

// PATTERN: Composite Pattern
@AllArgsConstructor
@Data
public abstract class AbstractDelegation {
    protected String name;

    //PATTERN:  Observer Pattern - {Behavioral Pattern}
    //          This method is called by the concrete observer to notify the order
    //          to the concrete subject
    protected abstract void notifyOrder(Order order);
}
