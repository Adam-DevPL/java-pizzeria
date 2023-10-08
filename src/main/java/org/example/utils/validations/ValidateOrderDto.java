package org.example.utils.validations;

import org.example.model.Order.OrderDto;

public class ValidateOrderDto {
    public static void validateOrderDto(OrderDto orderDto) {
        if (orderDto.howManySeats() <= 0) {
            throw new IllegalArgumentException("Number of seats cannot be negative or zero");
        }
        if (orderDto.option() == null) {
            throw new IllegalArgumentException("Option cannot be null");
        }
        if (orderDto.pizzas() == null || orderDto.pizzas().isEmpty()) {
            throw new IllegalArgumentException("Pizzas cannot be null or empty");
        }
        if (orderDto.discount() == null) {
            throw new IllegalArgumentException("Discount cannot be null");
        }
    }
}
