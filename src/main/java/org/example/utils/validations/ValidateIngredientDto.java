package org.example.utils.validations;

import org.example.model.Ingredient.IngredientDto;
import org.example.utils.error.ValidationIngredientException;

import java.math.BigDecimal;

public class ValidateIngredientDto {
    public static void validateIngredientDto(IngredientDto ingredientDto) {
        if (ingredientDto.name() == null || ingredientDto.name().isEmpty()) {
            throw new ValidationIngredientException(new IllegalArgumentException("Name cannot be empty"));
        }
        if (ingredientDto.price() == null || ingredientDto.price().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationIngredientException(new IllegalArgumentException("Price cannot be null or negative"));
        }
    }
}
