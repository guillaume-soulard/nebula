package com.nebula.core.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nebula.core.NebulaException;

public abstract class AmongTypeBuilder<T extends Comparable<T>> implements TypeBuilder {

	protected List<T> items = new ArrayList<T>();

	public AmongTypeBuilder<T> items(@SuppressWarnings("unchecked") T... items) {
		if (items == null) {
			throw new NebulaException("items is null");
		}
		if (items.length == 0) {
			throw new NebulaException("items is empty");
		}
		this.items.addAll(Arrays.asList(items));
		return this;
	}
}
