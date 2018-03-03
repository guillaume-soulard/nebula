package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.util.List;

import static com.nebula.core.types.NebulaTypes.number;

public class CharValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return Character.class.equals(clazz) || char.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultChar();
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return number().range()
                .withMin(Character.toString(Character.MIN_VALUE))
                .withMax(Character.toString(Character.MAX_VALUE));
    }

    @Override
    public Object getFinalValue(Object object) {
        return object.toString().toCharArray()[0];
    }
}
