package com.nebula.core.types;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;

import java.util.Collections;
import java.util.List;

public abstract class AbstractAmongType<T extends Comparable<T>> extends AbstractTypeWithIndexCheck implements Type {

	private List<T> items;

	protected AbstractAmongType(List<T> items) {
		if (items == null) {
			throw new NebulaException("items is null");
		}
		this.items = Collections.unmodifiableList(items);
	}

	@Override
	public Long getMinRange() {
		return 0L;
	}

	@Override
	public Long getMaxRange() {
		return Long.valueOf(Integer.toString(items.size() - 1));
	}

	public List<T> getItems() {
		return items;
	}

	@Override
	protected GeneratedObject generatedObjectAtIndex(Long index) {
        return GeneratedObject.of(items.get(Integer.valueOf(Long.toString(index))));
	}
}
