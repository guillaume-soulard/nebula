package com.nebula.core.types.string;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StringTypeTest {

	@Test
    @DisplayName("generateObject should return a non null GeneratedObject")
	void generateObject_should_return_a_non_null_GeneratedObject() {

		// GIVEN
		String pattern = "[a]{1}";
		StringType stringType = new StringType(newStringGenerator(pattern));
		long entityIndex = 0L;
        stringType.init(GenerationContext.of(new NebulaRandom(1L), null, entityIndex, 1, 10));

		// WHEN
        GeneratedObject result = stringType.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("generateObject should return GeneratedObject with non null value")
	void generateObject_should_return_GeneratedObject_with_non_null_value() {

		// GIVEN
		String pattern = "[a]{1}";
		StringType stringType = new StringType(newStringGenerator(pattern));
		long entityIndex = 0L;
        stringType.init(GenerationContext.of(new NebulaRandom(1L), null, entityIndex, 1, 10));

		// WHEN
        GeneratedObject result = stringType.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// THEN
		assertThat(result.getObject()).isNotNull();
	}

	@Test
    @DisplayName("generateObject should return GeneratedObject with value matching pattern")
	void generateObject_should_return_GeneratedObject_with_value_matching_pattern() {

		// GIVEN
		StringType stringType = new StringType(newStringGenerator("test"));
		long entityIndex = 0L;
        stringType.init(GenerationContext.of(new NebulaRandom(1L), null, entityIndex, 1, 10));

		// WHEN
        GeneratedObject result = stringType.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// THEN
		assertThat(result.getObject()).isInstanceOf(String.class).isEqualTo("test");
	}

	@Test
    @DisplayName("generateObject should generate different strings with index 0 and 1")
	void generateObject_should_generate_different_strings_with_index_0_and_1() {

		// GIVEN
		StringType stringType = new StringType(newStringGenerator(null));
		long entityIndex = 0L;
        stringType.init(GenerationContext.of(new NebulaRandom(1L), null, entityIndex, 1, 10));
        GeneratedObject resultIndex0 = stringType.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// WHEN
        GeneratedObject resultIndex1 = stringType.generateObject(new GeneratedProperties(Collections.emptyList()), 1L);

		// THEN
		assertThat(resultIndex0.getObject()).isNotEqualTo(resultIndex1.getObject());
	}

	@Test
    @DisplayName("generateObject should call stringGenerator generateString")
	void generateObject_should_call_stringGenerator_generateString() {

		// GIVEN
		StringGenerator stringGenerator = mock(StringGenerator.class);
		StringType stringType = new StringType(stringGenerator);

		// WHEN
        stringType.generateObject(new GeneratedProperties(Collections.emptyList()), 1L);

		// THEN
		verify(stringGenerator, times(1)).generateString();
	}

	@Test
    @DisplayName("newStringType should set pattern")
	void newStringType_should_set_pattern() {

		// GIVEN
		StringType stringType;
		String pattern = "test pattern";

		// WHEN
		stringType = new StringType(newStringGenerator(pattern));

		// THEN
		assertThat(stringType).hasFieldOrPropertyWithValue("stringGenerator.pattern", pattern);
	}

	@Test
    @DisplayName("newStringType should set default pattern when null is passed")
	void newStringType_should_set_default_pattern_when_null_is_passed() {

		// GIVEN
		StringType stringType;
		String expectedPattern = "[a-zA-Z_0-9]{10}";

		// WHEN
		stringType = new StringType(newStringGenerator(null));

		// THEN
		assertThat(stringType).hasFieldOrPropertyWithValue("stringGenerator.pattern", expectedPattern);
	}

	@Test
    @DisplayName("getMinRange should return 0")
	void getMinRange_should_return_0() {

		// GIVEN
		StringType stringType = new StringType(newStringGenerator(null));

		// WHEN
		Long result = stringType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("getMaxRange should return 0")
	void getMaxRange_should_return_0() {

		// GIVEN
		StringType stringType = new StringType(newStringGenerator(null));

		// WHEN
		Long result = stringType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("init should set new generex")
	void init_should_set_new_generex() {

		// GIVEN
		StringGenerator stringGenerator = mock(StringGenerator.class);
		StringType stringType = new StringType(stringGenerator);
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Model model = ModelBuilder.newEmptyModel().build();
		long entityIndex = 0L;
        GenerationContext context = GenerationContext.of(nebulaRandom, model, entityIndex, 1, 10);

		// WHEN
		stringType.init(context);

		// THEN
		verify(stringGenerator, times(1)).setSeed(0L);
	}

	private StringGenerator newStringGenerator(String pattern) {
		return StringGenerator.newStringGenerator(pattern);
	}
}
