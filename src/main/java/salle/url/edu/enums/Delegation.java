package salle.url.edu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Delegation {
    BARCELONA("Barcelona"),
    TARRAGONA("Tarragona"),
    LLEIDA("Lleida"),
    GIRONA("Girona");

    private final String name;
}
