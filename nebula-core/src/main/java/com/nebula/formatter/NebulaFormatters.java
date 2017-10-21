package com.nebula.formatter;

import com.nebula.formatter.csv.CsvFormatterBuilder;
import com.nebula.formatter.json.JsonFormatterBuilder;

public final class NebulaFormatters {

    public static CsvFormatterBuilder csv() {
        return new CsvFormatterBuilder();
    }

    public static JsonFormatterBuilder json() {
        return new JsonFormatterBuilder();
    }
}
