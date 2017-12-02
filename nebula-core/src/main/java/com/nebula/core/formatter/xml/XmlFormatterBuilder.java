package com.nebula.core.formatter.xml;

import com.nebula.core.Model;
import com.nebula.core.formatter.AbstractFormatterBuilder;
import com.nebula.core.formatter.Formatter;

public class XmlFormatterBuilder extends AbstractFormatterBuilder<XmlFormatterBuilder> {
    @Override
    public Formatter build(Model model) {
        return new XmlFormatter(buildValueFormatter(model));
    }

    @Override
    protected XmlFormatterBuilder getThis() {
        return this;
    }
}
