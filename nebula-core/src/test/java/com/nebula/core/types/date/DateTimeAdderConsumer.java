package com.nebula.core.types.date;

import com.nebula.core.types.date.strategy.DateTimeStrategy;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeAdderConsumer implements Consumer<DateTimeStrategy> {

	private final Class<? extends DateTimeStrategy> clazz;

	private DateTimeAdderConsumer(Class<? extends DateTimeStrategy> clazz) {
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