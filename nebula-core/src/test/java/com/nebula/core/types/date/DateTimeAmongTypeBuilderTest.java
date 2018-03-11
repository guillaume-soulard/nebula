package com.nebula.core.types.date;

import com.nebula.core.ModelBuilder;
import com.nebula.core.types.Type;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeAmongTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_DateTimeAmongType() {

		// GIVEN
		DateTimeAmongTypeBuilder builder = new DateTimeAmongTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).isInstanceOf(DateTimeAmongType.class);
	}
}
