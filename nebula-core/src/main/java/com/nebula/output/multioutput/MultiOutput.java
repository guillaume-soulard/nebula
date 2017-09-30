package com.nebula.output.multioutput;

import com.nebula.output.Output;

import java.util.Arrays;
import java.util.List;

public class MultiOutput implements Output {
    private List<Output> outputs;

    public MultiOutput(Output... outputs) {
        this.outputs = Arrays.asList(outputs);
    }

    @Override
    public void write(String... formattedObjects) {
        for (Output output : outputs) {
            output.write(formattedObjects);
        }
    }
}
