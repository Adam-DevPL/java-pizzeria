package org.example.model.Ingredient;

import java.util.Objects;
import java.util.UUID;

public class IngredientStock {
    private final String id = UUID.randomUUID().toString();
    private final String ingredientId;
    private int quantity;

    public IngredientStock(String ingredientId, int quantity) {
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getIngredient() {
        return ingredientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientStock that = (IngredientStock) o;
        return quantity == that.quantity && Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, quantity);
    }
}
