package com.nebula.core.output.file;

import com.nebula.core.output.Output;
import com.nebula.core.output.OutputBuilder;

public class FileOutputBuilder implements OutputBuilder {
    private String path;
    private String charset = "UTF-8";

    public FileOutputBuilder(String path) {
        this.path = path;
    }

    @Override
    public Output build() {
        return new FileOutput(path, charset);
    }

    public FileOutputBuilder withCharset(String charset) {
        this.charset = charset;
        return this;
    }
}
