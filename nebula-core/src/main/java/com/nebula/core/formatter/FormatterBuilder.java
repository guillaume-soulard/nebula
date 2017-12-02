package com.nebula.core.formatter;

import com.nebula.core.Model;

public interface FormatterBuilder {
    Formatter build(Model model);
}
