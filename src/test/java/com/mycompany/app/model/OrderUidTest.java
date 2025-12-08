package com.mycompany.app.model;

import com.mycompany.app.utility.ObjectFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderUidTest {
    private static final String resourcePath = "src/test/resources/orders/";
    private static final ObjectFileReader<Order> reader = new ObjectFileReader<>(Order.class);

    @Test
    void uidGenerationTest() {
        String path = resourcePath + "positive_1.json";
        Order order1 = reader.readFromFile(path);
        Order order2 = reader.readFromFile(path);
        Assertions.assertNotEquals(order1.getOrderId(), order2.getOrderId());
    }
}
