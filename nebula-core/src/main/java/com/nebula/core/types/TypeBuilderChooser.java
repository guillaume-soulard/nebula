package com.nebula.core.types;

public class TypeBuilderChooser<T extends Comparable<T>, R extends RangeTypeBuilder<T>, A extends AmongTypeBuilder<T>> {

	private R rangeBuilder;
	private A amongBuilder;

	public TypeBuilderChooser(R rangeBuilder, A amongBuilder) {
		this.rangeBuilder = rangeBuilder;
		this.amongBuilder = amongBuilder;
	}

	public R range() {
		return rangeBuilder;
	}

	public A among() {
		return amongBuilder;
	}
}
