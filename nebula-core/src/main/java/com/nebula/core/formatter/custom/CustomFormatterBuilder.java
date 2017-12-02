package com.nebula.core.formatter.custom;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.formatter.AbstractFormatterBuilder;
import com.nebula.core.formatter.Formatter;

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
