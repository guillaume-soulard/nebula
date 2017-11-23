package com.nebula.core.types.string;

import com.nebula.Model;
import com.nebula.ModelBuilder;
import com.nebula.core.GeneratedObject;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StringTypeTest {

	@Test
	public void generateObject_should_return_a_non_null_GeneratedObject() {

		// GIVEN
		String pattern = "[a]{1}";
		StringType stringType = new StringType(newStringGenerator(pattern));
		long entityIndex = 0L;
		stringType.init(new GenerationContext(new NebulaRandom(1l), null, entityIndex));

		// WHEN
		GeneratedObject result = stringType.generateObject(0l);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateObject_should_return_GeneratedObject_with_non_null_value() {

		// GIVEN
		String pattern = "[a]{1}";
		StringType stringType = new StringType(newStringGenerator(pattern));
		long entityIndex = 0L;
		stringType.init(new GenerationContext(new NebulaRandom(1l), null, entityIndex));

		// WHEN
		GeneratedObject result = stringType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isNotNull();
	}

	@Test
	public void generateObject_should_return_GeneratedObject_with_value_matching_pattern() {

		// GIVEN
		StringType stringType = new StringType(newStringGenerator("test"));
		long entityIndex = 0L;
		stringType.init(new GenerationContext(new NebulaRandom(1l), null, entityIndex));

		// WHEN
		GeneratedObject result = stringType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isInstanceOf(String.class).isEqualTo("test");
	}

	@Test
	public void generateObject_should_generate_different_strings_with_index_0_and_1() {

		// GIVEN
		StringType stringType = new StringType(newStringGenerator(null));
		long entityIndex = 0L;
		stringType.init(new GenerationContext(new NebulaRandom(1l), null, entityIndex));
		GeneratedObject resultIndex0 = stringType.generateObject(0l);

		// WHEN
		GeneratedObject resultIndex1 = stringType.generateObject(1l);

		// THEN
		assertThat(resultIndex0.getObject()).isNotEqualTo(resultIndex1.getObject());
	}

	@Test
	public void generateObject_should_call_stringGenerator_generateString() {

		// GIVEN
		StringGenerator stringGenerator = mock(StringGenerator.class);
		StringType stringType = new StringType(stringGenerator);

		// WHEN
		stringType.generateObject(1l);

		// THEN
		verify(stringGenerator, times(1)).generateString();
	}

	@Test
	public void newStringType_should_set_pattern() {

		// GIVEN
		StringType stringType = null;
		String pattern = "test pattern";

		// WHEN
		stringType = new StringType(newStringGenerator(pattern));

		// THEN
		assertThat(stringType).hasFieldOrPropertyWithValue("stringGenerator.pattern", pattern);
	}

	@Test
	public void newStringType_should_set_default_pattern_when_null_is_passed() {

		// GIVEN
		StringType stringType = null;
		String expectedPattern = "[a-zA-Z_0-9]{10}";

		// WHEN
		stringType = new StringType(newStringGenerator(null));

		// THEN
		assertThat(stringType).hasFieldOrPropertyWithValue("stringGenerator.pattern", expectedPattern);
	}

	@Test
	public void getMinRange_should_return_0() {

		// GIVEN
		String pattern = null;
		StringType stringType = new StringType(newStringGenerator(pattern));

		// WHEN
		Long result = stringType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_0() {

		// GIVEN
		String pattern = null;
		StringType stringType = new StringType(newStringGenerator(pattern));

		// WHEN
		Long result = stringType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void init_should_set_new_generex() {

		// GIVEN
		StringGenerator stringGenerator = mock(StringGenerator.class);
		StringType stringType = new StringType(stringGenerator);
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		Model model = new ModelBuilder().build();
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex);

		// WHEN
		stringType.init(context);

		// THEN
		verify(stringGenerator, times(1)).setSeed(0l);
	}

	private StringGenerator newStringGenerator(String pattern) {
		return StringGenerator.newStringGenerator(pattern);
	}
}
