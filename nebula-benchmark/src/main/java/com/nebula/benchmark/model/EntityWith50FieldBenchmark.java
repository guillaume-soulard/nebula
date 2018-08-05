package com.nebula.benchmark.model;

public class EntityWith50FieldBenchmark extends AbstractEntityBenchmark {

    @Override
    protected void setupBenchmark() {

        addNewEntityWithFields(100);
    }
}
