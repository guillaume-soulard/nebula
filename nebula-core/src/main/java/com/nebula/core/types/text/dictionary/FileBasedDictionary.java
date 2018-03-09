package com.nebula.core.types.text.dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

abstract class FileBasedDictionary extends NebulaDictionary {
    FileBasedDictionary(String fileName) throws IOException {
        super(Files.readAllLines(Paths.get(ClassLoader.getSystemClassLoader().getResource(fileName).getPath())));
    }
}
