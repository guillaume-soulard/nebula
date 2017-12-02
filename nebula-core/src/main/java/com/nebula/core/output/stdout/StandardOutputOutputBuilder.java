package com.nebula.core.output.stdout;

import com.nebula.core.output.Output;
import com.nebula.core.output.OutputBuilder;

public class StandardOutputOutputBuilder implements OutputBuilder {
    @Override
    public Output build() {
        return new StandardOutputOutput();
    }
}
