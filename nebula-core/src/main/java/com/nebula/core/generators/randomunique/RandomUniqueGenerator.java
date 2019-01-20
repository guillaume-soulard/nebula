package com.nebula.core.generators.randomunique;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.generators.Generator;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;

import java.util.Stack;
import java.util.stream.LongStream;

import static java.util.Collections.shuffle;

public class RandomUniqueGenerator implements Generator {

    private GenerationContext context;
    private Stack<Long> indices;

    RandomUniqueGenerator() {

    }

    @Override
    public void init(GenerationContext context) {

        this.context = context;
    }

    @Override
    public GeneratedObject generate(GeneratedProperties generatedProperties, Type type) {

        throwExceptionIfNotInitialized();
        type.init(context);

        if (indices == null) {
            indices = new Stack<>();
        }

        if (indices.isEmpty()) {
            LongStream.rangeClosed(type.getMinRange(), type.getMaxRange()).forEach((long index) -> indices.push(index));
            shuffle(indices, context.getNebulaRandom().getRandom());
        }

        return type.generateObject(generatedProperties, indices.pop());
    }

    private void throwExceptionIfNotInitialized() {

        if (context == null) {
            throw new NebulaException("generator is not initialized");
        }
    }
}
