package com.mycompany.app.configuration;

public class MavenPropertiesProvider extends PropertiesProvider {
    private static final String PATH = "maven.properties";
    private static final String VERSION = "version";

    public MavenPropertiesProvider() {
        super(PATH);
    }

    public String getVersion() {
        return getProperty(VERSION);
    }
}
