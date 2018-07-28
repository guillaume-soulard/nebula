package com.nebula.core;

import com.nebula.core.generationrule.GenerationRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelBuilder {

    private long seed;
    private Character numberThousandSeparator;
    private Character numberDecimalSeparator;
    private String dateFormat;
    private List<Entity> entities = new ArrayList<>();
    private List<GenerationRule> generationRules = new ArrayList<>();
    private int maxDepth;

    private ModelBuilder() {
        seed = new Random().nextLong();
        dateFormat = "MM/dd/yyyy";
        numberDecimalSeparator = '.';
        numberThousandSeparator = ',';
        maxDepth = 10;
    }

    private ModelBuilder(Model baseModel) {
        seed = baseModel.getSeed();
        dateFormat = baseModel.getDateFormat();
        numberDecimalSeparator = baseModel.getNumberDecimalSeparator();
        numberThousandSeparator = baseModel.getNumberThousandSeparator();
        entities = baseModel.getEntities();
        generationRules = baseModel.getGenerationRules();
        maxDepth = baseModel.getMaxDepth();
    }

    public static ModelBuilder newEmptyModel() {
        return new ModelBuilder();
    }

    public static ModelBuilder newModelFrom(Model baseModel) {
        return new ModelBuilder(baseModel);
    }

    public Model build() {
        return new Model(seed, numberThousandSeparator, numberDecimalSeparator, dateFormat, entities, generationRules, maxDepth);
    }

    public ModelBuilder withSeed(long seed) {
        this.seed = seed;
        return this;
    }

    public ModelBuilder withSeed(String seedString) {
        if (seedString == null) {
            throw new NebulaException("seed is null");
        }

        long longSeed = 0L;

        for (byte seedByte : seedString.getBytes()) {
            longSeed += 31 * seedByte;
        }

        this.seed = longSeed;
        return this;
    }

    public ModelBuilder withNumberThousandSeparator(char numberThousandSeparator) {
        this.numberThousandSeparator = numberThousandSeparator;
        return this;
    }

    public ModelBuilder withoutNumberThousandSeparator() {
        this.numberThousandSeparator = null;
        return this;
    }

    public ModelBuilder withNumberDecimalSeparator(char numberDecimalSeparator) {
        this.numberDecimalSeparator = numberDecimalSeparator;
        return this;
    }

    public ModelBuilder withoutNumberDecimalSeparator() {
        this.numberDecimalSeparator = null;
        return this;
    }

    public ModelBuilder withDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    public ModelBuilder withMaxDepth(int maxDepth) {
        if (maxDepth < 0) {

            throw new NebulaException("maxDepth is negative");
        }

        this.maxDepth = maxDepth;

        return this;
    }
}
