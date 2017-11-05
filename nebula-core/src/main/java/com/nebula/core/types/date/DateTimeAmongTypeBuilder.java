package com.nebula.core.types.date;

import com.nebula.Model;
import com.nebula.parser.DateParser;
import org.joda.time.ReadableInstant;

import com.nebula.core.types.AmongTypeBuilder;
import com.nebula.core.types.Type;

public class DateTimeAmongTypeBuilder extends AmongTypeBuilder<ReadableInstant> {

	private DateParser dateParser = new DateParser();

	@Override
	public Type buildImpl(Model model) {
		return new DateTimeAmongType(items);
	}

	@Override
	protected ReadableInstant parseItem(Model model, String itemString) {
		return dateParser.parse(model, itemString);
	}
}
