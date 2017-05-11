package com.nebula.core.types.date;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.types.Type;

public class DateTimeAmongTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_DateTimeAmongType() {

		// GIVEN
		DateTimeAmongTypeBuilder builder = new DateTimeAmongTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).isInstanceOf(DateTimeAmongType.class);
	}
}
