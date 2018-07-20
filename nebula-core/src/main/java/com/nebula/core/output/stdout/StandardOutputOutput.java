package com.nebula.core.output.stdout;

import com.nebula.core.output.Output;
import com.nebula.core.output.OutputParameter;

class StandardOutputOutput implements Output {

    StandardOutputOutput() {

    }

    @Override
    public void open() {
    }

    public void write(OutputParameter formattedObject) {
        System.out.print(formattedObject.getFormattedObject());
        System.out.flush();
    }

    @Override
    public void close() {
    }
}
