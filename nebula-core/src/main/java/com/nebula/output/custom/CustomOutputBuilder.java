package com.nebula.output.custom;

import com.nebula.core.NebulaException;
import com.nebula.output.Output;
import com.nebula.output.OutputBuilder;

public class CustomOutputBuilder implements OutputBuilder {

    private Output output;

    public CustomOutputBuilder(Output output) {
        if (output == null) {
            throw new NebulaException("output is null");
        }
        this.output = output;
    }

    @Override
    public Output build() {
        return output;
    }
}
