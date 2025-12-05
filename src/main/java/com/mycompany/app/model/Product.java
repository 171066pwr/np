package com.mycompany.app.model;

import java.math.BigDecimal;

record Product(
        String productId,
        BigDecimal quantity,
        Units unit
) {
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
