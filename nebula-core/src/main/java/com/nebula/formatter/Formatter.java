package com.nebula.formatter;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;

public interface Formatter {
    String formatHeader(Entity entity);
    String formatGeneratedObject(GeneratedObject generatedObject);
    String formatFooter(Entity entity);
}
