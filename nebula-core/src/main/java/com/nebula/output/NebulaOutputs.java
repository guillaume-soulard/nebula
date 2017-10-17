package com.nebula.output;

import com.nebula.output.file.FileOutputBuilder;
import com.nebula.output.stdout.StandardOutputOutputBuilder;

public final class NebulaOutputs {

    public static StandardOutputOutputBuilder stdout() {
        return new StandardOutputOutputBuilder();
    }

    public static FileOutputBuilder file(String path) {
        return new FileOutputBuilder(path);
    }
}