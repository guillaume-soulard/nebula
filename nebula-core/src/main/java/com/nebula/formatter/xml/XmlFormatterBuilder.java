package com.nebula.formatter.xml;

import com.nebula.Model;
import com.nebula.formatter.AbstractFormatterBuilder;
import com.nebula.formatter.Formatter;

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
