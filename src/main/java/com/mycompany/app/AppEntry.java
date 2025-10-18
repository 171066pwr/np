package com.mycompany.app;

import com.mycompany.app.configuration.PropertiesProvider;
import com.mycompany.app.utility.PeriodicFileReader;

/**
 * Test of javadoc creation.
 * @since 1.0
 */
public class AppEntry {
    public static void main(final String[] args) {
        PeriodicFileReader reader = PeriodicFileReader.builder()
                .path(PropertiesProvider.TEST_PATH)
                .regex(".*.json")
                .build();
        reader.run();
    }
}
