package com.mycompany.app.configuration;

public class AppPropertiesProvider extends PropertiesProvider {
    private static final String TEST_PATH = "orders.path";
    private static final String FILENAME = "appsettings.properties";
    private static final String ENV_VARIABLE_NAME = "MY_APP_RESOURCE_PATH";

    public AppPropertiesProvider() {
        super(FILENAME);
    }

    public String getTestPath() {
        return getProperty(TEST_PATH);
    }

    public String getTestPathFromEnv() {
        return getPropertyOrEnv(TEST_PATH, ENV_VARIABLE_NAME);
    }
}
