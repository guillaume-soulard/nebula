package com.nebula.benchmark.types;

import com.nebula.benchmark.AbstractNebulaBenchmark;
import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.atomic.AtomicLong;


abstract class AbstractTypeBenchmark extends AbstractNebulaBenchmark {

    private AtomicLong index;
    private Model model;
    private Entity entity;

    @Benchmark
    public void benchmarkType(Blackhole blackhole) {
        blackhole.consume(model.generateEntityObject(entity, index.getAndIncrement(), 0L));
    }

    @Setup(Level.Iteration)
    public void setup() {
        model = ModelBuilder.newModel().build();
        entity = model.newEntity("test");
        addProperty(entity);
        index = new AtomicLong(0L);
    }

    protected abstract void addProperty(Entity entity);
}
