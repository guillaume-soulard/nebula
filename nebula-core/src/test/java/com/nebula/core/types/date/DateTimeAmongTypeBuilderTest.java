package com.nebula.core.types.date;

import com.nebula.core.ModelBuilder;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DateTimeAmongTypeBuilderTest {

	@Test
	void build_should_return_a_new_instance_of_DateTimeAmongType() {

		// GIVEN
		DateTimeAmongTypeBuilder builder = new DateTimeAmongTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).isInstanceOf(DateTimeAmongType.class);
	}
}
