package com.nebula.core.types.number;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;

public class DoubleTypeTest {

	@Test
	public void generateObject_should_return_0_dot_5_when_min_0_and_max_1_and_precision_1_for_index_5()
			throws NebulaException {

		// GIVEN
		Range<Double> range = new Range<Double>(0d, 1d);
		int precision = 1;
		DoubleType doubleType = new DoubleType(range, precision);

		// WHEN
		GeneratedObject result = doubleType.generateObject(5l);

		// THEN
		assertThat(result.getObject()).isEqualTo(0.5);
	}

	@Test
	public void generateObject_should_return_0_dot_05_when_min_0_and_max_10_and_precision_2_for_index_5()
			throws NebulaException {

		// GIVEN
		Range<Double> range = new Range<Double>(0d, 10d);
		int precision = 2;
		DoubleType doubleType = new DoubleType(range, precision);

		// WHEN
		GeneratedObject result = doubleType.generateObject(5l);

		// THEN
		assertThat(result.getObject()).isEqualTo(0.05);
	}

	@Test
	public void generateObject_should_throw_exception_when_index_is_out_of_range() throws NebulaException {

		// GIVEN
		Range<Double> range = new Range<Double>(0d, 1d);
		int precision = 1;
		DoubleType doubleType = new DoubleType(range, precision);

		// WHEN
		catchException(doubleType).generateObject(100l);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void getMinRange_should_return_0() throws NebulaException {

		// GIVEN
		Range<Double> range = new Range<Double>(0d, 1d);
		int precision = 1;
		DoubleType doubleType = new DoubleType(range, precision);

		// WHEN
		Long result = doubleType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_1() throws NebulaException {

		// GIVEN
		Range<Double> range = new Range<Double>(0d, 1d);
		int precision = 1;
		DoubleType doubleType = new DoubleType(range, precision);

		// WHEN
		Long result = doubleType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1l);
	}

	@Test
	public void generateObject_should_throw_exception_when_negative_index_is_passed() throws NebulaException {

		// GIVEN
		Range<Double> range = new Range<Double>(0d, 10d);
		int precision = 1;
		DoubleType doubleType = new DoubleType(range, precision);

		// WHEN
		catchException(doubleType).generateObject(-1l);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}
}
