package org.example.service.IngredientStock;

import org.example.model.Ingredient.IngredientStock;
import org.example.utils.error.IngredientStockException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class IngredientStockServiceImpl implements IngredientStockService {

    private final Set<IngredientStock> ingredientStocks = new HashSet<>();

    @Override
    public String addIngredientToStock(String ingredientId, int quantity) {
        IngredientStock ingredientStock = new IngredientStock(ingredientId, quantity);

        boolean isIngredientAdded = ingredientStocks.add(ingredientStock);

        if (!isIngredientAdded) {
            throw new IngredientStockException(new IllegalArgumentException("Ingredient already exists"));
        }

        return ingredientStock.getId();
    }

    @Override
    public boolean deleteIngredientFromStock(String ingredientId) {
        Optional<IngredientStock> ingredientStock = findIngredientStockById(ingredientId);

        if (ingredientStock.isEmpty()) {
            throw new IngredientStockException(new IllegalArgumentException("Ingredient not found"));
        }

        return ingredientStocks.remove(ingredientStock.get());
    }

    @Override
    public boolean updateIngredientQuantity(String ingredientId, int quantity) {

        if (quantity < 0) {
            throw new IngredientStockException(new IllegalArgumentException("Quantity cannot be negative"));
        }

        Optional<IngredientStock> ingredientStock = findIngredientStockById(ingredientId);

        if (ingredientStock.isEmpty()) {
            throw new IngredientStockException(new IllegalArgumentException("Ingredient not found"));
        }

        ingredientStock.get().setQuantity(quantity);

        return true;
    }

    @Override
    public int getIngredientQuantity(String ingredientId) {
        Optional<IngredientStock> ingredientStock = findIngredientStockById(ingredientId);

        if (ingredientStock.isEmpty()) {
            throw new IngredientStockException(new IllegalArgumentException("Ingredient not found"));
        }

        return ingredientStock.get().getQuantity();
    }

    @Override
    public Set<IngredientStock> getAllIngredients() {
        return ingredientStocks;
    }


    private Optional<IngredientStock> findIngredientStockById(String ingredientId) {
        return ingredientStocks.stream()
                .filter(ingredientStock -> ingredientStock.getId().equals(ingredientId))
                .findFirst();
    }
}
