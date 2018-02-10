package com.nebula.core.types.list;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.JavaTypeName;
import com.nebula.core.types.Type;

import java.util.ArrayList;
import java.util.List;

class ListTypeOfType extends AbstractListType {

	private Type type;

	ListTypeOfType(int minSize, int maxSize, Generator generator, Type type) {
		super(minSize, maxSize, generator);
		this.type = type;
	}

	@Override
	public void init(GenerationContext context) {
		this.context = context;
	}

	@Override
	protected List<Object> generateList(int listSize, NebulaRandom nebulaRandom) {
		List<Object> list = new ArrayList<>(listSize);
		fillList(listSize, nebulaRandom, list);
		return list;
	}

	@Override
	protected Object getItem(NebulaRandom nebulaRandom) {
		return generator.generate(type);
	}

	@Override
	public Long getMinRange() {
		return 0l;
	}

	@Override
	public Long getMaxRange() {
		return (long) (maxSize - minSize);
	}

	@Override
	public JavaType getJavaType() {
		return new JavaType(JavaTypeName.ARRAY, type.getJavaType());
	}
}
