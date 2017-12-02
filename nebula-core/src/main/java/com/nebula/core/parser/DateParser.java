package com.nebula.core.parser;

import com.nebula.core.Model;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateParser {
    public ReadableInstant parse(Model model, String itemString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(model.getDateFormat());
        return formatter.parseDateTime(itemString);
    }
}
