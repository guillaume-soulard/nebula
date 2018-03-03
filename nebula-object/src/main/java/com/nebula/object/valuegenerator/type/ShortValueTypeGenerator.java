package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.util.List;

import static com.nebula.core.types.NebulaTypes.number;

public class ShortValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return short.class.equals(clazz) || Short.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultShort();
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return number().range()
                .withMin(Short.toString(Short.MIN_VALUE))
                .withMax(Short.toString(Short.MAX_VALUE));
    }

    @Override
    public Object getFinalValue(Object object) {
        return Short.valueOf(object.toString());
    }
}
