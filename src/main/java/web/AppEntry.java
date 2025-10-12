package web;

import configuration.PropertiesProvider;
import utility.ResourceReader;

import java.io.IOException;

public class AppEntry {
    public static void main(String[] args) {
        System.out.println(PropertiesProvider.TEST_PATH);
        ResourceReader reader = new ResourceReader();

        try {
            String s = reader.resourceToString(PropertiesProvider.TEST_PATH + "order.json");
            System.out.println(s);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        Thread s;
    }
}
