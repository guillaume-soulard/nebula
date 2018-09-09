package com.nebula.core.output.file;

import com.nebula.core.output.OutputParameter;
import io.github.glytching.junit.extension.folder.TemporaryFolder;
import io.github.glytching.junit.extension.folder.TemporaryFolderExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.util.function.Function;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SlicedFileOutputTest {

    private static Function<SlicedFileIndexContext, String> DEFAULT_FILE_NAME_FUNCTION = slicedFileIndexContext -> String.format("generated-file-%s", String.valueOf(slicedFileIndexContext.getFileIndex()));

    @Test
    @ExtendWith(TemporaryFolderExtension.class)
    @DisplayName("write should generate one file")
    void test1(TemporaryFolder temporaryFolder) {

        // GIVEN
        SlicedFileOutput output = new SlicedFileOutput(temporaryFolder.getRoot().getPath(), "UTF-8", 10, DEFAULT_FILE_NAME_FUNCTION);
        output.open();
        OutputParameter object = new OutputParameter("test", null);

        // WHEN
        output.write(object);

        // THEN
        assertThat(new File(temporaryFolder.getRoot().getPath()).list()).containsOnly("generated-file-0");
        assertThat(new File(temporaryFolder.getRoot().getPath(), "generated-file-0")).hasContent("test");
    }

    @Test
    @ExtendWith(TemporaryFolderExtension.class)
    @DisplayName("write should generate three files")
    void test2(TemporaryFolder temporaryFolder) {

        // GIVEN
        SlicedFileOutput output = new SlicedFileOutput(temporaryFolder.getRoot().getPath(), "UTF-8", 1, DEFAULT_FILE_NAME_FUNCTION);
        output.open();
        OutputParameter object = new OutputParameter("test", null);

        // WHEN
        output.write(object);
        output.write(object);
        output.write(object);

        // THEN
        assertThat(new File(temporaryFolder.getRoot().getPath()).list()).containsOnly("generated-file-0", "generated-file-1", "generated-file-2");
        assertThat(new File(temporaryFolder.getRoot().getPath(), "generated-file-0")).hasContent("test");
        assertThat(new File(temporaryFolder.getRoot().getPath(), "generated-file-1")).hasContent("test");
        assertThat(new File(temporaryFolder.getRoot().getPath(), "generated-file-2")).hasContent("test");
    }
}