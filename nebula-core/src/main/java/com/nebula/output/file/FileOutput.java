package com.nebula.output.file;

import com.nebula.core.NebulaException;
import com.nebula.output.Output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileOutput implements Output {
    private final String charset;
    private Path filePath;

    public FileOutput(String filePath, String charset) {
        this.filePath = Paths.get(filePath);
        this.charset = charset;
    }

    public void write(String... formattedObjects) {
        try {
            Files.deleteIfExists(filePath);
            createFileIfNotExists();
            writeFormattedObjectsToFile(formattedObjects);
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    private void writeFormattedObjectsToFile(String[] formattedObjects) throws IOException {
        for (String formattedObject : formattedObjects) {
            writeFormattedObjectToFile(formattedObject);
        }
    }

    private void writeFormattedObjectToFile(String formattedObject) throws IOException {
        Files.write(filePath, formattedObject.getBytes(charset), StandardOpenOption.APPEND);
    }

    private void createFileIfNotExists() throws IOException {
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }
}
