package com.mycompany.app;

import com.mycompany.app.configuration.EnvPropertiesProvider;
import com.mycompany.app.configuration.PropertiesProvider;
import com.mycompany.app.model.Order;
import com.mycompany.app.utility.PeriodicFileReader;

/**
 * Test of javadoc creation.
 * @since 1.0
 */
public class AppEntry {
    public static void main(final String[] args) {
        PropertiesProvider properties = new EnvPropertiesProvider();

        PeriodicFileReader reader = PeriodicFileReader.builder()
                .clazz((Class) Order.class)
                .path(properties.getTestPath())
                .regex(".*.json")
                .build();
        reader.run();
    }
}
