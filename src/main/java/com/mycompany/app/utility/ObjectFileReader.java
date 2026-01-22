package com.mycompany.app.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@RequiredArgsConstructor
public class ObjectFileReader<T> {
    final ObjectMapper objectMapper = new ObjectMapper();
    private final SourceReader reader = new SourceReader();
    private final Class<T> clazz;

    public T readFromFile(String path) {
        return mapToObject(reader.fileToString(path));
    }

    public List<T> readFromFiles(String path, String regex) {
        List<String> testFiles = reader.readFiles(path, regex);
        return testFiles.stream()
                .map(this::mapToObject)
                .toList();
    }

    public T mapToObject(String string) {
        try {
            return objectMapper.readValue(string, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String mapToString(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
