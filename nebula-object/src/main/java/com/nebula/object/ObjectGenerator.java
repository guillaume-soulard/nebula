package com.nebula.object;

import java.util.List;

public interface ObjectGenerator {
    <T> T generate(Class<T> clazz);
    <T> List<T> generateListOf(int amount, Class<T> clazz);
}
