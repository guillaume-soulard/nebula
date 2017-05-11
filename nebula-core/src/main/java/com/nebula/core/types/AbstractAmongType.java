package com.nebula.core.types;

import java.util.Collections;
import java.util.List;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;

public abstract class AbstractAmongType<T extends Comparable<T>> implements Type {

	private List<T> items;

	public AbstractAmongType(List<T> items) {
		if (items == null) {
			throw new NebulaException("items is null");
		}
		this.items = Collections.unmodifiableList(items);
	}

	@Override
	public void init(NebulaRandom nebulaRandom) {

	}

	@Override
	public GeneratedObject generateObject(Long objectIndex) {
		Integer index = Integer.valueOf(Long.toString(objectIndex));
		if (index < 0 || index > items.size() - 1) {
			throw new NebulaException("requested object is out of range");
		}
		return new GeneratedObject(items.get(index));
	}

	@Override
	public Long getMinRange() {
		return 0l;
	}

	@Override
	public Long getMaxRange() {
		return Long.valueOf(Integer.toString(items.size() - 1));
	}

	public List<T> getItems() {
		return items;
	}
}
