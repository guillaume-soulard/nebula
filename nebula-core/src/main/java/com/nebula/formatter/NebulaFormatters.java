package com.nebula.formatter;

import com.nebula.formatter.csv.CsvFormatterBuilder;
import com.nebula.formatter.custom.CustomFormatterBuilder;
import com.nebula.formatter.json.JsonFormatterBuilder;
import com.nebula.formatter.sql.SqlFormatterBuilder;
import com.nebula.formatter.xml.XmlFormatterBuilder;

public final class NebulaFormatters {

    public static CsvFormatterBuilder csv() {
        return new CsvFormatterBuilder();
    }

    public static JsonFormatterBuilder json() {
        return new JsonFormatterBuilder();
    }

    public static CustomFormatterBuilder custom() {
        return new CustomFormatterBuilder();
    }

    public static SqlFormatterBuilder sql() { return new SqlFormatterBuilder(); }

    public static XmlFormatterBuilder xml() { return new XmlFormatterBuilder(); }
}
