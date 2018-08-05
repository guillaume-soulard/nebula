package com.nebula.benchmark.model;

public class EntityWith1FieldBenchmark extends AbstractEntityBenchmark {

    @Override
    protected void setupBenchmark() {

        addNewEntityWithFields(1);
    }
}
