package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

import com.nebula.core.types.bool.BooleanTypeBuilder;
import com.nebula.core.types.constant.ConstantTypeBuilder;
import com.nebula.core.types.date.DateTimeTypeBuilderChooser;
import com.nebula.core.types.entity.EntityTypeBuilder;
import com.nebula.core.types.list.ListTypeBuilder;
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

	@Test
	public void list_should_return_a_new_instance_of_ListTypeBuilder() {

		// GIVEN

		// WHEN
		ListTypeBuilder result = NebulaGenerationTypes.list();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void constant_should_return_a_new_instance_of_ConstantTypeBuilder_with_String() {

		// GIVEN
		String value = "test";

		// WHEN
		ConstantTypeBuilder result = NebulaGenerationTypes.constant(value);

		// THEN
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("value", value);
	}

	@Test
	public void constant_should_return_a_new_instance_of_ConstantTypeBuilder_with_Date() {

		// GIVEN
		DateTime value = new DateTime(2017, 1, 1, 0, 0);

		// WHEN
		ConstantTypeBuilder result = NebulaGenerationTypes.constant(value);

		// THEN
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("value", value);
	}

	@Test
	public void constant_should_return_a_new_instance_of_ConstantTypeBuilder_with_BigDecimal() {

		// GIVEN
		BigDecimal value = BigDecimal.ZERO;

		// WHEN
		ConstantTypeBuilder result = NebulaGenerationTypes.constant(value);

		// THEN
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("value", value);
	}

	@Test
	public void constant_should_return_a_new_instance_of_ConstantTypeBuilder_with_Boolean() {

		// GIVEN
		Boolean value = Boolean.FALSE;

		// WHEN
		ConstantTypeBuilder result = NebulaGenerationTypes.constant(value);

		// THEN
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("value", value);
	}

	@Test
	public void entity_should_return_a_new_instance_of_EntityTypeBuilder() {

		// GIVEN
		String entityName = "test";

		// WHEN
		EntityTypeBuilder result = NebulaGenerationTypes.entity(entityName);

		// THEN
		assertThat(result).isNotNull();
	}
}
