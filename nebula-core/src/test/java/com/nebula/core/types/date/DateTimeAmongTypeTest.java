package com.nebula.core.types.date;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

class DateTimeAmongTypeTest {

	private final ReadableInstant day1 = new DateTime(2017, 1, 1, 0, 0);

	@Test
    @DisplayName("generateObject should return a non null object")
	void generateObject_should_return_a_non_null_object() {

		// GIVEN
		DateTimeAmongType dateType = new DateTimeAmongType(oneDay());

		// WHEN
        GeneratedObject result = dateType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("generateObject should return day1")
	void generateObject_should_return_day1() {

		// GIVEN
		DateTimeAmongType dateType = new DateTimeAmongType(oneDay());

		// WHEN
        GeneratedObject result = dateType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result.getObject()).isEqualTo(day1);
	}

	@Test
    @DisplayName("generateObject should throw Exception when index is out of range")
	void generateObject_should_throw_Exception_when_index_is_out_of_range() {

		// GIVEN
		DateTimeAmongType dateType = new DateTimeAmongType(oneDay());

		// WHEN
        catchException(dateType).generateObject(Collections.emptyList(), 10L);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
    @DisplayName("newDateTimeAmongType should set items as UnmodifiableList when other List implementation is passed")
	void newDateTimeAmongType_should_set_items_as_UnmodifiableList_when_other_List_implementation_is_passed() {

		// GIVEN
		DateTimeAmongType dateType;

		// WHEN
		dateType = new DateTimeAmongType(oneDayAsLinkedList());

		// THEN
		assertThat(dateType.getItems()).isInstanceOf(Collections.unmodifiableList(oneDayAsLinkedList()).getClass());
	}

	@Test
    @DisplayName("newDateTimeAmongType should throw exception when null items is passed")
	void newDateTimeAmongType_should_throw_exception_when_null_items_is_passed() {

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
		return new LinkedList<>(Collections.singletonList(day1));
	}

	private List<ReadableInstant> oneDay() {
		return new ArrayList<>(Collections.singletonList(day1));
	}
}
