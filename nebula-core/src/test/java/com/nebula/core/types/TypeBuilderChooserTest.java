package com.nebula.core.types;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.nebula.core.types.number.NumberAmongTypeBuilder;
import com.nebula.core.types.number.NumberRangeTypeBuilder;

public class TypeBuilderChooserTest {

	@Test
	public void range_should_return_given_instance_of_range_buidler() {

		// GIVEN
		NumberRangeTypeBuilder rangeBuilder = new NumberRangeTypeBuilder();
		NumberAmongTypeBuilder amongBuilder = new NumberAmongTypeBuilder();
		TypeBuilderChooser<BigDecimal, NumberRangeTypeBuilder, NumberAmongTypeBuilder> chooser = new TypeBuilderChooser<>(
				rangeBuilder, amongBuilder);

		// WHEN
		NumberRangeTypeBuilder result = chooser.range();

		// THEN
		assertThat(result).isEqualTo(rangeBuilder);
	}

	@Test
	public void among_should_return_given_instance_of_among_buidler() {

		// GIVEN
		NumberRangeTypeBuilder rangeBuilder = new NumberRangeTypeBuilder();
		NumberAmongTypeBuilder amongBuilder = new NumberAmongTypeBuilder();
		TypeBuilderChooser<BigDecimal, NumberRangeTypeBuilder, NumberAmongTypeBuilder> chooser = new TypeBuilderChooser<>(
				rangeBuilder, amongBuilder);

		// WHEN
		NumberAmongTypeBuilder result = chooser.among();

		// THEN
		assertThat(result).isEqualTo(amongBuilder);
	}
}
