package com.nebula.core.types.list;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.constant.ConstantTypeBuilder;
import com.nebula.core.types.number.NumberRangeType;

public class ListTypeAmongItems extends AbstractListType {

	private GeneratedObject[] items;
	private NumberRangeType numberRange;

	public ListTypeAmongItems(int minSize, int maxSize, Generator generator, ConstantTypeBuilder[] builders) {
		super(minSize, maxSize, generator);
		this.items = new GeneratedObject[builders.length];

		for (int i = 0; i < builders.length; i++) {
			items[i] = builders[i].build().generateObject(0L);
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
		numberRange = (NumberRangeType) NebulaGenerationTypes.number().range().withMin(BigDecimal.ZERO)
				.withMax(BigDecimal.valueOf(items.length - 1)).build();
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
		return 0l;
	}

	@Override
	public Long getMaxRange() {
		return (long) items.length - 1;
	}
}
