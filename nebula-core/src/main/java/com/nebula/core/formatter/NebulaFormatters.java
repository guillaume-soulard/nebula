package com.nebula.core.formatter;

import com.nebula.core.formatter.csv.CsvFormatterBuilder;
import com.nebula.core.formatter.custom.CustomFormatterBuilder;
import com.nebula.core.formatter.json.JsonFormatterBuilder;
import com.nebula.core.formatter.sql.SqlFormatterBuilder;
import com.nebula.core.formatter.xml.XmlFormatterBuilder;

public final class NebulaFormatters {

    public static CsvFormatterBuilder csv() {
        return new CsvFormatterBuilder();
    }

    public static JsonFormatterBuilder json() {
        return new JsonFormatterBuilder();
    }

    public static CustomFormatterBuilder custom(Formatter formatter) {
        return new CustomFormatterBuilder(formatter);
    }

    public static SqlFormatterBuilder sql() { return new SqlFormatterBuilder(); }

    public static XmlFormatterBuilder xml() { return new XmlFormatterBuilder(); }
}
