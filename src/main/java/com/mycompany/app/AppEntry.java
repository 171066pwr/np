package com.mycompany.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.configuration.PropertiesProvider;
import com.mycompany.app.model.Order;
import com.mycompany.app.utility.SourceReader;

import java.util.List;

/**
 * @since 1.0
 */
public class AppEntry {
    public static void main(final String[] args) {
        SourceReader reader = new SourceReader();
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> testFiles = reader.readFiles(PropertiesProvider.TEST_PATH, ".*.json");
        List<Order> orders = testFiles.stream()
            .map(s -> {
                try {
                    return objectMapper.readValue(s, Order.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            })
            .toList();
        for (Order order: orders) {
            try {
                System.out.println(objectMapper.writeValueAsString(order));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
