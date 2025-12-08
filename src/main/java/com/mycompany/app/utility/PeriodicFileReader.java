package com.mycompany.app.utility;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@SuperBuilder
public class PeriodicFileReader<T> extends ObjectFileReader<T> implements Runnable {
    private final String path;
    private final String regex;
    @Builder.Default
    private long repetitions = 100;
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
        List<T> objects = readFromFiles(path, regex);
        for (T t: objects) {
            try {
                System.out.println(objectMapper.writeValueAsString(t));
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
