package com.nebula.core.parser;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class NumberParser {

    public BigDecimal parse(Model model, String string) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(model.getNumberThousandSeparator());
        symbols.setDecimalSeparator(model.getNumberDecimalSeparator());
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        try {
            return (BigDecimal) decimalFormat.parse(string);
        } catch (ParseException e) {
            throw new NebulaException(e.getMessage());
        }
    }
}
