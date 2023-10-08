package org.example.model.Order;

import org.example.model.Enums.Discount;
import org.example.model.Enums.Option;
import org.example.model.Pizza.Pizza;

import java.util.List;

public record OrderDto(int howManySeats, Option option, List<String> pizzas, Discount discount) {
}
