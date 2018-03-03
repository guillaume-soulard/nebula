package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.types.NebulaTypes.entity;
import static com.nebula.core.types.NebulaTypes.list;

public class ListValueTypeGenerator extends ValueTypeGeneratorWithMaxDepth {

    private List<ValueTypeGenerator> basicValueTypeGenerators;

    public ListValueTypeGenerator(List<ValueTypeGenerator> basicValueTypeGenerators) {
        this.basicValueTypeGenerators = basicValueTypeGenerators;
    }

    @Override
    public boolean match(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultCollectionSize(); i++) {
            list.add(generateIfMaxDepthNotReached(context, context.getTypeArguments().get(0)));
        }
        return list;
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {

        Class<?> listType = genericTypes.get(0);
        for (ValueTypeGenerator basicValueTypeGenerator : basicValueTypeGenerators) {
            if (basicValueTypeGenerator.match(listType)) {
                return list().of(random(), basicValueTypeGenerator.getNebulaTypeFor(listType, Collections.emptyList()));
            }
        }

        return list().of(random(), entity(listType.getCanonicalName()));
    }

    @Override
    public Object getFinalValue(Object object) {
        return object;
    }
}
