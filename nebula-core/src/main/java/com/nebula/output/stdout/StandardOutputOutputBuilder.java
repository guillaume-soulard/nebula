package com.nebula.output.stdout;

import com.nebula.output.Output;
import com.nebula.output.OutputBuilder;

public class StandardOutputOutputBuilder implements OutputBuilder {
    @Override
    public Output build() {
        return new StandardOutputOutput();
    }
}
