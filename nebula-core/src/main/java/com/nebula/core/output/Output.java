package com.nebula.core.output;

public interface Output {
    void open();
    void write(OutputParameter formattedObject);
    void close();
}
