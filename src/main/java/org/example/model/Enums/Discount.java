package org.example.model.Enums;

public enum Discount {
    younger10(0.1),
    student(0.4),
    none(0.0);

    private final double discount;

    Discount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public static Discount getDiscount(String discount) {
        return switch (discount) {
            case "younger10" -> Discount.younger10;
            case "student" -> Discount.student;
            default -> Discount.none;
        };
    }


}
