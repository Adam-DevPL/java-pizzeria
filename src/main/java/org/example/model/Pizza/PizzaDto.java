package org.example.model.Pizza;

import java.util.List;

public record PizzaDto(String name, String description, List<String> ingredients) {
}
