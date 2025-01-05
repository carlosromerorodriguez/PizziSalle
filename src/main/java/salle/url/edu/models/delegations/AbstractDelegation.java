package salle.url.edu.models.delegations;

import lombok.AllArgsConstructor;
import lombok.Data;
import salle.url.edu.models.Order;

// PATTERN: Composite Pattern
@AllArgsConstructor
@Data
public abstract class AbstractDelegation {
    protected String name;

    protected abstract void notifyOrder(Order order);
}
