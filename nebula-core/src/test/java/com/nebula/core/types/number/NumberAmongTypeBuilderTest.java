package com.nebula.core.types.number;

import com.nebula.core.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

class NumberAmongTypeBuilderTest {

	@Test
	void build_should_return_a_new_instance_of_NumberAmongType_with_defaults() {

		// GIVEN
		NumberAmongTypeBuilder builder = new NumberAmongTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).isInstanceOf(NumberAmongType.class);
	}

	@Test
	void items_should_return_the_builder_with_given_item_as_collection() {

		// GIVEN
		NumberAmongTypeBuilder builder = new NumberAmongTypeBuilder();
		BigDecimal item = BigDecimal.ZERO;

		// WHEN
		NumberAmongTypeBuilder result = builder.items(item);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("items", Collections.singletonList(item));
	}

	@Test
	void items_should_return_the_builder_with_given_items_as_collection() {

		// GIVEN
		NumberAmongTypeBuilder builder = new NumberAmongTypeBuilder();
		BigDecimal item1 = BigDecimal.ZERO;
		BigDecimal item2 = BigDecimal.ONE;

		// WHEN
		NumberAmongTypeBuilder result = builder.items(item1, item2);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("items", Arrays.asList(item1, item2));
	}

	@Test
	void items_should_return_the_builder_with_null_as_collection() {

		// GIVEN
		NumberAmongTypeBuilder builder = new NumberAmongTypeBuilder();

		// WHEN
		NumberAmongTypeBuilder result = builder.items(new BigDecimal[] { null });

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("items", Arrays.asList(new BigDecimal[] { null }));
	}

	@Test
	void items_should_throw_exception_when_null_items_is_passed_for_big_decimal() {

		// GIVEN
		NumberAmongTypeBuilder builder = new NumberAmongTypeBuilder();
		Exception exception = null;

		// WHEN
		try {
			builder.items((BigDecimal[]) null);
		} catch (Exception e) {
			exception = e;
		}

		// THEN
		assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("items is null");
	}

	@Test
	void items_should_throw_exception_when_null_items_is_passed_for_string() {

		// GIVEN
		NumberAmongTypeBuilder builder = new NumberAmongTypeBuilder();

		// WHEN
		catchException(builder).items((String[]) null);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("items is null");
	}

	@Test
	void items_should_throw_exception_when_empty_items_is_passed() {

		// GIVEN
		NumberAmongTypeBuilder builder = new NumberAmongTypeBuilder();
		Exception exception = null;

		// WHEN
		try {
			catchException(builder).items(new BigDecimal[] {});
		} catch (Exception e) {
			exception = e;
		}

		// THEN
		assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("items is empty");
	}

	@Test
	void build_should_NumberAmongType_with_items() {

		// GIVEN
		NumberAmongTypeBuilder builder = new NumberAmongTypeBuilder();
		BigDecimal item1 = BigDecimal.ZERO;
		BigDecimal item2 = BigDecimal.ONE;

		// WHEN
		Type result = builder.items(item1, item2).build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("items", Arrays.asList(item1, item2));
	}


}
