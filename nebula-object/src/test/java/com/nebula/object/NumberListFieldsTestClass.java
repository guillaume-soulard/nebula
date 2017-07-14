package com.nebula.object;

import com.nebula.object.annotation.generator.Random;
import com.nebula.object.annotation.type.NumberList;

import java.math.BigDecimal;

public class NumberListFieldsTestClass {

    @Random
    @NumberList({ 20.0 })
    public int intField;

    @Random
    @NumberList({ 20.0 })
    public int intObjectField;

    @Random
    @NumberList({ 20.0 })
    public double doubleField;

    @Random
    @NumberList({ 20.0 })
    public double doubleObjectField;

    @Random
    @NumberList({ 20.0 })
    public float floatField;

    @Random
    @NumberList({ 20.0 })
    public float floatObjectField;

    @Random
    @NumberList({ 20.0 })
    public long longField;

    @Random
    @NumberList({ 20.0 })
    public long longObjectField;

    @Random
    @NumberList({ 20.0 })
    public BigDecimal bigDecimalField;
}
