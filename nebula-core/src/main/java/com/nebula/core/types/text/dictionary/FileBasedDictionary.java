package com.nebula.core.types.text.dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

abstract class FileBasedDictionary extends NebulaDictionary {
    FileBasedDictionary(String fileName) throws IOException {
        super(Files.readAllLines(
                        normalizePath(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(fileName))
                                .getPath()
                )
            )
        );
    }

    static Path normalizePath(String path) {

        String normalizedPath;

        if (isWindowPath(path)) {

            normalizedPath = path.substring(1);
        } else {

            normalizedPath = path;
        }

        return Paths.get(normalizedPath);
    }

    private static boolean isWindowPath(String path) {
        return path.matches("([/\\\\])[A-Z]:([/\\\\]).*");
    }
}
