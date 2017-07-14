package com.nebula.object;

import com.nebula.object.annotation.generator.Random;
import com.nebula.object.annotation.type.StringPattern;

public class ConstantStringFieldTestClass {

    @Random
    @StringPattern(pattern = "[a]{10}")
    public String field;
}
