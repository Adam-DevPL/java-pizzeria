package org.example.utils.validations;

import org.example.model.Pizza.PizzaDto;
import org.example.utils.error.ValidationPizzaException;

public class ValidatePizzaDto {
    public static void validatePizzaDto(PizzaDto pizzaDto) {
        if (pizzaDto.name().isEmpty()) {
            throw new ValidationPizzaException(new IllegalArgumentException("Pizza name is empty"));
        }
        if (pizzaDto.description().isEmpty()) {
            throw new ValidationPizzaException(new IllegalArgumentException("Pizza description is empty"));
        }
        if (pizzaDto.ingredients().isEmpty()) {
            throw new ValidationPizzaException(new IllegalArgumentException("Pizza ingredients is empty"));
        }
    }
}
