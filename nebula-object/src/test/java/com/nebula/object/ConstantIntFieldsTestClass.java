package com.nebula.object;

import com.nebula.object.annotation.generator.Random;
import com.nebula.object.annotation.type.NumberRange;

public class ConstantIntFieldsTestClass {

    @Random
    @NumberRange(min = 10, max = 10)
    private int privateIntField;

    @Random
    @NumberRange(min = 20, max = 20)
    public int publicIntField;

    @Random
    @NumberRange(min = 30, max = 30)
    protected int protectedIntField;

    public int publicIntFieldNotGenerated = 0;

    public int getPrivateIntField() {
        return privateIntField;
    }
}
