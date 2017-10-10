package com.nebula.formatter;

import com.nebula.Model;
import com.nebula.formatter.csv.CsvFormatter;

public interface FormatterBuilder {
    CsvFormatter build(Model model);
}
