package com.mycompany.app.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class OrderValidator {
    public boolean validate(Order order) {
        return validateName(order) && validateProducts(order.getProducts());
    }

    private boolean validateName(Order order) {
        return notEmpty(order.getName()) && notEmpty(order.getSurname());
    }

    private static boolean notEmpty(final String s) {
        return s != null && !s.isBlank();
    }

    private boolean validateProducts(List<Product> products) {
        boolean result = products.size() > 0 && products.size() < 10;
        result &= products.stream()
                .allMatch(this::validateProduct);
        result &= 2000 >= products.stream()
                .mapToDouble(this::mapQuantityToKilograms)
                .sum();
        return result;
    }

    private boolean validateProduct(Product product) {
        boolean result = Arrays.asList(Product.Units.GRAM, Product.Units.KILOGRAM, Product.Units.TONNE).contains(product.unit());
        result &= product.quantity().compareTo(BigDecimal.ZERO) > 0;
        return result;
    }

    private double mapQuantityToKilograms(Product product) {
        switch (product.unit()) {
            case GRAM:
                return product.quantity().doubleValue() / 1000;
            case KILOGRAM:
                return product.quantity().doubleValue();
            case TONNE:
                return product.quantity().doubleValue() * 1000;
            default:
                return Double.NaN;
        }
    }
}