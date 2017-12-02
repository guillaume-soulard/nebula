package com.nebula.core.types;

import com.nebula.core.Model;

public abstract class RangeTypeBuilder<T extends Comparable<T>> extends AbstractParsable<T> implements TypeBuilder {

	protected T min;
	protected T max;

	private String minString;
	private String maxString;

	@Override
	public Type build(Model model) {

		if (minString != null) {
			min = parseItem(model, minString);
		}

		if (maxString != null) {
			max = parseItem(model, maxString);
		}

		return buildImpl(model);
	}

	protected abstract Type buildImpl(Model model);

	public RangeTypeBuilder() {
		min = getDefaultMin();
		max = getDefaultMax();
	}

	public RangeTypeBuilder<T> withMin(T value) {
		min = value;
		return this;
	}

	public RangeTypeBuilder<T> withMax(T value) {
		max = value;
		return this;
	}

	public RangeTypeBuilder<T> withMin(String value) {
		minString = value;
		return this;
	}

	public RangeTypeBuilder<T> withMax(String value) {
		maxString = value;
		return this;
	}

	protected abstract T getDefaultMin();

	protected abstract T getDefaultMax();
}
