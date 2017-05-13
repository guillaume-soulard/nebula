package com.nebula.core.generators;

import java.util.concurrent.atomic.AtomicLong;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;

public class SequanceGenerator implements Generator {

	private AtomicLong index = null;
	private boolean allowCycle = false;

	@Override
	public void init(NebulaRandom nebulaRandom) {

	}

	@Override
	public GeneratedObject generate(Type type) {
		initIndexIfItsFirstTime(type);
		reInitIndexIfIndexReachMaxTypeIndexAndCycleIsAllow(type);
		throwExceptionWhenIndexReachMaxIndexIfNeeded(type);
		return type.generateObject(index.getAndIncrement());
	}

	private void reInitIndexIfIndexReachMaxTypeIndexAndCycleIsAllow(Type type) {
		if (allowCycle && index.get() > type.getMaxRange()) {
			index.set(type.getMinRange());
		}
	}

	private void throwExceptionWhenIndexReachMaxIndexIfNeeded(Type type) {
		if (index.get() > type.getMaxRange()) {
			throw new NebulaException("sequance reach the maximum index of type (" + type.getMaxRange()
					+ "). Use cycle() to allow sequance to restart");
		}
	}

	private void initIndexIfItsFirstTime(Type type) {
		if (index == null) {
			index = new AtomicLong(type.getMinRange());
		}
	}

	public SequanceGenerator cycle() {
		this.allowCycle = true;
		return this;
	}
}
