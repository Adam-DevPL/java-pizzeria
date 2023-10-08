package org.example.model.Pizza;

import org.example.model.Ingredient.Ingredient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Pizza {
    private final String id = UUID.randomUUID().toString();

    private final String name;

    private final String description;

    private final List<String> ingredients;

    public Pizza(PizzaDto pizzaDto) {
        this.name = pizzaDto.name();
        this.description = pizzaDto.description();
        this.ingredients = pizzaDto.ingredients();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(name, pizza.name) && Objects.equals(description, pizza.description) && Objects.equals(ingredients, pizza.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, ingredients);
    }
}
