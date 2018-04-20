package com.nebula.object;

import java.util.List;

public interface ObjectGenerator {
    <T> T generateNext(Class<T> clazz);
    <T> T generateAt(long index, Class<T> clazz);
    <T> List<T> generateListOf(int amount, Class<T> clazz);
    <T> List<T> generateLazyListOf(int amount, Class<T> clazz);
}
