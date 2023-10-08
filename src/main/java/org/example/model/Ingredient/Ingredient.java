package org.example.model.Ingredient;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Ingredient {
    private final String id = UUID.randomUUID().toString();
    private final String name;
    private final BigDecimal price;

    public Ingredient(IngredientDto ingredientDto) {
        this.name = ingredientDto.name();
        this.price = ingredientDto.price();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
