package com.nebula.core.output.file;

import com.nebula.core.NebulaException;
import com.nebula.core.output.Output;
import com.nebula.core.output.OutputParameter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileOutput implements Output {
    private final String charset;
    private final Path filePath;
    private FileOutputStream fileOutputStream;

    FileOutput(String filePath, String charset) {
        this.filePath = Paths.get(filePath);
        this.charset = charset;
    }

    @Override
    public void open() {
        try {
            if (Files.isDirectory(filePath)) {
                throw new NebulaException("'" + filePath.toFile().getPath() + "' is a directory");
            }
            Files.deleteIfExists(filePath);
            createFileIfNotExists();
            fileOutputStream = new FileOutputStream(filePath.toFile());
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    public void write(OutputParameter formattedObject) {
        try {
            writeFormattedObjectToFile(formattedObject);
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    private void writeFormattedObjectToFile(OutputParameter formattedObject) throws IOException {
        fileOutputStream.write(formattedObject.getFormattedObject().getBytes(charset));
        fileOutputStream.flush();
    }

    private void createFileIfNotExists() throws IOException {
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }
}
