package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.types.date.DateTimeTypeBuilder;
import com.nebula.core.types.number.NumberTypeBuilder;
import com.nebula.core.types.string.StringTypeBuilder;

public class NebulaTypesTest {

	@Test
	public void integer_should_return_a_new_instance_of_LongTypeBuilder() throws NebulaException {

		// GIVEN

		// WHEN
		NumberTypeBuilder result = NebulaTypes.number();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void dateTime_should_return_a_new_instance_of_DateTimeTypeBuilder() {

		// GIVEN

		// WHEN
		DateTimeTypeBuilder result = NebulaTypes.dateTime();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void string_should_return_a_new_instance_of_StringTypeBuilder() {

		// GIVEN

		// WHEN
		StringTypeBuilder result = NebulaTypes.string();

		// THEN
		assertThat(result).isNotNull();
	}
}
