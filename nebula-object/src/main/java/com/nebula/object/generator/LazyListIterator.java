package com.nebula.object.generator;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class LazyListIterator<T> implements Iterator<T> {

    private AtomicInteger index;
    private LazyList<T> lazyList;

    LazyListIterator(LazyList<T> lazyList) {
        this.lazyList = lazyList;
        index = new AtomicInteger(0);
    }

    @Override
    public boolean hasNext() {
        return index.get() < lazyList.size();
    }

    @Override
    public T next() {
        return lazyList.get(index.getAndIncrement());
    }
}
