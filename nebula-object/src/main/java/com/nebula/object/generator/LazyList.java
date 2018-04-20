package com.nebula.object.generator;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LazyList<T> implements List<T> {
    private final int listSize;
    private final ModelBasedObjectGenerator modelBasedObjectGenerator;
    private Class<T> clazz;

    public LazyList(int listSize, ModelBasedObjectGenerator modelBasedObjectGenerator, Class<T> clazz) {
        this.listSize = listSize;
        this.modelBasedObjectGenerator = modelBasedObjectGenerator;
        this.clazz = clazz;
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize <= 0;
    }

    @Override
    public boolean contains(Object o) {
        return stream().anyMatch(item -> item == o || (item != null && o != null && item.equals(o)));
    }

    @Override
    public Iterator<T> iterator() {
        return new LazyListIterator(this);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(item -> contains(item));
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {

        if (index >= listSize) {

            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + listSize);
        }

        return modelBasedObjectGenerator.generateAt(index, clazz);
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        AtomicInteger index = new AtomicInteger();
        int foundIndex = -1;

        for (T item : this) {

            if (item == o || (item != null && item.equals(o))) {

                foundIndex = index.get();
                break;
            }
            index.incrementAndGet();
        }

        return foundIndex;
    }

    @Override
    public int lastIndexOf(Object o) {
        AtomicInteger index = new AtomicInteger();
        int foundIndex = -1;

        for (T item : this) {

            if (item == o || (item != null && item.equals(o))) {

                foundIndex = index.get();
            }
            index.incrementAndGet();
        }

        return foundIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex >= toIndex) {

            throw new IllegalArgumentException("fromIndex should be strictly lower than toIndex");
        }

        List<T> subList = new ArrayList<>();

        for (int index = fromIndex; index < toIndex; index++) {

            subList.add(get(index));
        }

        return subList;
    }
}
