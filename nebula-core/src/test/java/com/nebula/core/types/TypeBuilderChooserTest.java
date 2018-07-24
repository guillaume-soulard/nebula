package com.nebula.core.types;

import com.nebula.core.types.number.NumberAmongTypeBuilder;
import com.nebula.core.types.number.NumberRangeTypeBuilder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class TypeBuilderChooserTest {

	@Test
	void range_should_return_given_instance_of_range_builder() {

		// GIVEN
		NumberRangeTypeBuilder rangeBuilder = new NumberRangeTypeBuilder();
		NumberAmongTypeBuilder amongBuilder = new NumberAmongTypeBuilder();
		TypeBuilderChooser<NumberRangeTypeBuilder, NumberAmongTypeBuilder, BigDecimal, NumberRangeTypeBuilder, NumberAmongTypeBuilder> chooser = new TypeBuilderChooser<>(
				rangeBuilder, amongBuilder);

		// WHEN
		NumberRangeTypeBuilder result = chooser.range();

		// THEN
		assertThat(result).isEqualTo(rangeBuilder);
	}

	@Test
	void among_should_return_given_instance_of_among_builder() {

		// GIVEN
		NumberRangeTypeBuilder rangeBuilder = new NumberRangeTypeBuilder();
		NumberAmongTypeBuilder amongBuilder = new NumberAmongTypeBuilder();
		TypeBuilderChooser<NumberRangeTypeBuilder, NumberAmongTypeBuilder, BigDecimal, NumberRangeTypeBuilder, NumberAmongTypeBuilder> chooser = new TypeBuilderChooser<>(
				rangeBuilder, amongBuilder);

		// WHEN
		NumberAmongTypeBuilder result = chooser.among();

		// THEN
		assertThat(result).isEqualTo(amongBuilder);
	}
}
