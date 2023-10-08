package org.example.service.PizzeriaService;

import org.example.model.Employee.EmployeeDto;
import org.example.model.Ingredient.IngredientDto;
import org.example.model.Order.OrderDto;
import org.example.model.Pizza.PizzaDto;
import org.example.model.Table.TableDto;

import java.math.BigDecimal;

public interface PizzeriaService {
    String addEmployee(EmployeeDto employeeDto);
    void removeEmployee(String id);
    String addIngredient(IngredientDto ingredientDto);
    void removeIngredient(String id);
    String addPizza(PizzaDto pizzaDto);
    void removePizza(String id);
    String addTable(TableDto tableDto);
    void removeTable(String id);
    BigDecimal addOrder(OrderDto orderDto);
    void removeOrder(String id);
}
