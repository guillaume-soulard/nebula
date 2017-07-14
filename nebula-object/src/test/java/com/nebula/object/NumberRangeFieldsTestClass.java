package com.nebula.object;

import com.nebula.object.annotation.generator.Random;
import com.nebula.object.annotation.type.NumberRange;

import java.math.BigDecimal;

public class NumberRangeFieldsTestClass {

    @Random
    @NumberRange(min = 10, max = 10)
    public int intField;

    @Random
    @NumberRange(min = 10, max = 10)
    public int intObjectField;

    @Random
    @NumberRange(min = 10, max = 10)
    public double doubleField;

    @Random
    @NumberRange(min = 10, max = 10)
    public double doubleObjectField;

    @Random
    @NumberRange(min = 10, max = 10)
    public float floatField;

    @Random
    @NumberRange(min = 10, max = 10)
    public float floatObjectField;

    @Random
    @NumberRange(min = 10, max = 10)
    public long longField;

    @Random
    @NumberRange(min = 10, max = 10)
    public long longObjectField;

    @Random
    @NumberRange(min = 10, max = 10)
    public BigDecimal bigDecimalField;
}
