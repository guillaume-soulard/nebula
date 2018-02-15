package com.nebula.core.formatter;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class ValueFormatter {

    private DecimalFormat numberFormat;
    private DateTimeFormatter dateTimeFormat;

    public ValueFormatter(String dateFormat, Character numberDecimalSeparator, Character numberThousandSeparator) {

        dateTimeFormat = DateTimeFormat.forPattern(dateFormat);

        numberFormat = (DecimalFormat) NumberFormat.getInstance();
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(numberDecimalSeparator);
        if (numberThousandSeparator != null) {
            decimalFormatSymbols.setGroupingSeparator(numberThousandSeparator);
        } else {
            numberFormat.setGroupingUsed(false);
        }
        numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    public String formatValue(Object valueToFormat) {

        if (valueToFormat == null) {
            return null;
        } else if (valueToFormat instanceof DateTime) {
            return dateTimeFormat.print((ReadableInstant) valueToFormat);
        } else if (valueToFormat instanceof BigDecimal) {

            return numberFormat.format(((BigDecimal)valueToFormat).doubleValue());
        } else {
            return valueToFormat.toString();
        }
    }
}
