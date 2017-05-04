package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.types.date.DateTimeTypeBuilder;
import com.nebula.core.types.number.DoubleTypeBuilder;
import com.nebula.core.types.number.LongTypeBuilder;

public class NebulaTypesTest {

	@Test
	public void integer_should_return_a_new_instance_of_LongTypeBuilder() throws NebulaException {

		// GIVEN

		// WHEN
		LongTypeBuilder result = NebulaTypes.integer();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void decimal_should_return_a_new_instance_of_DoubleTypeBuilder() {

		// GIVEN

		// WHEN
		DoubleTypeBuilder result = NebulaTypes.decimal();

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
}
