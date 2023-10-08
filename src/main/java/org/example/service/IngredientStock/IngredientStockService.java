package org.example.service.IngredientStock;

import org.example.model.Ingredient.IngredientStock;

import java.util.Set;

public interface IngredientStockService {
    String addIngredientToStock(String ingredientId, int quantity);
    boolean deleteIngredientFromStock(String ingredientId);
    boolean updateIngredientQuantity(String ingredientId, int quantity);
    int getIngredientQuantity(String ingredientId);
    Set<IngredientStock> getAllIngredients();
}
