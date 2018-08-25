package com.nebula.core;

import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.bool.BooleanTypeBuilder;
import com.nebula.core.types.constant.ConstantTypeBuilder;
import com.nebula.core.types.date.DateTimeTypeBuilderChooser;
import com.nebula.core.types.entity.EntityTypeBuilder;
import com.nebula.core.types.list.ListTypeBuilder;
import com.nebula.core.types.number.NumberTypeBuilderChooser;
import com.nebula.core.types.string.StringTypeBuilder;
import org.joda.time.DateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class NebulaGenerationTypesTest {

	@Test
    @DisplayName("integer should return a new instance of NumberTypeBuilderChooser")
	void integer_should_return_a_new_instance_of_NumberTypeBuilderChooser() {

		// GIVEN

		// WHEN
		NumberTypeBuilderChooser result = NebulaTypes.number();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("dateTime should return a new instance of DateTimeTypeBuilderChooser")
	void dateTime_should_return_a_new_instance_of_DateTimeTypeBuilderChooser() {

		// GIVEN

		// WHEN
		DateTimeTypeBuilderChooser result = NebulaTypes.dateTime();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("string should return a new instance of StringTypeBuilder")
	void string_should_return_a_new_instance_of_StringTypeBuilder() {

		// GIVEN

		// WHEN
		StringTypeBuilder result = NebulaTypes.string();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("bool should return a new instance of BooleanTypeBuilder")
	void bool_should_return_a_new_instance_of_BooleanTypeBuilder() {

		// GIVEN

		// WHEN
		BooleanTypeBuilder result = NebulaTypes.bool();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("list should return a new instance of ListTypeBuilder")
	void list_should_return_a_new_instance_of_ListTypeBuilder() {

		// GIVEN

		// WHEN
		ListTypeBuilder result = NebulaTypes.list();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("constant should return a new instance of ConstantTypeBuilder with String")
	void constant_should_return_a_new_instance_of_ConstantTypeBuilder_with_String() {

		// GIVEN
		String value = "test";

		// WHEN
		ConstantTypeBuilder result = NebulaTypes.constant(value);

		// THEN
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("value", value);
	}

	@Test
    @DisplayName("constant should return a new instance of ConstantTypeBuilder with Date")
	void constant_should_return_a_new_instance_of_ConstantTypeBuilder_with_Date() {

		// GIVEN
		DateTime value = new DateTime(2017, 1, 1, 0, 0);

		// WHEN
		ConstantTypeBuilder result = NebulaTypes.constant(value);

		// THEN
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("value", value);
	}

	@Test
    @DisplayName("constant should return a new instance of ConstantTypeBuilder with BigDecimal")
	void constant_should_return_a_new_instance_of_ConstantTypeBuilder_with_BigDecimal() {

		// GIVEN
		BigDecimal value = BigDecimal.ZERO;

		// WHEN
		ConstantTypeBuilder result = NebulaTypes.constant(value);

		// THEN
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("value", value);
	}

	@Test
    @DisplayName("constant should return a new instance of ConstantTypeBuilder with Boolean")
	void constant_should_return_a_new_instance_of_ConstantTypeBuilder_with_Boolean() {

		// GIVEN

		// WHEN
		ConstantTypeBuilder result = NebulaTypes.constant(false);

		// THEN
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("value", false);
	}

	@Test
    @DisplayName("entity should return a new instance of EntityTypeBuilder")
	void entity_should_return_a_new_instance_of_EntityTypeBuilder() {

		// GIVEN
		String entityName = "test";

		// WHEN
		EntityTypeBuilder result = NebulaTypes.entity(entityName);

		// THEN
		assertThat(result).isNotNull();
	}
}
