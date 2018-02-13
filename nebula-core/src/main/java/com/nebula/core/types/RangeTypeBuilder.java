package com.nebula.core.types;

import com.nebula.core.Model;

public abstract class RangeTypeBuilder<S extends RangeTypeBuilder, T extends Comparable<T>> extends AbstractParsable<T> implements RandomTypeBuilder {

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
	protected abstract S getThis();

	public RangeTypeBuilder() {
		min = getDefaultMin();
		max = getDefaultMax();
	}

	public S withMin(T value) {
		min = value;
		return getThis();
	}

	public S withMax(T value) {
		max = value;
		return getThis();
	}

	public S withMin(String value) {
		minString = value;
		return getThis();
	}

	public S withMax(String value) {
		maxString = value;
		return getThis();
	}

	protected abstract T getDefaultMin();

	protected abstract T getDefaultMax();
}
