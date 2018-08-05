package com.nebula.benchmark.model;

public class EntityWith10FieldBenchmark extends AbstractEntityBenchmark {

    @Override
    protected void setupBenchmark() {

        addNewEntityWithFields(10);
    }
}
