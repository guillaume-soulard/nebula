package com.nebula.formatter.sql;

import com.nebula.Model;
import com.nebula.formatter.AbstractFormatterBuilder;
import com.nebula.formatter.Formatter;

public class SqlFormatterBuilder extends AbstractFormatterBuilder<SqlFormatterBuilder> {

    @Override
    public Formatter build(Model model) {
        return new SqlFormatter(buildValueFormatter(model), propertiesToExclude);
    }

    @Override
    protected SqlFormatterBuilder getThis() {
        return this;
    }
}
