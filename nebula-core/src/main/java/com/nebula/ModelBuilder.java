package com.nebula;

import com.nebula.core.NebulaException;

import java.util.Random;

public class ModelBuilder {

    private long seed;
    private char numberThousandSeparator;
    private char numberDecimalSeparator;
    private String dateFormat;

    public ModelBuilder() {
        seed = new Random().nextLong();
        dateFormat = "MM/dd/yyyy";
        numberDecimalSeparator = '.';
        numberThousandSeparator = ',';
    }

    public Model build() {
        return new Model(seed, numberThousandSeparator, numberDecimalSeparator, dateFormat);
    }

    public ModelBuilder withSeed(long seed) {
        this.seed = seed;
        return this;
    }

    public ModelBuilder withSeed(String seedString) {
        if (seedString == null) {
            throw new NebulaException("seed is null");
        }

        long longSeed = 0l;

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

    public ModelBuilder withNumberDecimalSeparator(char numberDecimalSeparator) {
        this.numberDecimalSeparator = numberDecimalSeparator;
        return this;
    }

    public ModelBuilder withDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }
}
