package com.mycompany.app.configuration;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public final class PropertiesProvider {
    public static final Configuration CONFIGURATION = loadAppProperties();
    public static final String TEST_PATH = CONFIGURATION.getString("test.orders.path");
    private static final String FILENAME = "appsettings.properties";

    private static Configuration loadAppProperties() {
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(new Parameters().properties().setFileName(FILENAME));
        try {
            return builder.getConfiguration();
        } catch (ConfigurationException cex) {
            throw new ConfigurationLoadingException();
        }
    }

    static class ConfigurationLoadingException extends RuntimeException {
        ConfigurationLoadingException() {
            super("Could not load app settings from file " + FILENAME);
        }
    }
}
