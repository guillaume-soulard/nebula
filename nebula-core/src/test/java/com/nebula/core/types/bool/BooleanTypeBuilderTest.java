package com.nebula.core.types.bool;

import com.nebula.core.ModelBuilder;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanTypeBuilderTest {

	@Test
	void build_should_return_a_new_instance_of_BooleanType() {

		// GIVEN
		BooleanTypeBuilder builder = new BooleanTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).isInstanceOf(BooleanType.class);
	}

}
