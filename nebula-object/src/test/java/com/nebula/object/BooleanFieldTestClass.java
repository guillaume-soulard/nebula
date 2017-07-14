package com.nebula.object;

import com.nebula.object.annotation.generator.Random;
import com.nebula.object.annotation.type.Bool;
import com.nebula.object.annotation.type.DateRange;
import org.joda.time.DateTime;

public class BooleanFieldTestClass {

    @Random
    @Bool
    public Boolean booleanField;
}
