package com.nebula.object;

import com.nebula.object.annotation.generator.Random;
import com.nebula.object.annotation.type.EntityObject;

public class EntityFieldTestClass {

    @Random
    @EntityObject(amount = 1)
    public BooleanFieldTestClass entityTest;
}
