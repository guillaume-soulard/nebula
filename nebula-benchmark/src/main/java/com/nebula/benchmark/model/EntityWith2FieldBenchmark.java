package com.nebula.benchmark.model;

public class EntityWith2FieldBenchmark extends AbstractEntityBenchmark {

    @Override
    protected void setupBenchmark() {

        addNewEntityWithFields(2);
    }
}
