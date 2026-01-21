package com.mycompany.app.utility;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;
import java.util.List;

@Log4j2
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
                log.error("Periodic reader interrupted.");
                return;
            }
        }
    }

    private void readFiles() {
        List<T> objects = readFromFiles(path, regex);
        for (T t: objects) {
            try {
                log.info(objectMapper.writeValueAsString(t));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    private void printExecutionTime() {
        log.info("###########################");
        log.info(Instant.now().toString());
        log.info("###########################");
    }
}
