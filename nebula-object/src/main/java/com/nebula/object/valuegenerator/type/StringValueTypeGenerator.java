package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.util.List;

import static com.nebula.core.types.NebulaTypes.string;

public class StringValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultString();
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return string();
    }

    @Override
    public Object getFinalValue(Object object) {
        return object.toString();
    }
}
