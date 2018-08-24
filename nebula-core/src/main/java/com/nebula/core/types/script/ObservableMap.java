package com.nebula.core.types.script;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ObservableMap<K, V> extends HashMap<K, V> {

    private Set<K> readKeys = new HashSet<>();

    public Set<K> getReadKeys() {
        return readKeys;
    }

    @Override
    public V get(Object key) {
        readKeys.add((K) key);
        return super.get(key);
    }
}
