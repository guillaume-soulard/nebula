package com.nebula.core.types.list;

import com.nebula.core.Model;
import com.nebula.core.GeneratedObject;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.JavaTypeName;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Type;
import com.nebula.core.types.constant.ConstantTypeBuilder;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.*;

public class ListTypeAmongItems extends AbstractListType {

	private final GeneratedObject[] items;
	private Type numberRange;
	private final Model model;

	ListTypeAmongItems(Model model, int minSize, int maxSize, Generator generator, ConstantTypeBuilder[] builders) {
		super(minSize, maxSize, generator);
		this.items = new GeneratedObject[builders.length];
		this.model = model;

		for (int i = 0; i < builders.length; i++) {
			items[i] = builders[i].build(model).generateObject(0L);
		}
	}

	@Override
	protected List<Object> generateList(int listSize, NebulaRandom nebulaRandom) {
		List<Object> list = new ArrayList<>(listSize);
		if (canFillListWithItems()) {
			initNumberRange();
			fillList(listSize, nebulaRandom, list);
		}
		return list;
	}

	private void initNumberRange() {
		numberRange = NebulaTypes.number().range().withMin(BigDecimal.ZERO)
				.withMax(BigDecimal.valueOf(items.length - 1)).build(model);
		numberRange.init(context);
	}

	private boolean canFillListWithItems() {
		return items.length > 0;
	}

	protected Object getItem(NebulaRandom localNebulaRandom) {
		return items[getItemIndex()];
	}

	private Integer getItemIndex() {
		BigDecimal index = (BigDecimal) generator.generate(numberRange).getObject();
		return index.intValue();
	}

	@Override
	public Long getMinRange() {
		return 0L;
	}

	@Override
	public Long getMaxRange() {
		return (long) items.length - 1;
	}

	@Override
	public JavaType getJavaType() {
		Set<JavaType> javaTypes = JavaType.getJavaTypesFor(Arrays.asList(items));

		if (javaTypes.size() == 1) {
			return new JavaType(JavaTypeName.ARRAY, javaTypes.iterator().next());
		} else {
			return new JavaType(JavaTypeName.ARRAY, new JavaType(JavaTypeName.OBJECT));
		}
	}
}
