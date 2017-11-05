package com.nebula.core.types;

import com.nebula.Model;

public abstract class AbstractParsable<T> {
    protected abstract T parseItem(Model model, String itemString);
}
