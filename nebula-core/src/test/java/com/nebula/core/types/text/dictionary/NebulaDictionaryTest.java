package com.nebula.core.types.text.dictionary;

import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.text.TextGenerationConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class NebulaDictionaryTest {

    private NebulaDictionary dictionary;
    private TextGenerationConfiguration configuration;
    private NebulaRandom nebulaRandom;

    @Before
    public void setUp() throws Exception {
        dictionary = new NebulaDictionary(Collections.singletonList("test"));
        configuration = new TextGenerationConfiguration();
        nebulaRandom = new NebulaRandom(1L);
    }

    @Test
    public void getText_should_return_non_null_text() throws Exception {

        // GIVEN

        // WHEN
        String result = dictionary.getText(nebulaRandom, configuration);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    public void getText_should_return_string_with_test_inside() throws Exception {

        // GIVEN

        // WHEN
        String result = dictionary.getText(nebulaRandom, configuration);

        // THEN
        assertThat(result).contains("test");
    }

    @Test
    public void getText_should_return_string_only_one_word_and_one_sentence() throws Exception {

        // GIVEN
        configuration.setMinWords(1);
        configuration.setMaxWords(1);
        configuration.setMinSentences(1);
        configuration.setMaxSentences(1);

        // WHEN
        String result = dictionary.getText(nebulaRandom, configuration);

        // THEN
        assertThat(result).isEqualTo("Test.");
    }

    @Test
    public void getText_should_return_string_with_two_words_in_two_sentences() throws Exception {

        // GIVEN
        configuration.setMinWords(1);
        configuration.setMaxWords(1);
        configuration.setMinSentences(2);
        configuration.setMaxSentences(2);

        // WHEN
        String result = dictionary.getText(nebulaRandom, configuration);

        // THEN
        assertThat(result).isEqualTo("Test. Test.");
    }

    @Test
    public void getText_should_return_a_sentence_with_a_number_of_words_between_5_and_10() throws Exception {

        // GIVEN
        configuration.setMinWords(5);
        configuration.setMaxWords(10);
        configuration.setMinSentences(1);
        configuration.setMaxSentences(1);

        // WHEN
        String result = dictionary.getText(nebulaRandom, configuration);

        // THEN
        assertThat(result.split(" ")).containsOnly("Test", "test", "test.");
    }
}