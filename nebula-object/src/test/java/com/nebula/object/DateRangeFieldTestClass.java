package com.nebula.object;

import com.nebula.object.annotation.generator.Random;
import com.nebula.object.annotation.type.DateRange;
import com.nebula.object.annotation.type.NumberRange;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;

public class DateRangeFieldTestClass {

    @Random
    @DateRange(min = "01/01/2017", max = "01/01/2017")
    public Date dateField;

    @Random
    @DateRange(min = "01/01/2017", max = "01/01/2017")
    public DateTime dateTimeField;
}
