package com.nebula.core.output.file;

public class SlicedFileIndexContext {

    private long fileIndex;
    private long numberOfTotalGeneratedObjects;

    public SlicedFileIndexContext(long fileIndex, long numberOfTotalGeneratedObjects) {
        this.fileIndex = fileIndex;
        this.numberOfTotalGeneratedObjects = numberOfTotalGeneratedObjects;
    }

    public long getFileIndex() {
        return fileIndex;
    }

    public long getNumberOfTotalGeneratedObjects() {
        return numberOfTotalGeneratedObjects;
    }
}
