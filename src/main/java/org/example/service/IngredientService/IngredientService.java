package org.example.service.IngredientService;

import org.example.model.Ingredient.Ingredient;
import org.example.model.Ingredient.IngredientDto;

import java.util.Set;

public interface IngredientService {
    String addIngredient(IngredientDto ingredientDto);
    boolean deleteIngredient(String ingredientId);
    Ingredient getIngredient(String ingredientId);
    Set<Ingredient> getAllIngredients();
}
