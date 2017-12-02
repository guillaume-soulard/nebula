package com.nebula.core.output.stdout;

import com.nebula.core.output.Output;

class StandardOutputOutput implements Output {

    StandardOutputOutput() {

    }

    @Override
    public void open() {
    }

    public void write(String formattedObject) {
        System.out.print(formattedObject);
        System.out.flush();
    }

    @Override
    public void close() {
    }
}
