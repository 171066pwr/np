package com.mycompany.app;

import com.mycompany.app.configuration.AppPropertiesProvider;
import com.mycompany.app.configuration.MavenPropertiesProvider;
import com.mycompany.app.model.Order;
import com.mycompany.app.utility.PeriodicFileReader;

/**
 * Test of javadoc creation.
 * @since 1.0
 */
public class AppEntry {
    public static void main(final String[] args) {
        MavenPropertiesProvider mavenProperties = new MavenPropertiesProvider();
        System.out.println("Application version: " + mavenProperties.getVersion());
        AppPropertiesProvider properties = new AppPropertiesProvider();

        PeriodicFileReader reader = PeriodicFileReader.builder()
                .clazz((Class) Order.class)
                .path(properties.getTestPath())
                .regex(".*.json")
                .build();
        reader.run();
    }
}
