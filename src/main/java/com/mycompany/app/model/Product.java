package com.mycompany.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

record Product(
        String productId,
        BigDecimal quantity,
        Units unit,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        BigDecimal price
) {

    Product {
        price = quantity.multiply(new BigDecimal((productId.hashCode()%100 * productId.hashCode()%100)%100));
    }

    public enum Units {
        UNIT("u"),
        GRAM("g"),
        KILOGRAM("kg"),
        TONNE("t");
        private final String name;

        Units(String name) {
            this.name = name;
        }
    }
}
