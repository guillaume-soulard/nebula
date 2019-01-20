package com.nebula.core.types.amongitems;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.JavaTypeName;
import com.nebula.core.types.Type;

import java.util.List;
import java.util.Set;

class AmongItemsType implements Type {

    private List<GeneratedObject> generatedObjects;

    AmongItemsType(List<GeneratedObject> generatedObjects) {
        if (generatedObjects == null) {
            throw new NebulaException("generatedObjects is null");
        }
        this.generatedObjects = generatedObjects;
    }

    @Override
    public void init(GenerationContext context) {
    }

    @Override
    public GeneratedObject generateObject(GeneratedProperties generatedProperties, Long objectIndex) {
        return generatedObjects.get(Math.toIntExact(objectIndex));
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

        Set<JavaType> javaTypes = JavaType.getJavaTypesFor(generatedObjects);

        if (javaTypes.size() == 1) {
            return javaTypes.iterator().next();
        } else {
            return new JavaType(JavaTypeName.OBJECT);
        }
    }
}
