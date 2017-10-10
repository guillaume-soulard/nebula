package com.nebula.output;

public interface Output {
    void open();
    void write(String... formattedObjects);
    void close();
}
