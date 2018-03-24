package com.nebula.core.types.constant;

import com.nebula.core.ModelBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;
import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ConstantTypeBuilderTest {

	@Test
	public void new_ConstantTypeBuilder_should_set_string_as_value() {

		// GIVEN
		String value = "test";
		TypeBuilder builder;

		// WHEN
		builder = new ConstantTypeBuilder(value);

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("value", value);
	}

	@Test
	public void new_ConstantTypeBuilder_should_set_BigDecimal_as_value() {

		// GIVEN
		BigDecimal value = BigDecimal.ZERO;
		TypeBuilder builder;

		// WHEN
		builder = new ConstantTypeBuilder(value);

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("value", value);
	}

	@Test
	public void new_ConstantTypeBuilder_should_set_Date_as_value() {

		// GIVEN
		DateTime value = new DateTime(2017, 1, 1, 0, 0);
		TypeBuilder builder;

		// WHEN
		builder = new ConstantTypeBuilder(value);

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("value", value);
	}

	@Test
	public void new_ConstantTypeBuilder_should_set_Boolean_as_value() {

		// GIVEN
		Boolean value = Boolean.FALSE;
		TypeBuilder builder;

		// WHEN
		builder = new ConstantTypeBuilder(false);

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("value", false);
	}

	@Test
	public void build_should_return_new_instance_of_ConstantType_with_given_value() {

		// GIVEN
		String value = "test";
		ConstantTypeBuilder builder = new ConstantTypeBuilder(value);

		// WHEN
		Type result = builder.build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).isInstanceOf(ConstantType.class);
	}
}
