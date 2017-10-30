package com.nebula.output;

public interface Output {
    void open();
    void write(String formattedObject);
    void close();
}
