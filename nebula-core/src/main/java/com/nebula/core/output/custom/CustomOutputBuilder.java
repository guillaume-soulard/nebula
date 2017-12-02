package com.nebula.core.output.custom;

import com.nebula.core.NebulaException;
import com.nebula.core.output.Output;
import com.nebula.core.output.OutputBuilder;

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
