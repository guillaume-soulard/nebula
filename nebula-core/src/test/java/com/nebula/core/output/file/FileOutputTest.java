package com.nebula.core.output.file;

import com.nebula.core.output.OutputParameter;
import io.github.glytching.junit.extension.folder.TemporaryFolder;
import io.github.glytching.junit.extension.folder.TemporaryFolderExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class FileOutputTest {

    @Test
    @ExtendWith(TemporaryFolderExtension.class)
    void write_should_write_in_existing_file(TemporaryFolder temporaryFolder) throws Exception {

        // GIVEN
        File fileToWrite = temporaryFolder.createFile("test.txt");
        FileOutput fileOutput = new FileOutput(fileToWrite.getPath(), "UTF-8");
        String formattedObject = "content to write";
        fileOutput.open();
        OutputParameter objectToWrite = new OutputParameter(formattedObject, null);

        // WHEN
        fileOutput.write(objectToWrite);

        // THEN
        assertThat(fileToWrite).exists().hasContent(formattedObject);
    }

    @Test
    @ExtendWith(TemporaryFolderExtension.class)
    void write_should_write_in_non_existing_file(TemporaryFolder temporaryFolder) {

        // GIVEN
        File fileToWrite = new File(temporaryFolder.getRoot(), "nonExisting.tmp");
        FileOutput fileOutput = new FileOutput(fileToWrite.getPath(), "UTF-8");
        String formattedObject = "content to write";
        fileOutput.open();
        OutputParameter objectToWrite = new OutputParameter(formattedObject, null);

        // WHEN
        fileOutput.write(objectToWrite);

        // THEN
        assertThat(fileToWrite).exists().hasContent(formattedObject);
    }

    @Test
    @ExtendWith(TemporaryFolderExtension.class)
    void write_should_write_in_file_with_UTF_8_encoding(TemporaryFolder temporaryFolder) throws Exception {

        // GIVEN
        File fileToWrite = temporaryFolder.createFile("test.txt");
        String charset = "UTF-8";
        FileOutput fileOutput = new FileOutput(fileToWrite.getPath(), charset);
        String formattedObject = "content to write";
        fileOutput.open();
        OutputParameter objectToWrite = new OutputParameter(formattedObject, null);

        // WHEN
        fileOutput.write(objectToWrite);

        // THEN
        assertThat(fileToWrite).exists().hasBinaryContent(formattedObject.getBytes(charset));
    }

    @Test
    @ExtendWith(TemporaryFolderExtension.class)
    void write_should_write_in_file_with_ISO_8859_1_encoding(TemporaryFolder temporaryFolder) throws Exception {

        // GIVEN
        File fileToWrite = temporaryFolder.createFile("test.txt");
        String charset = "ISO-8859-1";
        FileOutput fileOutput = new FileOutput(fileToWrite.getPath(), charset);
        String formattedObject = "content to write";
        fileOutput.open();
        OutputParameter objectToWrite = new OutputParameter(formattedObject, null);

        // WHEN
        fileOutput.write(objectToWrite);

        // THEN
        assertThat(fileToWrite).exists().hasBinaryContent(formattedObject.getBytes(charset));
    }

    @Test
    @ExtendWith(TemporaryFolderExtension.class)
    void write_should_replace_existing_file_content(TemporaryFolder temporaryFolder) throws Exception {

        // GIVEN
        File fileToWrite = temporaryFolder.createFile("test.txt");
        String existingContent = "existing content";
        Files.write(Paths.get(fileToWrite.getPath()), existingContent.getBytes());
        FileOutput fileOutput = new FileOutput(fileToWrite.getPath(), "UTF-8");
        String newContent = "new content";
        fileOutput.open();
        OutputParameter objectToWrite = new OutputParameter(newContent, null);

        // WHEN
        fileOutput.write(objectToWrite);

        // THEN
        assertThat(fileToWrite).exists().hasContent(newContent);
    }
}
