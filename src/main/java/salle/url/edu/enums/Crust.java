package salle.url.edu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public enum Crust {
    ORIGINAL("Original"),
    THIN("Thin"),
    SICILIAN("Sicilian");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
