package com.nebula.benchmark.model;

public class EntityWith20FieldBenchmark extends AbstractEntityBenchmark {

    @Override
    protected void setupBenchmark() {

        addNewEntityWithFields(20);
    }
}
