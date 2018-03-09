package com.nebula.core.types.text.dictionary;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnglishDictionaryTest {

    @Test
    public void newEnglishDictionary_should_have_non_empty_word_list() throws Exception {

        // GIVEN
        EnglishDictionary dictionary;

        // WHEN
        dictionary = new EnglishDictionary();

        // THEN
        assertThat(dictionary.getWords()).isNotEmpty();
    }
}
