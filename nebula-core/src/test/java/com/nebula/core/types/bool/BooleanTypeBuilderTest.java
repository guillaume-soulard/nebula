package com.nebula.core.types.bool;

import com.nebula.core.ModelBuilder;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanTypeBuilderTest {

	@Test
    @DisplayName("build should return a new instance of BooleanType")
	void build_should_return_a_new_instance_of_BooleanType() {

		// GIVEN
		BooleanTypeBuilder builder = new BooleanTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).isInstanceOf(BooleanType.class);
	}

}
