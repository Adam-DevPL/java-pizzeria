package org.example.service.PizzaService;

import org.example.model.Pizza.Pizza;
import org.example.model.Pizza.PizzaDto;

import java.util.Set;

public interface PizzaService {
    String createPizza(PizzaDto pizzaDto);

    Pizza getPizza(String pizzaId);

    boolean deletePizza(String pizzaId);

    Set<Pizza> getAllPizzas();
}
