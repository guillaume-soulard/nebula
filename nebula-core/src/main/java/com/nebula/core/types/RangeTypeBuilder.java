package com.nebula.core.types;

public abstract class RangeTypeBuilder<T extends Comparable<T>> implements TypeBuilder {

	protected T min;
	protected T max;

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

	protected abstract T getDefaultMin();

	protected abstract T getDefaultMax();
}
