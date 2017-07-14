package com.nebula.object;

import com.nebula.object.annotation.generator.Random;
import com.nebula.object.annotation.type.DateList;
import com.nebula.object.annotation.type.DateRange;
import org.joda.time.DateTime;

import java.util.Date;

public class DateListFieldTestClass {

    @Random
    @DateList("01/01/2017")
    public Date dateField;

    @Random
    @DateList("01/01/2017")
    public DateTime dateTimeField;
}
