package com.mycompany.app.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.model.Order;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public class PeriodicFileReader implements Runnable {
    private final SourceReader reader = new SourceReader();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path;
    private final String regex;
    @Builder.Default
    private long repetitions = 10;
    @Builder.Default
    private long period = 10000;

    @Override
    public void run() {
        while (repetitions > 0) {
            printExecutionTime();
            readFiles();
            repetitions--;
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                System.out.println("Periodic reader interrupted.");
                return;
            }
        }
    }

    private void readFiles() {
        List<String> testFiles = reader.readFiles(path, regex);
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

    private void printExecutionTime() {
        System.out.println("###########################");
        System.out.println(Instant.now().toString());
        System.out.println("###########################");
    }
}
