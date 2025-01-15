package salle.url.edu.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationUtils {
    public static boolean checkInt(int min, int max, String line) {
        int option;
        try {
            option = Integer.parseInt(line);

            return option >= min && option <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkName(String name) {
        // Name can't be empty or contain numbers or special characters
        return name.length() > 1 && name.length() < 50 && name.matches("[a-zA-Z]+");
    }

    public static boolean checkPhone(String line) {
        // Phone can't be empty, must be 9 digits long and can't contain letters
        return line.length() == 9 && line.matches("\\d+");
    }

    public static boolean checkAddress(String line) {
        // Address can't be empty
        return line.length() > 3 && line.length() < 50;
    }
}
