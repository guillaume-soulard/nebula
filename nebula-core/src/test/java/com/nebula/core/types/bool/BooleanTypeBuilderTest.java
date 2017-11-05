package com.nebula.core.types.bool;

import static com.nebula.Nebula.newModel;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.types.Type;

public class BooleanTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_BooleanType() {

		// GIVEN
		BooleanTypeBuilder builder = new BooleanTypeBuilder();

		// WHEN
		Type result = builder.build(newModel());

		// THEN
		assertThat(result).isInstanceOf(BooleanType.class);
	}

}
