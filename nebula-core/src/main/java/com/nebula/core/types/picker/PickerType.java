package com.nebula.core.types.picker;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.JavaTypeName;
import com.nebula.core.types.Type;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.*;

class PickerType implements Type {

    private List<GeneratedObject> generatedObjects;
    private GenerationContext context;

    PickerType(List<GeneratedObject> generatedObjects) {
        if (generatedObjects == null) {
            throw new NebulaException("generatedObjects is null");
        }
        this.generatedObjects = generatedObjects;
    }

    @Override
    public void init(GenerationContext context) {
        this.context = context;
    }

    @Override
    public GeneratedObject generateObject(Long objectIndex) {
        NebulaRandom localNebulaRandom = new NebulaRandom(context.getNebulaRandom().getSeed() + objectIndex);
        return generatedObjects.get(Math.toIntExact(localNebulaRandom.randomBetween(getMinRange(), getMaxRange())));
    }

    @Override
    public Long getMinRange() {
        return 0L;
    }

    @Override
    public Long getMaxRange() {
        return (long) generatedObjects.size() - 1;
    }

    @Override
    public JavaType getJavaType() {

        Set<JavaType> javaTypes = new HashSet<>();

        for (GeneratedObject generatedObject : generatedObjects) {

            if (generatedObject.getObject() instanceof Boolean) {
                javaTypes.add(JavaType.BOOLEAN);
            }

            if (generatedObject.getObject() instanceof String) {
                javaTypes.add(JavaType.STRING);
            }

            if (generatedObject.getObject() instanceof BigDecimal) {
                javaTypes.add(JavaType.NUMBER);
            }

            if (generatedObject.getObject() instanceof DateTime) {
                javaTypes.add(JavaType.DATE);
            }
        }

        if (javaTypes.size() == 1) {
            return javaTypes.iterator().next();
        } else {
            return new JavaType(JavaTypeName.OBJECT);
        }
    }
}
