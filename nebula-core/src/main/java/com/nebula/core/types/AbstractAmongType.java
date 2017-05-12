package com.nebula.core.types;

import java.util.Collections;
import java.util.List;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;

public abstract class AbstractAmongType<T extends Comparable<T>> extends AbstractTypeWithIndexCheck implements Type {

	private List<T> items;

	public AbstractAmongType(List<T> items) {
		if (items == null) {
			throw new NebulaException("items is null");
		}
		this.items = Collections.unmodifiableList(items);
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

	@Override
	protected GeneratedObject generatedObjectAtIndex(Long index) {
		return new GeneratedObject(items.get(Integer.valueOf(Long.toString(index))));
	}
}
