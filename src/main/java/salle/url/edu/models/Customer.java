package salle.url.edu.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Customer {
    private String name;
    private int age;
    private String phone;
    private String address;
    private boolean isFirstOrder;
}

