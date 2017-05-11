package com.nebula.core.types.date;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Consumer;

import com.nebula.core.types.date.strategy.DateTimeStrategy;

public class DateTimeAdderConsumer implements Consumer<DateTimeStrategy> {

	private Class<? extends DateTimeStrategy> clazz;

	public DateTimeAdderConsumer(Class<? extends DateTimeStrategy> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void accept(DateTimeStrategy t) {
		assertThat(t).isInstanceOf(clazz);
	}

	public static Consumer<DateTimeStrategy> isInstanceOf(Class<? extends DateTimeStrategy> expectedClass) {
		return new DateTimeAdderConsumer(expectedClass);
	}
}