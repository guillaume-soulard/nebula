package com.nebula.core.types;

import com.nebula.core.Model;

public abstract class AbstractParsable<T> {
    protected abstract T parseItem(Model model, String itemString);
}
