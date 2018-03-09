package com.nebula.core.types.text.dictionary;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoremIpsumDictionaryTest {

    @Test
    public void newLoremIpsumDictionary_should_have_non_empty_word_list() throws Exception {

        // GIVEN
        LoremIpsumDictionary dictionary;

        // WHEN
        dictionary = new LoremIpsumDictionary();

        // THEN
        assertThat(dictionary.getWords()).isNotEmpty();
    }
}
