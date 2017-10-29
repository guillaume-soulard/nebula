package com.nebula.output.stdout;

import com.nebula.output.Output;

public class StandardOutputOutput implements Output {

    StandardOutputOutput() {

    }

    @Override
    public void open() {
    }

    public void write(String... formattedObjects) {
        for (String formattedObject : formattedObjects) {
            System.out.print(formattedObject);
        }
        System.out.flush();
    }

    @Override
    public void close() {
    }
}
