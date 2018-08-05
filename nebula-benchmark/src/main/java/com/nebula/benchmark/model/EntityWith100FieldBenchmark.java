package com.nebula.benchmark.model;

public class EntityWith100FieldBenchmark extends AbstractEntityBenchmark {

    @Override
    protected void setupBenchmark() {

        addNewEntityWithFields(50);
    }
}
