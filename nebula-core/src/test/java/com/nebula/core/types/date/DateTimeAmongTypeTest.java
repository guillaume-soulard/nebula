package com.nebula.core.types.date;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;

public class DateTimeAmongTypeTest {

	private ReadableInstant day1 = new DateTime(2017, 1, 1, 0, 0);

	@Test
	public void generateObject_should_return_a_non_null_object() {

		// GIVEN
		DateTimeAmongType dateType = new DateTimeAmongType(oneDay());

		// WHEN
		GeneratedObject result = dateType.generateObject(0l);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateObject_should_return_day1() {

		// GIVEN
		DateTimeAmongType dateType = new DateTimeAmongType(oneDay());

		// WHEN
		GeneratedObject result = dateType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isEqualTo(day1);
	}

	@Test
	public void generateObject_should_throw_Exception_when_index_is_out_of_range() {

		// GIVEN
		DateTimeAmongType dateType = new DateTimeAmongType(oneDay());

		// WHEN
		catchException(dateType).generateObject(10l);

		// THEN
		assertThat((Exception) (Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void newDateTimeAmongType_should_set_items_as_UnmodifiableList_when_other_List_implementation_is_passed() {

		// GIVEN
		DateTimeAmongType dateType = null;

		// WHEN
		dateType = new DateTimeAmongType(oneDayAsLinkedList());

		// THEN
		assertThat(dateType.getItems()).isInstanceOf(Collections.unmodifiableList(oneDayAsLinkedList()).getClass());
	}

	@Test
	public void newDateTimeAmongType_should_throw_exception_when_null_items_is_passed() {

		// GIVEN
		Exception exception = null;

		// WHEN
		try {
			new DateTimeAmongType(null);
		} catch (Exception e) {
			exception = e;
		}

		// THEN
		assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("items is null");
	}

	private List<ReadableInstant> oneDayAsLinkedList() {
		return new LinkedList<>(Arrays.asList(day1));
	}

	private List<ReadableInstant> oneDay() {
		return new ArrayList<>(Arrays.asList(day1));
	}
}
