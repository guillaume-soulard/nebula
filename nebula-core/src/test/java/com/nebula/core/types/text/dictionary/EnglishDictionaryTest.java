package com.nebula.core.types.text.dictionary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnglishDictionaryTest {

    @Test
    @DisplayName("newEnglishDictionary should have non empty word list")
    void newEnglishDictionary_should_have_non_empty_word_list() throws Exception {

        // GIVEN
        EnglishDictionary dictionary;

        // WHEN
        dictionary = new EnglishDictionary();

        // THEN
        assertThat(dictionary.getWords()).isNotEmpty();
    }
}
