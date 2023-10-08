package org.example.service.PizzeriaService;

import org.example.model.Employee.Employee;
import org.example.model.Employee.EmployeeDto;
import org.example.model.Enums.Option;
import org.example.model.Enums.Role;
import org.example.model.Enums.Status;
import org.example.model.Ingredient.Ingredient;
import org.example.model.Ingredient.IngredientDto;
import org.example.model.Order.Order;
import org.example.model.Order.OrderDto;
import org.example.model.Pizza.Pizza;
import org.example.model.Pizza.PizzaDto;
import org.example.model.Table.Table;
import org.example.model.Table.TableDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PizzeriaServiceImpl implements PizzeriaService {

    private final List<Employee> employees = new ArrayList<>();
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<Pizza> pizzas = new ArrayList<>();
    private final List<Table> tables = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<Order> queueOrders = new ArrayList<>();


    @Override
    public String addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto);
        employees.add(employee);
        return employee.getId();
    }

    @Override
    public void removeEmployee(String id) {
        employees.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Employee not found"));
    }

    @Override
    public String addIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient(ingredientDto);
        ingredients.add(ingredient);
        return ingredient.getId();
    }

    @Override
    public void removeIngredient(String id) {
        ingredients.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));
    }

    @Override
    public String addTable(TableDto tableDto) {
        Table table = new Table(tableDto);
        tables.add(table);
        return table.getId();
    }

    @Override
    public void removeTable(String id) {
        tables.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Table not found"));
    }

    @Override
    public String addPizza(PizzaDto pizzaDto) {
        Pizza pizza = new Pizza(pizzaDto);
        pizzas.add(pizza);
        return pizza.getId();
    }

    @Override
    public void removePizza(String id) {
        pizzas.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Pizza not found"));
    }

    @Override
    public BigDecimal addOrder(OrderDto orderDto) {
        if (orderDto.getOption().equals(Option.TAKEAWAY)) {
            String cook = getFreeCook();
            if (Objects.isNull(cook)) {
                Order order = new Order(orderDto, null, null);
                queueOrders.add(order);
                return order.getTotalPrice();
            }
            Employee cookEmployee = employees.stream().filter(e -> e.getId().equals(cook)).findFirst().orElse(null);
            cookEmployee.setStatus(Status.BUSY);
            Order order = new Order(orderDto, null, cook);
            orders.add(order);
            return order.getTotalPrice();
        }
        Table freeTable = tables.stream().filter(e -> e.getStatus().equals(Status.FREE)).findFirst().orElseThrow(() -> new IllegalArgumentException("No free table"));
        String cook = getFreeCook();
        if (Objects.isNull(cook)) {
            Order order = new Order(orderDto, freeTable.getId(), null);
            queueOrders.add(order);
            return order.getTotalPrice();
        }
        Employee cookEmployee = employees.stream().filter(e -> e.getId().equals(cook)).findFirst().orElse(null);
        cookEmployee.setStatus(Status.BUSY);
        freeTable.setStatus(Status.BUSY);
        Order order = new Order(orderDto, freeTable.getId(), cook);
        orders.add(order);
        return order.getTotalPrice();
    }

    @Override
    public void removeOrder(String id) {
        Order order = orders.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Order not found"));
        if (Objects.nonNull(order.getEmployeeId())) {
            Employee cookEmployee = employees.stream().filter(e -> e.getId().equals(order.getEmployeeId())).findFirst().orElse(null);
            cookEmployee.setStatus(Status.FREE);
        }

        if (Objects.nonNull(order.getTableId())) {
            Table table = tables.stream().filter(e -> e.getId().equals(order.getTableId())).findFirst().orElse(null);
            table.setStatus(Status.FREE);
        }

        orders.remove(order);

        Order queueOrder = queueOrders.stream().findFirst().orElse(null);

        if (Objects.nonNull(queueOrder)) {
            String cookEmployee = getFreeCook();
            if (Objects.isNull(cookEmployee)) {
                return;
            }
            Employee cook = employees.stream().filter(e -> e.getId().equals(cookEmployee)).findFirst().orElse(null);
            cook.setStatus(Status.BUSY);
            queueOrder.setEmployeeId(cookEmployee);
            queueOrders.remove(queueOrder);
            orders.add(queueOrder);
        }
    }

    private String getFreeCook() {
        Employee cook = employees.stream().filter(e -> e.getRole().equals(Role.COOK) && e.getStatus().equals(Status.FREE)).findFirst().orElse(null);
        return cook != null ? cook.getId() : null;
    }
}
