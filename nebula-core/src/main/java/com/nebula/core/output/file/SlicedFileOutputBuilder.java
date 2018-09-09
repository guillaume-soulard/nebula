package com.nebula.core.output.file;

import com.nebula.core.output.Output;
import com.nebula.core.output.OutputBuilder;

import java.util.function.Function;

public class SlicedFileOutputBuilder implements OutputBuilder {

    private String charset = "UTF-8";
    private String outputDirectory;
    private int maxObjectPerFiles = 10;
    private Function<SlicedFileIndexContext, String> fileNameFunction = slicedFileIndexContext -> String.format("generated-file-%s", String.valueOf(slicedFileIndexContext.getFileIndex()));

    public SlicedFileOutputBuilder(String outputDirectory) {

        this.outputDirectory = outputDirectory;
    }

    @Override
    public Output build() {
        return new SlicedFileOutput(outputDirectory, charset, maxObjectPerFiles, fileNameFunction);
    }

    public SlicedFileOutputBuilder withCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public SlicedFileOutputBuilder withMaxObjectPerFiles(int maxObjectPerFiles) {
        this.maxObjectPerFiles = maxObjectPerFiles;
        return this;
    }

    public SlicedFileOutputBuilder withFileNameFunction(Function<SlicedFileIndexContext, String> callback) {
        this.fileNameFunction = callback;
        return this;
    }
}
