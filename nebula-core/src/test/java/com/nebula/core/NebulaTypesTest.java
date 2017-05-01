package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.types.number.DoubleTypeBuilder;
import com.nebula.core.types.number.LongTypeBuilder;

public class NebulaTypesTest {

	@Test
	public void integer_should_return_a_new_instance_of_LongTypeBuilder() throws NebulaException {

		// GIVEN

		// WHEN
		LongTypeBuilder result = NebulaTypes.integer();

		// THEN
		assertThat(result).isInstanceOf(LongTypeBuilder.class);
	}

	@Test
	public void decimal_should_return_a_new_instance_of_DoubleTypeBuilder() {

		// GIVEN

		// WHEN
		DoubleTypeBuilder result = NebulaTypes.decimal();

		// THEN
		assertThat(result).isInstanceOf(DoubleTypeBuilder.class);
	}
}
