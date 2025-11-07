package com.mycompany.app.configuration;

public class EnvPropertiesProvider extends PropertiesProvider {
    private static final String ENV_VARIABLE_NAME = "MY_APP_RESOURCE_PATH";

    @Override
    public String getTestPath() {
        String testPath = System.getenv(ENV_VARIABLE_NAME);
        return testPath == null ? super.getTestPath() : testPath;
    }
}
