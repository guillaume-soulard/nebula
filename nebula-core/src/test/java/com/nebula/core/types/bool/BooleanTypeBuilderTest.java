package com.nebula.core.types.bool;

import com.nebula.ModelBuilder;
import com.nebula.core.types.Type;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BooleanTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_BooleanType() {

		// GIVEN
		BooleanTypeBuilder builder = new BooleanTypeBuilder();

		// WHEN
		Type result = builder.build(new ModelBuilder().build());

		// THEN
		assertThat(result).isInstanceOf(BooleanType.class);
	}

}
