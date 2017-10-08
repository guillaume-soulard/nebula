package com.nebula.formatter;

import com.nebula.formatter.csv.CsvFormatterBuilder;

public final class NebulaFormatters {

    public static CsvFormatterBuilder csv() {
        return new CsvFormatterBuilder();
    }
}
