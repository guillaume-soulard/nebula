package com.nebula.core.formatter.sql;

import com.nebula.core.Model;
import com.nebula.core.formatter.AbstractFormatterBuilder;
import com.nebula.core.formatter.Formatter;

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
