package com.nebula.core.types.text;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.text.dictionary.NebulaDictionary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TextTypeTest {

    private TextType textType;

    @Mock
    private TextGenerationConfiguration textGenerationConfiguration;

    @Mock
    private NebulaDictionary dictionary;

    @BeforeEach
    void setUp() {
        textType = new TextType(dictionary, textGenerationConfiguration);
        GenerationContext context = new GenerationContext(new NebulaRandom(1L), mock(Model.class), 0L, 1, 10);
        textType.init(context);
    }

    @Test
    @DisplayName("generateObject should return a non null string")
    void generateObject_should_return_a_non_null_string() {

        // GIVEN

        // WHEN
        GeneratedObject result = textType.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getMinRange should return 0")
    void getMinRange_should_return_0() {

        // GIVEN

        // WHEN
        Long result = textType.getMinRange();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    @DisplayName("getMaxRange should return 0")
    void getMaxRange_should_return_0() {

        // GIVEN

        // WHEN
        Long result = textType.getMaxRange();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    @DisplayName("getJavaType should return STRING")
    void getJavaType_should_return_STRING() {

        // GIVEN

        // WHEN
        JavaType result = textType.getJavaType();

        // THEN
        assertThat(result).isEqualTo(JavaType.STRING);
    }
}
