package com.nebula.core.types;

public class TypeBuilderChooser<
		SR extends RangeTypeBuilder,
		SA extends AmongTypeBuilder,
		T extends Comparable<T>,
		R extends RangeTypeBuilder<SR, T>,
		A extends AmongTypeBuilder<SA, T>> {

	private final R rangeBuilder;
	private final A amongBuilder;

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
