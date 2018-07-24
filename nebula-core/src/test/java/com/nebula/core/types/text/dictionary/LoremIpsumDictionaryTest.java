package com.nebula.core.types.text.dictionary;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoremIpsumDictionaryTest {

    @Test
    void newLoremIpsumDictionary_should_have_non_empty_word_list() throws Exception {

        // GIVEN
        LoremIpsumDictionary dictionary;

        // WHEN
        dictionary = new LoremIpsumDictionary();

        // THEN
        assertThat(dictionary.getWords()).isNotEmpty();
    }
}
