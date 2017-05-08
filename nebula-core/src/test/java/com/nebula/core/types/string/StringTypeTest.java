package com.nebula.core.types.string;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.generators.NebulaRandom;

public class StringTypeTest {

	@Test
	public void generateObject_should_return_a_non_null_GeneratedObject() {

		// GIVEN
		StringType stringType = new StringType(5, 25, "");
		NebulaRandom nebulaRandom = mock(NebulaRandom.class);
		stringType.init(nebulaRandom);

		// WHEN
		GeneratedObject result = stringType.generateObject(0l);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateObject_should_return_GeneratedObject_with_non_null_value() {

		// GIVEN
		StringType stringType = new StringType(5, 25, "");
		NebulaRandom nebulaRandom = mock(NebulaRandom.class);
		stringType.init(nebulaRandom);

		// WHEN
		GeneratedObject result = stringType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isNotNull();
	}

	@Test
	public void generateObject_should_return_GeneratedObject_with_value_matching_pattern() {

		// GIVEN
		StringType stringType = new StringType(5, 25, "tes");
		NebulaRandom nebulaRandom = mock(NebulaRandom.class);
		stringType.init(nebulaRandom);
		when(nebulaRandom.randomBetween(anyLong(), anyLong())).thenReturn(4l).thenReturn(0l, 1l, 2l, 0l);

		// WHEN
		GeneratedObject result = stringType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isInstanceOf(String.class).isEqualTo("test");
	}

	@Test
	public void generateObject_should_generate_defferent_strings_with_index_0_and_1() {

		// GIVEN
		StringType stringType = new StringType(5, 25, "test");
		stringType.init(new NebulaRandom(1l));
		GeneratedObject resultIndex0 = stringType.generateObject(0l);

		// WHEN
		GeneratedObject resultIndex1 = stringType.generateObject(1l);

		// THEN
		assertThat(resultIndex0.getObject()).isNotEqualTo(resultIndex1.getObject());
	}

	@Test
	public void newStringType_should_set_min_length_max_length_and_allowed_chars() {

		// GIVEN
		int minLength = 5;
		int maxLength = 25;
		String allowedChars = "azertyuiopqsdfghjklmwxcvbn";
		StringType stringType = null;

		// WHEN
		stringType = new StringType(minLength, maxLength, allowedChars);

		// THEN
		assertThat(stringType).hasFieldOrPropertyWithValue("minLength", minLength)
				.hasFieldOrPropertyWithValue("maxLength", maxLength)
				.hasFieldOrPropertyWithValue("allowedChars", allowedChars);
	}

	@Test
	public void getMinRange_should_return_1() {

		// GIVEN
		int minLength = 5;
		int maxLength = 25;
		String allowedChars = "";
		StringType stringType = new StringType(minLength, maxLength, allowedChars);

		// WHEN
		Long result = stringType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(5);
	}

	@Test
	public void getMinRange_should_return_10() {

		// GIVEN
		int minLength = 10;
		int maxLength = 25;
		String allowedChars = "";
		StringType stringType = new StringType(minLength, maxLength, allowedChars);

		// WHEN
		Long result = stringType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(10);
	}

	@Test
	public void getMaxRange_should_return_25() {

		// GIVEN
		int minLength = 5;
		int maxLength = 25;
		String allowedChars = "";
		StringType stringType = new StringType(minLength, maxLength, allowedChars);

		// WHEN
		Long result = stringType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(25);
	}

	@Test
	public void getMaxRange_should_return_100() {

		// GIVEN
		int minLength = 5;
		int maxLength = 100;
		String allowedChars = "";
		StringType stringType = new StringType(minLength, maxLength, allowedChars);

		// WHEN
		Long result = stringType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(100);
	}

	@Test
	public void init_should_set_NebulaRandom_in_fields() {

		// GIVEN
		int minLength = 5;
		int maxLength = 25;
		String allowedChars = "";
		StringType stringType = new StringType(minLength, maxLength, allowedChars);
		NebulaRandom nebulaRandom = new NebulaRandom(1l);

		// WHEN
		stringType.init(nebulaRandom);

		// THEN
		assertThat(stringType).hasFieldOrPropertyWithValue("nebulaRandom", nebulaRandom);
	}
}
