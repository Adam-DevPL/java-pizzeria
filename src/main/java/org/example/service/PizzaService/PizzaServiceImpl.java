package org.example.service.PizzaService;

import org.example.model.Pizza.Pizza;
import org.example.model.Pizza.PizzaDto;
import org.example.utils.error.PizzaException;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public class PizzaServiceImpl implements PizzaService {
    
    private final Set<Pizza> pizzas = new HashSet<>();

    @Override
    public String createPizza(PizzaDto pizzaDto) {
        Pizza pizza = new Pizza(pizzaDto);
        
        boolean isPizzaAdded = pizzas.add(pizza);
        
        if (!isPizzaAdded) {
            throw new PizzaException(new IllegalArgumentException("Pizza already exists"));
        }
        
        
        return pizza.getId();
    }

    @Override
    public Pizza getPizza(String pizzaId) {
        Optional<Pizza> pizza = getFirst(pizzaId);

        if (pizza.isEmpty()) {
            throw new PizzaException(new NoSuchElementException("Pizza not found"));
        }

        return pizza.get();
    }

    @Override
    public boolean deletePizza(String pizzaId) {
        Optional<Pizza> pizza = getFirst(pizzaId);

        if (pizza.isEmpty()) {
            throw new PizzaException(new NoSuchElementException("Pizza not found"));
        }

        return pizzas.remove(pizza.get());
    }

    @Override
    public Set<Pizza> getAllPizzas() {
        return pizzas;
    }

    private Optional<Pizza> getFirst(String pizzaId) {
        return pizzas.stream().filter(p -> p.getId().equals(pizzaId)).findFirst();
    }
}
