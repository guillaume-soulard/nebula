package com.nebula.core.types.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;

public class StringTypeTest {

	@Test
	public void generateObject_should_return_a_non_null_GeneratedObject() {

		// GIVEN
		String pattern = "[a]{1}";
		StringType stringType = new StringType(pattern);
		stringType.init(new GenerationContext(new NebulaRandom(1l), null));

		// WHEN
		GeneratedObject result = stringType.generateObject(0l);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateObject_should_return_GeneratedObject_with_non_null_value() {

		// GIVEN
		String pattern = "[a]{1}";
		StringType stringType = new StringType(pattern);
		stringType.init(new GenerationContext(new NebulaRandom(1l), null));

		// WHEN
		GeneratedObject result = stringType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isNotNull();
	}

	@Test
	public void generateObject_should_return_GeneratedObject_with_value_matching_pattern() {

		// GIVEN
		StringType stringType = new StringType("test");
		stringType.init(new GenerationContext(new NebulaRandom(1l), null));

		// WHEN
		GeneratedObject result = stringType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isInstanceOf(String.class).isEqualTo("test");
	}

	@Test
	public void generateObject_should_generate_defferent_strings_with_index_0_and_1() {

		// GIVEN
		StringType stringType = new StringType(null);
		stringType.init(new GenerationContext(new NebulaRandom(1l), null));
		GeneratedObject resultIndex0 = stringType.generateObject(0l);

		// WHEN
		GeneratedObject resultIndex1 = stringType.generateObject(1l);

		// THEN
		assertThat(resultIndex0.getObject()).isNotEqualTo(resultIndex1.getObject());
	}

	@Test
	public void newStringType_should_set_pattern() {

		// GIVEN
		StringType stringType = null;
		String pattern = "test pattern";

		// WHEN
		stringType = new StringType(pattern);

		// THEN
		assertThat(stringType).hasFieldOrPropertyWithValue("pattern", pattern);
	}

	@Test
	public void newStringType_should_set_default_pattern_when_null_is_passed() {

		// GIVEN
		StringType stringType = null;
		String expectedPattern = "[a-zA-Z_0-9]{10}";

		// WHEN
		stringType = new StringType(null);

		// THEN
		assertThat(stringType).hasFieldOrPropertyWithValue("pattern", expectedPattern);
	}

	@Test
	public void getMinRange_should_return_0() {

		// GIVEN
		String pattern = null;
		StringType stringType = new StringType(pattern);

		// WHEN
		Long result = stringType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_0() {

		// GIVEN
		String pattern = null;
		StringType stringType = new StringType(pattern);

		// WHEN
		Long result = stringType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}
}
