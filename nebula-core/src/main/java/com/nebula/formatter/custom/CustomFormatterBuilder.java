package com.nebula.formatter.custom;

import com.nebula.Model;
import com.nebula.core.NebulaException;
import com.nebula.formatter.AbstractFormatterBuilder;
import com.nebula.formatter.Formatter;

public class CustomFormatterBuilder extends AbstractFormatterBuilder<CustomFormatterBuilder> {

    private Formatter formatter;

    public CustomFormatterBuilder(Formatter formatter) {
        if  (formatter == null) {
            throw new NebulaException("formatter is null");
        }
        this.formatter = formatter;
    }

    @Override
    public Formatter build(Model model) {
        return formatter;
    }

    @Override
    protected CustomFormatterBuilder getThis() {
        return this;
    }
}
