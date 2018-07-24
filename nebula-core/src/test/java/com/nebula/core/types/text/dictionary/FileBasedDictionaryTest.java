package com.nebula.core.types.text.dictionary;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class FileBasedDictionaryTest {

    @Test
    void normalizePath_should_not_normalize_linux_like_path() {

        // GIVEN
        String originalPath = "/home/ogama/test.txt";

        // WHEN
        Path result = FileBasedDictionary.normalizePath(originalPath);

        // THEN
        assertThat(result.toFile().getPath()).isEqualTo(replaceWithSystemPathSeparator(originalPath));
    }

    @Test
    void normalizePath_should_normalize_windows_like_path() {

        // GIVEN
        String originalPath = "/C:/users/ogama/test.txt";

        // WHEN
        Path result = FileBasedDictionary.normalizePath(originalPath);

        // THEN
        assertThat(result.toFile().getPath()).isEqualTo(replaceWithSystemPathSeparator("C:/users/ogama/test.txt"));
    }

    @Test
    void normalizePath_should_normalize_windows_like_path_with_backslash() {

        // GIVEN
        String originalPath = "\\C:\\users\\ogama\\test.txt";

        // WHEN
        Path result = FileBasedDictionary.normalizePath(originalPath);

        // THEN
        assertThat(result.toFile().getPath()).isEqualTo(replaceWithSystemPathSeparator("C:\\users\\ogama\\test.txt"));
    }

    private String replaceWithSystemPathSeparator(String s) {
        return s.replaceAll("/", File.separator + File.separator);
    }
}