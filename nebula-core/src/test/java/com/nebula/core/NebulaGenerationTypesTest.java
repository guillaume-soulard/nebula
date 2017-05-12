package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.types.bool.BooleanTypeBuilder;
import com.nebula.core.types.date.DateTimeTypeBuilderChooser;
import com.nebula.core.types.number.NumberTypeBuilderChooser;
import com.nebula.core.types.string.StringTypeBuilder;

public class NebulaGenerationTypesTest {

	@Test
	public void integer_should_return_a_new_instance_of_NumberTypeBuilderChooser() {

		// GIVEN

		// WHEN
		NumberTypeBuilderChooser result = NebulaGenerationTypes.number();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void dateTime_should_return_a_new_instance_of_DateTimeTypeBuilderChooser() {

		// GIVEN

		// WHEN
		DateTimeTypeBuilderChooser result = NebulaGenerationTypes.dateTime();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void string_should_return_a_new_instance_of_StringTypeBuilder() {

		// GIVEN

		// WHEN
		StringTypeBuilder result = NebulaGenerationTypes.string();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void bool_should_return_a_new_instance_of_BooleanTypeBuilder() {

		// GIVEN

		// WHEN
		BooleanTypeBuilder result = NebulaGenerationTypes.bool();

		// THEN
		assertThat(result).isNotNull();
	}
}
