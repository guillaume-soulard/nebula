package com.nebula.core.output.file;

import com.nebula.core.NebulaException;
import com.nebula.core.output.Output;
import com.nebula.core.output.OutputParameter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class SlicedFileOutput implements Output {

    private final int maxObjectPerFiles;
    private final Function<SlicedFileIndexContext, String> fileNameFunction;
    private String outputDirectory;
    private String charset;
    private FileOutputStream currentFileOutputStream;
    private AtomicLong currentFileIndex = new AtomicLong();
    private AtomicLong numberOfObjectsInCurrentFile = new AtomicLong();
    private AtomicLong numberOfGeneratedObjectWritten = new AtomicLong();

    SlicedFileOutput(String outputDirectory,
                     String charset,
                     int maxObjectPerFiles,
                     Function<SlicedFileIndexContext, String> fileNameFunction) {
        this.outputDirectory = outputDirectory;
        this.charset = charset;
        this.maxObjectPerFiles = maxObjectPerFiles;
        this.fileNameFunction = fileNameFunction;
    }

    @Override
    public void open() {

        try {
            currentFileIndex.set(0L);
            numberOfObjectsInCurrentFile.set(0L);
            numberOfGeneratedObjectWritten.set(0L);
            openFileIfNeeded();
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    @Override
    public void write(OutputParameter formattedObject) {

        try {

            openFileIfNeeded();
            currentFileOutputStream.write(formattedObject.getFormattedObject().getBytes(charset));
            numberOfObjectsInCurrentFile.incrementAndGet();
            numberOfGeneratedObjectWritten.incrementAndGet();
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    private void openFileIfNeeded() throws IOException {

        if (currentFileOutputStream == null || numberOfObjectsInCurrentFile.get() >= maxObjectPerFiles) {

            if (currentFileOutputStream != null) {
                currentFileOutputStream.flush();
            }
            System.out.println("new file " + numberOfGeneratedObjectWritten.get());

            currentFileOutputStream = newFile();
            numberOfObjectsInCurrentFile.set(0L);
        }
    }

    private FileOutputStream newFile() {
        try {
            return new FileOutputStream(new File(outputDirectory, getFileNameFromCurrentIndex()));
        } catch (FileNotFoundException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    private String getFileNameFromCurrentIndex() {

        return fileNameFunction.apply(new SlicedFileIndexContext(currentFileIndex.getAndIncrement(), numberOfGeneratedObjectWritten.get()));
    }

    @Override
    public void close() {

        try {
            currentFileOutputStream.flush();
            currentFileOutputStream.close();
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }
}
