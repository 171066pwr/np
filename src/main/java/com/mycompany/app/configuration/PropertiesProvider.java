package com.mycompany.app.configuration;

import lombok.Getter;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class PropertiesProvider {
    @Getter
    private Configuration configuration = loadAppProperties();
    private static final String TEST_PATH = "orders.path";
    private static final String FILENAME = "appsettings.properties";

    public PropertiesProvider() {
        this.configuration = loadAppProperties();
    }

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

    public void reloadAppProperties() {
        this.configuration = loadAppProperties();
    }

    public String getTestPath() {
        return configuration.getString(TEST_PATH);
    }

    static class ConfigurationLoadingException extends RuntimeException {
        ConfigurationLoadingException() {
            super("Could not load app settings from file " + FILENAME);
        }
    }
}
