package com.nebula.core.types;

import com.nebula.core.NebulaException;

public class Range<T extends Comparable<T>> {

	private T min;

	private T max;

	public Range(T min, T max) throws NebulaException {
		checkMin(min);
		checkMax(max);
		checkInterval(min, max);
		this.min = min;
		this.max = max;
	}

	public void checkInterval(T min, T max) throws NebulaException {
		if (min.compareTo(max) > 0) {

			throw new NebulaException("range [" + min + ";" + max + "] is invalid");
		}
	}

	public void checkMax(T max) throws NebulaException {
		if (max == null) {

			throw new NebulaException("max is null");
		}
	}

	public void checkMin(T min) throws NebulaException {
		if (min == null) {

			throw new NebulaException("min is null");
		}
	}

	public T getMin() {
		return min;
	}

	public T getMax() {
		return max;
	}
}
