package com.nebula.formatter;

import com.nebula.core.GeneratedObject;

public interface Formatter {
    String formatHeader(GeneratedObject generatedObject);
    String formatGeneratedObject(GeneratedObject generatedObject);
    String formatFooter(GeneratedObject generatedObject);
}
