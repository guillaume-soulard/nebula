package com.nebula.object.valuegenerator.type;

import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;
import org.joda.time.DateTime;

import java.util.Date;

public class DateValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return Date.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultDate();
    }
}
