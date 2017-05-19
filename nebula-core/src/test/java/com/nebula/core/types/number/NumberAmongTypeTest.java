package com.nebula.core.types.number;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;

public class NumberAmongTypeTest {

	@Test
	public void newNumberAmongType_should_set_items_in_fields() {

		// GIVEN
		NumberAmongType numberAmongType = null;
		List<BigDecimal> expectedItems = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);

		// WHEN
		numberAmongType = new NumberAmongType(expectedItems);

		// THEN
		assertThat(numberAmongType).hasFieldOrPropertyWithValue("items", expectedItems);
	}

	@Test
	public void generateObject_should_return_0() {

		// GIVEN
		List<BigDecimal> items = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
		NumberAmongType numberAmongType = new NumberAmongType(items);

		// WHEN
		GeneratedObject result = numberAmongType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isEqualTo(BigDecimal.ZERO);
	}

	@Test
	public void generateObject_should_return_1() {

		// GIVEN
		List<BigDecimal> items = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
		NumberAmongType numberAmongType = new NumberAmongType(items);

		// WHEN
		GeneratedObject result = numberAmongType.generateObject(1l);

		// THEN
		assertThat(result.getObject()).isEqualTo(BigDecimal.ONE);
	}

	@Test
	public void generateObject_should_throw_exception_when_object_index_is_out_of_range() {

		// GIVEN
		List<BigDecimal> items = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
		NumberAmongType numberAmongType = new NumberAmongType(items);

		// WHEN
		catchException(numberAmongType).generateObject(10l);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void generateObject_should_throw_exception_when_object_index_is_last_index_plus_one() {

		// GIVEN
		List<BigDecimal> items = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
		NumberAmongType numberAmongType = new NumberAmongType(items);

		// WHEN
		catchException(numberAmongType).generateObject(3l);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void generateObject_should_throw_exception_when_object_index_negative() {

		// GIVEN
		List<BigDecimal> items = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
		NumberAmongType numberAmongType = new NumberAmongType(items);

		// WHEN
		catchException(numberAmongType).generateObject(-1l);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void getMinRange_should_return_0() {

		// GIVEN
		List<BigDecimal> items = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
		NumberAmongType numberAmongType = new NumberAmongType(items);

		// WHEN
		Long result = numberAmongType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_0() {

		// GIVEN
		List<BigDecimal> items = Arrays.asList(BigDecimal.ZERO);
		NumberAmongType numberAmongType = new NumberAmongType(items);

		// WHEN
		Long result = numberAmongType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_1() {

		// GIVEN
		List<BigDecimal> items = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE);
		NumberAmongType numberAmongType = new NumberAmongType(items);

		// WHEN
		Long result = numberAmongType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1l);
	}
}
