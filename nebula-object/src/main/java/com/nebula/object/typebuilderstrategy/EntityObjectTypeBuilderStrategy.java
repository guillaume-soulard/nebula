package com.nebula.object.typebuilderstrategy;

import com.nebula.core.Entity;
import com.nebula.core.types.TypeBuilder;
import com.nebula.object.annotation.type.DateList;
import com.nebula.object.annotation.type.EntityObject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.nebula.core.NebulaGenerationTypes.dateTime;
import static com.nebula.core.NebulaGenerationTypes.entity;

public class EntityObjectTypeBuilderStrategy implements TypeBuilderStrategy {
    @Override
    public TypeBuilder getTypeBuilder(Field field, Annotation typeAnnotationOfField) {
        String entityName = field.getType().getCanonicalName();
        return entity(entityName);
    }
}
