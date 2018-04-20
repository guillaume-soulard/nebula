package com.nebula.core.types.text;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.text.dictionary.NebulaDictionary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class TextTypeTest {

    private TextType textType;

    @Mock
    private TextGenerationConfiguration textGenerationConfiguration;

    @Mock
    private NebulaDictionary dictionary;

    @Before
    public void setUp() {
        textType = new TextType(dictionary, textGenerationConfiguration);
        GenerationContext context = new GenerationContext(new NebulaRandom(1L), mock(Model.class), 0L, 1, 10);
        textType.init(context);
    }

    @Test
    public void generateObject_should_return_a_non_null_string() {

        // GIVEN

        // WHEN
        GeneratedObject result = textType.generateObject(0L);

        // THEN
        assertThat(result).isNotNull();
    }

//    @Test
//    public void generateObject_should_call_generate_on_TextGenerator() {
//
//        // GIVEN
//
//        // WHEN
//        textType.generateObject(0L);
//
//        // THEN
//        verify(textGeneratorModel).getText(any());
//    }

//    @Test
//    public void generateObject_should_return_TextGenerator_generate_return() {
//
//        // GIVEN
//        String expectedString = "test";
//        when(textGeneratorModel.getText(any())).thenReturn(expectedString);
//
//        // WHEN
//        GeneratedObject result = textType.generateObject(0L);
//
//        // THEN
//        assertThat(result.getObject()).isEqualTo(expectedString);
//    }

    @Test
    public void getMinRange_should_return_0() {

        // GIVEN

        // WHEN
        Long result = textType.getMinRange();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    public void getMaxRange_should_return_0() {

        // GIVEN

        // WHEN
        Long result = textType.getMaxRange();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    public void getJavaType_should_return_STRING() {

        // GIVEN

        // WHEN
        JavaType result = textType.getJavaType();

        // THEN
        assertThat(result).isEqualTo(JavaType.STRING);
    }
}
