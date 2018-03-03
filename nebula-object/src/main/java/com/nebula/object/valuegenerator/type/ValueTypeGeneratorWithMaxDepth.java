package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.util.List;

abstract class ValueTypeGeneratorWithMaxDepth implements ValueTypeGenerator {

    Object generateIfMaxDepthNotReached(ValueTypeGeneratorContext context, Class<?> clazz) {
        if (context.getCurrentGenerationDepth() < context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultMaxDepth()) {
            context.incrementGenerationDepth();
            return context.getObjectGenerator().generateWithMaxDepth(clazz, context.getCurrentGenerationDepth());
        } else {
            return null;
        }
    }
}
