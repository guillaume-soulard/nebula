package com.nebula.core.output;

public interface Output {
    void open();
    void write(String formattedObject);
    void close();
}
