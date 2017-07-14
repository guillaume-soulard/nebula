package com.nebula.object;

import com.nebula.object.annotation.generator.Random;
import com.nebula.object.annotation.type.NumberRange;

public class RandomIntFieldsTestClass {

    @Random
    @NumberRange(min = 0, max = 100)
    private int privateIntField;

    @Random
    @NumberRange(min = 0, max = 100)
    public int publicIntField;

    @Random
    @NumberRange(min = 0, max = 100)
    protected int protectedIntField;

    public int getPrivateIntField() {
        return privateIntField;
    }
}
