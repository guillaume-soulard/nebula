package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.util.List;

import static com.nebula.core.types.NebulaTypes.number;

public class DoubleValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return Double.class.equals(clazz) || double.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultDouble();
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return number().range()
                .withMin(Double.toString(Double.MIN_VALUE))
                .withMax(Double.toString(Double.MAX_VALUE));
    }

    @Override
    public Object getFinalValue(Object object) {
        return Double.valueOf(object.toString());
    }
}
