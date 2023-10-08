package org.example.service.IngredientService;

import org.example.model.Ingredient.Ingredient;
import org.example.model.Ingredient.IngredientDto;
import org.example.utils.error.IngredientException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public class IngredientServiceImpl implements IngredientService {

    private final Set<Ingredient> ingredients = new HashSet<>();

    @Override
    public String addIngredient(final IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient(ingredientDto);

        boolean isIngredientAdded = ingredients.add(ingredient);

        if (!isIngredientAdded) {
            throw new IngredientException(new IllegalArgumentException("Ingredient already exists"));
        }

        return ingredient.getId();
    }

    @Override
    public boolean deleteIngredient(String ingredientId) {

        Optional<Ingredient> ingredient = findIngredientById(ingredientId);

        if (ingredient.isEmpty()) {
            throw new IngredientException(new NoSuchElementException("Ingredient not found"));
        }

        return ingredients.remove(ingredient.get());
    }


    @Override
    public Ingredient getIngredient(String ingredientId) {
        Optional<Ingredient> ingredient = findIngredientById(ingredientId);

        if (ingredient.isEmpty()) {
            throw new IngredientException(new NoSuchElementException("Ingredient not found"));
        }

        return ingredient.get();
    }

    @Override
    public Set<Ingredient> getAllIngredients() {
        return ingredients;
    }
    private Optional<Ingredient> findIngredientById(String ingredientId) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst();
    }
}
