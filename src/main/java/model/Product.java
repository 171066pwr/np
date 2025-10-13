package model;

import java.math.BigDecimal;

record Product(
        String productId,
        BigDecimal quantity,
        Units unit
){
    public enum Units {
        UNIT("u"),
        KILOGRAM("kg");
        private final String unit;

        Units(String unit) {
            this.unit = unit;
        }
    }
}
