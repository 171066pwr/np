package com.mycompany.app.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SourceReader {
    /**
     * Test of javadoc creation.
     * @param path
     * @param regex
     * @return list of contents of files contained in specified path, non-recursively
     */
    public List<String> readFiles(String path, String regex) {
        File dir = new File(path);
        String[] files = dir.list((d, name) -> name.matches(regex));
        List<String> testFiles = files == null ? new ArrayList<>() : Arrays.asList(files);
        return testFiles.stream()
            .map(s -> fileToString(path + s))
            .toList();
    }

    String fileToString(String resource) {
        try (Scanner scanner = new Scanner(new File(resource))) {
            List<String> contents = scanner.tokens().toList();
            return String.join("\n", contents);
        } catch (FileNotFoundException e) {
            return "";
        }
    }
}
