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
    private Configuration configuration;
    private final String filename;

    PropertiesProvider(String filename) {
        this.filename = filename;
        this.configuration = loadProperties();
    }

    public void reloadAppProperties() {
        this.configuration = loadProperties();
    }

    public String getProperty(String key) {
        return configuration.getString(key);
    }

    public String getPropertyOrEnv(String key, String env) {
        String value = System.getenv(env);
        return value == null || value.isBlank() ? getProperty(key) : value;
    }

    private Configuration loadProperties() {
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(new Parameters().properties().setFileName(filename));
        try {
            return builder.getConfiguration();
        } catch (ConfigurationException cex) {
            throw new ConfigurationLoadingException(filename);
        }
    }

    static class ConfigurationLoadingException extends RuntimeException {
        ConfigurationLoadingException(String filename) {
            super("Could not load app settings from file " + filename);
        }
    }
}
