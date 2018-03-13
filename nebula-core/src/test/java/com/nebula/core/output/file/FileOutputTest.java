package com.nebula.core.output.file;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileOutputTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        temporaryFolder.create();
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();
    }

    @Test
    public void write_should_write_in_existing_file() throws Exception {

        // GIVEN
        File fileToWrite = temporaryFolder.newFile();
        FileOutput fileOutput = new FileOutput(fileToWrite.getPath(), "UTF-8");
        String formattedObject = "content to write";
        fileOutput.open();

        // WHEN
        fileOutput.write(formattedObject);

        // THEN
        assertThat(fileToWrite).exists().hasContent(formattedObject);
    }

    @Test
    public void write_should_write_in_non_existing_file() {

        // GIVEN
        File fileToWrite = new File(temporaryFolder.getRoot(), "nonExisting.tmp");
        FileOutput fileOutput = new FileOutput(fileToWrite.getPath(), "UTF-8");
        String formattedObject = "content to write";
        fileOutput.open();

        // WHEN
        fileOutput.write(formattedObject);

        // THEN
        assertThat(fileToWrite).exists().hasContent(formattedObject);
    }

    @Test
    public void write_should_write_in_file_with_UTF_8_encoding() throws Exception {

        // GIVEN
        File fileToWrite = temporaryFolder.newFile();
        String charset = "UTF-8";
        FileOutput fileOutput = new FileOutput(fileToWrite.getPath(), charset);
        String formattedObject = "content to write";
        fileOutput.open();

        // WHEN
        fileOutput.write(formattedObject);

        // THEN
        assertThat(fileToWrite).exists().hasBinaryContent(formattedObject.getBytes(charset));
    }

    @Test
    public void write_should_write_in_file_with_ISO_8859_1_encoding() throws Exception {

        // GIVEN
        File fileToWrite = temporaryFolder.newFile();
        String charset = "ISO-8859-1";
        FileOutput fileOutput = new FileOutput(fileToWrite.getPath(), charset);
        String formattedObject = "content to write";
        fileOutput.open();

        // WHEN
        fileOutput.write(formattedObject);

        // THEN
        assertThat(fileToWrite).exists().hasBinaryContent(formattedObject.getBytes(charset));
    }

    @Test
    public void write_should_replace_existing_file_content() throws Exception {

        // GIVEN
        File fileToWrite = temporaryFolder.newFile();
        String existingContent = "existing content";
        Files.write(Paths.get(fileToWrite.getPath()), existingContent.getBytes());
        FileOutput fileOutput = new FileOutput(fileToWrite.getPath(), "UTF-8");
        String newContent = "new content";
        fileOutput.open();

        // WHEN
        fileOutput.write(newContent);

        // THEN
        assertThat(fileToWrite).exists().hasContent(newContent);
    }
}
