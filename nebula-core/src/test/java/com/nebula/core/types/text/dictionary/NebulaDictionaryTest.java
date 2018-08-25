package com.nebula.core.types.text.dictionary;

import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.text.TextGenerationConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class NebulaDictionaryTest {

    private NebulaDictionary dictionary;
    private TextGenerationConfiguration configuration;
    private NebulaRandom nebulaRandom;

    @BeforeEach
    void setUp() {
        dictionary = new NebulaDictionary(Collections.singletonList("test"));
        configuration = new TextGenerationConfiguration();
        nebulaRandom = new NebulaRandom(1L);
    }

    @Test
    @DisplayName("getText should return non null text")
    void getText_should_return_non_null_text() {

        // GIVEN

        // WHEN
        String result = dictionary.getText(nebulaRandom, configuration);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getText should return string with test inside")
    void getText_should_return_string_with_test_inside() {

        // GIVEN

        // WHEN
        String result = dictionary.getText(nebulaRandom, configuration);

        // THEN
        assertThat(result).contains("test");
    }

    @Test
    @DisplayName("getText should return string only one word and one sentence")
    void getText_should_return_string_only_one_word_and_one_sentence() {

        // GIVEN
        configuration.setMinWordsPerSentence(1);
        configuration.setMaxWordsPerSentence(1);
        configuration.setMinSentences(1);
        configuration.setMaxSentences(1);

        // WHEN
        String result = dictionary.getText(nebulaRandom, configuration);

        // THEN
        assertThat(result).isEqualTo("Test.");
    }

    @Test
    @DisplayName("getText should return string with two words in two sentences")
    void getText_should_return_string_with_two_words_in_two_sentences() {

        // GIVEN
        configuration.setMinWordsPerSentence(1);
        configuration.setMaxWordsPerSentence(1);
        configuration.setMinSentences(2);
        configuration.setMaxSentences(2);

        // WHEN
        String result = dictionary.getText(nebulaRandom, configuration);

        // THEN
        assertThat(result).isEqualTo("Test. Test.");
    }

    @Test
    @DisplayName("getText should return a sentence with a number of words between 5 and 10")
    void getText_should_return_a_sentence_with_a_number_of_words_between_5_and_10() {

        // GIVEN
        configuration.setMinWordsPerSentence(5);
        configuration.setMaxWordsPerSentence(10);
        configuration.setMinSentences(1);
        configuration.setMaxSentences(1);

        // WHEN
        String result = dictionary.getText(nebulaRandom, configuration);

        // THEN
        assertThat(result.split(" ")).containsOnly("Test", "test", "test.");
    }
}