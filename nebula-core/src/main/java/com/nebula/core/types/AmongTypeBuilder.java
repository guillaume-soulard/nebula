package com.nebula.core.types;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AmongTypeBuilder<S extends AmongTypeBuilder, T extends Comparable<T>> extends AbstractParsable<T> implements TypeBuilder {

	protected List<T> items = new ArrayList<>();
	private List<String> itemStrings = new ArrayList<>();

	@Override
	public Type build(Model model) {

		if (itemStrings != null) {
			for (String itemString : itemStrings) {
				if (itemString != null) {
					items.add(parseItem(model, itemString));
				} else {
					items.add(null);
				}
			}
		}

		return buildImpl(model);
	}

	protected abstract Type buildImpl(Model model);
	protected abstract S getThis();

	public S items(T... items) {
		checkParameter(items);
		this.items.addAll(Arrays.asList(items));
		return getThis();
	}

	public S items(String... itemStrings) {
		checkParameter(itemStrings);
		this.itemStrings.addAll(Arrays.asList(itemStrings));
		return getThis();
	}

	private void checkParameter(Object[] items) {
		if (items == null) {
			throw new NebulaException("items is null");
		}
		if (items.length == 0) {
			throw new NebulaException("items is empty");
		}
	}
}
