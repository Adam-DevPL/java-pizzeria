package org.example.model.Order;

import org.example.model.Enums.Discount;
import org.example.model.Enums.Option;
import org.example.model.Pizza.Pizza;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id = UUID.randomUUID().toString();
    private final String tableId;
    private final Option option;
    private final Discount discount;
    private String employeeId;
    private final List<String> pizzas;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public Order(OrderDto orderDto, String tableId, String employeeId) {

        this.tableId = tableId;
        this.option = orderDto.option();
        this.discount = orderDto.discount();
        this.employeeId = employeeId;
        this.pizzas = orderDto.pizzas();
    }

    public String getId() {
        return id;
    }

    public String getTableId() {
        return tableId;
    }

    public Option getOption() {
        return option;
    }

    public Discount getDiscount() {
        return discount;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public List<String> getPizzas() {
        return pizzas;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
