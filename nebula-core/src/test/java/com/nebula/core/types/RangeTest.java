package com.nebula.core.types;

import com.nebula.core.NebulaException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class RangeTest {

	@Test
	public void newRange_should_return_a_new_instance_of_Range_with_both_min_and_max_value_specified() {
		// GIVEN
		Range<Integer> range;

		// WHEN
		range = new Range<>(10, 20);

		// THEN
		assertThat(range).hasFieldOrPropertyWithValue("min", 10);
		assertThat(range).hasFieldOrPropertyWithValue("max", 20);
	}

	@Test
	public void newRange_should_throw_exception_when_inverted_range_is_passed() {
		// GIVEN

		// WHEN
		try {
            new Range<>(20, 10);
			fail("Should throw an exception");
		} catch (Throwable e) {

			// THEN
			assertThat(e).isInstanceOf(NebulaException.class).hasMessage("range [20;10] is invalid");
		}
	}

	@Test
	public void newRange_should_throw_NebulaException_when_null_min_is_passed() {

		// GIVEN

		// WHEN
		try {
            new Range<>(null, 20);
		} catch (Throwable e) {

			// THEN
			assertThat(e).isInstanceOf(NebulaException.class).hasMessage("min is null");
		}
	}

	@Test
	public void newRange_should_throw_NebulaException_when_null_max_is_passed() {

		// GIVEN

		// WHEN
		try {
            new Range<>(10, null);
		} catch (Throwable e) {

			// THEN
			assertThat(e).isInstanceOf(NebulaException.class).hasMessage("max is null");
		}
	}
}
