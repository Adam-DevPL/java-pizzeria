package org.example.utils.validations;

import org.example.utils.error.CustomError;

public class ValidateEmployee {
    public static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new CustomError("Name cannot be empty");
        }
    }

    public static void validateRole(String role) {
        if (role == null || role.isEmpty()) {
            throw new CustomError("Role cannot be empty");
        }
    }
}
