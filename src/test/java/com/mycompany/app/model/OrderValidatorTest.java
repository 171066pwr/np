package com.mycompany.app.model;

import com.github.nylle.javafixture.Configuration;
import com.github.nylle.javafixture.Fixture;
import com.mycompany.app.utility.ObjectFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

class OrderValidatorTest {
    private static final String resourcePath = "src/test/resources/orders/";
    private static final ObjectFileReader<Order> reader = new ObjectFileReader<>(Order.class);
    private final OrderValidator validator = new OrderValidator();

    @Test
    void testValidatePositive() {
        List<Order> orders = reader.readFromFiles(resourcePath, "positive.*.json");
        for(Order order: orders) {
            Assertions.assertTrue(validator.validate(order));
        }
    }

    @Test
    void testValidateNegative() {
        List<Order> orders = reader.readFromFiles(resourcePath, "negative.*.json");
        for(Order order: orders) {
            Assertions.assertFalse(validator.validate(order));
        }
    }

    @Test
    void testWithFixture() {
        var fixture = new Fixture();
        Order order = fixture.create(Order.class);
        Assertions.assertFalse(validator.validate(order));
    }

    @Test
    void testShowingWhyFixtureIsBadHere() {
        var fixture = new Fixture();
        Order order = fixture.build(Order.class)
                .with("products", fixture.build(Product.class).createMany(9).collect(Collectors.toList()))
                .create();
        //Fixture cannot create instance of class without public constructor. Package-private record breaks it.
        //Records are final, so you cannot even extend it to make it public.
        //Also, please notice how difficult would be creating test data for such complex conditions using this method.
        //Better to cover edge cases manually.
        Assertions.assertTrue(validator.validate(order));
    }

    @Test
    void testFixtureWithConfigStillBad() {
        var config = Configuration.configure()
                .collectionSizeRange(1, 9)
                .usePositiveNumbersOnly(true);
        var fixture = new Fixture(config);
        Order order = fixture.create(Order.class);
        //This way it's still impossible
        Assertions.assertTrue(validator.validate(order));
    }
}