package com.mycompany.app.utility;

import com.mycompany.app.configuration.PropertiesProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ResourceReader {
    public List<String> readResourceFiles(String path, String regex) throws IOException {
        List<String> testFiles = getResourceFiles(PropertiesProvider.TEST_PATH, ".*.json");
        return testFiles.stream()
            .map(s -> {
                try {
                    return resourceToString(PropertiesProvider.TEST_PATH + s);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            })
            .toList();
    }

    /**
     * Test of javadoc creation;
     * @param path
     * @param pattern
     * @return
     * @throws IOException
     */
    private List<String> getResourceFiles(String path, String pattern) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        List<String> filenames = new ArrayList<>();
        try (InputStream in = classLoader.getResourceAsStream(path);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;
            while ((resource = br.readLine()) != null && resource.matches(pattern)) {
                filenames.add(resource);
            }
        }
        return filenames;
    }

    private String resourceToString(String resourceName) throws IOException {
        return String.join("\n", resourceToList(resourceName));
    }

    //This method does not need preceeding / in the path - navigation starts at the root of the classpath
    private List<String> resourceToList(String resourceName){
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + resourceName);
        } else {
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            return reader.lines().toList();
        }
    }
}
