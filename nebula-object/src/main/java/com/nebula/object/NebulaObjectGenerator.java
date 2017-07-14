package com.nebula.object;

import com.nebula.core.*;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;
import com.nebula.core.types.bool.BooleanType;
import com.nebula.core.types.constant.ConstantType;
import com.nebula.core.types.date.DateTimeAmongType;
import com.nebula.core.types.date.DateTimeRangeType;
import com.nebula.core.types.entity.EntityType;
import com.nebula.core.types.list.ListTypeAmongItems;
import com.nebula.core.types.list.ListTypeOfType;
import com.nebula.core.types.number.NumberAmongType;
import com.nebula.core.types.number.NumberRangeType;
import com.nebula.core.types.number.NumberRangeTypeBuilder;
import com.nebula.core.types.string.StringType;
import com.nebula.object.annotation.NebulaAnnotation;
import com.nebula.object.annotation.NebulaGeneratorAnnotation;
import com.nebula.object.annotation.NebulaTypeAnnotation;
import com.nebula.object.annotation.generator.Random;
import com.nebula.object.annotation.type.*;
import com.nebula.object.fieldsetter.*;
import com.nebula.object.typebuilderstrategy.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nebula.core.NebulaCore.newEntity;
import static com.nebula.core.NebulaCore.newModel;
import static com.nebula.core.NebulaGenerationTypes.*;
import static com.nebula.core.NebulaGenerators.random;

public class NebulaObjectGenerator<T> {

    private final Class<T> objectClass;
    private final Model model;
    private final Entity entity;
    private Map<Class<? extends Type>, FieldSetter> fieldSetterStrategy = new HashMap<>();
    private Map<Class<? extends Annotation>, TypeBuilderStrategy> typeBuilderStrategyMap = new HashMap<>();

    public NebulaObjectGenerator(Class<T> objectClass) {
        this.objectClass = objectClass;

        model = newModel();

        fieldSetterStrategy.put(DateTimeAmongType.class, new DateFieldSetter());
        fieldSetterStrategy.put(DateTimeRangeType.class, new DateFieldSetter());
        fieldSetterStrategy.put(ConstantType.class, new ObjectFieldSetter());
        fieldSetterStrategy.put(ListTypeOfType.class, new ListFieldSetter());
        fieldSetterStrategy.put(ListTypeAmongItems.class, new ListFieldSetter());
        fieldSetterStrategy.put(BooleanType.class, new ObjectFieldSetter());
        fieldSetterStrategy.put(StringType.class, new ObjectFieldSetter());
        fieldSetterStrategy.put(NumberAmongType.class, new NumberFieldSetter());
        fieldSetterStrategy.put(NumberRangeType.class, new NumberFieldSetter());
        fieldSetterStrategy.put(EntityType.class, new ObjectFieldSetter());

        typeBuilderStrategyMap.put(EntityObject.class, new EntityObjectTypeBuilderStrategy());
        typeBuilderStrategyMap.put(Bool.class, new BoolTypeBuilderStrategy());
        typeBuilderStrategyMap.put(DateList.class, new DateListTypeBuilderStrategy());
        typeBuilderStrategyMap.put(DateRange.class, new DateRangeTypeBuilderStrategy());
        typeBuilderStrategyMap.put(NumberList.class, new NumberListTypeBuilderStrategy());
        typeBuilderStrategyMap.put(NumberRange.class, new NumberRangeTypeBuilderStrategy());
        typeBuilderStrategyMap.put(StringPattern.class, new StringPatternTypeBuilderStrategy());

        // FIXME amount should be parameterizable
        entity = buildEntity(objectClass, 1);
        model.addEntity(entity);
    }

    public T getObjectAtIndex(long index) throws NebulaException {
        try {
            T object = objectClass.newInstance();
            setGeneratedPropertiesToObject(object, model.generateEntityObject(entity, index, 0l));
            return object;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new NebulaException(e.getMessage());
        } catch (NoSuchFieldException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    private void setGeneratedPropertiesToObject(Object object, GeneratedObject generatedObject) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        for (GeneratedProperty generatedProperty : generatedObject.getGeneratedProperties()) {
            setPropertyValueToObject(object, generatedProperty);
        }
    }

    private void setPropertyValueToObject(Object object, GeneratedProperty generatedProperty) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        if (isNotIdProperty(generatedProperty)) {
            Field field = getFieldByGeneratedProperty(object, generatedProperty);
            setFieldAccessibleIfNeeded(field);
            FieldSetter fieldSetter = getFieldSetter(generatedProperty);
            if (isSubObject(generatedProperty)) {
                setSubObjectProperty(object, generatedProperty, field);
            } else {
                setObjectProperty(object, generatedProperty, field, fieldSetter);
            }
        }
    }

    private void setObjectProperty(Object object, GeneratedProperty generatedProperty, Field field, FieldSetter fieldSetter) {
        Object generatedValue = generatedProperty.getPropertyValue().getObject();
        fieldSetter.setValueToObjectForField(object, field, generatedValue);
    }

    private FieldSetter getFieldSetter(GeneratedProperty generatedProperty) {
        return fieldSetterStrategy.get(generatedProperty.getPropertyType().getClass());
    }

    private void setFieldAccessibleIfNeeded(Field field) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
    }

    private Field getFieldByGeneratedProperty(Object object, GeneratedProperty generatedProperty) throws NoSuchFieldException {
        return object.getClass().getDeclaredField(generatedProperty.getPropertyName());
    }

    private boolean isNotIdProperty(GeneratedProperty generatedProperty) {
        return !"_id".equals(generatedProperty.getPropertyName());
    }

    private boolean isSubObject(GeneratedProperty generatedProperty) {
        return generatedProperty.getPropertyValue().getObject() == null
                && generatedProperty.getPropertyValue().getGeneratedProperties() != null
                && !generatedProperty.getPropertyValue().getGeneratedProperties().isEmpty();
    }

    private void setSubObjectProperty(Object object, GeneratedProperty generatedProperty, Field field) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Object objectProperty = field.getType().newInstance();
        field.set(object, objectProperty);
        setGeneratedPropertiesToObject(objectProperty, generatedProperty.getPropertyValue());
    }

    private Entity buildEntity(Class<?> entityClass, long amount) {
        Entity entity = newEntity(entityClass.getCanonicalName(), amount);

        // FIXME inherited fields are ignored
        for (Field field : entityClass.getDeclaredFields()) {
            addPropertyInEntityByField(entity, field);
        }

        return entity;
    }

    private void addPropertyInEntityByField(Entity entity, Field field) {
        Annotation generatorAnnotationOfField = getGeneratorAnnotationOfFieldOrNull(field);
        Annotation typeAnnotationOfField = getTypeAnnotationOfFieldOrNull(field);

        TypeBuilder typeBuilder = getTypeBuilder(field, typeAnnotationOfField);
        GeneratorBuilder generatorBuilder = getGeneratorBuilder(generatorAnnotationOfField);

        if (generatorBuilder != null && typeBuilder != null) {
            entity.addProperty(field.getName(), generatorBuilder, typeBuilder);
        }
    }

    private TypeBuilder getTypeBuilder(Field field, Annotation typeAnnotationOfField) {
        TypeBuilder typeBuilder = null;
        if (typeAnnotationOfField != null) {
            typeBuilder = typeBuilderStrategyMap.get(typeAnnotationOfField.annotationType())
                    .getTypeBuilder(field, typeAnnotationOfField);

            if (typeAnnotationOfField != null
                    && typeAnnotationOfField.annotationType().equals(EntityObject.class)) {
                String entityName = field.getType().getCanonicalName();
                Entity entityToGenerate = model.getEntityByName(entityName);
                if (entityToGenerate == null) {
                    EntityObject entityObject = (EntityObject) typeAnnotationOfField;
                    entityToGenerate = buildEntity(field.getType(), entityObject.amount());
                    model.addEntity(entityToGenerate);
                }
            }
        }
        return typeBuilder;
    }

    private GeneratorBuilder getGeneratorBuilder(Annotation generatorAnnotationOfField) {
        GeneratorBuilder generatorBuilder = null;

        if (generatorAnnotationOfField != null
                && generatorAnnotationOfField.annotationType().equals(Random.class)) {
            generatorBuilder = random();
        }

        return generatorBuilder;
    }

    private Annotation getTypeAnnotationOfFieldOrNull(Field field) {
        for (Annotation annotation : getNebulaAnnotationsOfField(field)) {
            if (annotation.annotationType().isAnnotationPresent(NebulaTypeAnnotation.class)) {
                return annotation;
            }
        }

        return null;
    }

    private Annotation getGeneratorAnnotationOfFieldOrNull(Field field) {
        for (Annotation annotation : getNebulaAnnotationsOfField(field)) {
            if (annotation.annotationType().isAnnotationPresent(NebulaGeneratorAnnotation.class)) {
                return annotation;
            }
        }

        return null;
    }

    private List<Annotation> getNebulaAnnotationsOfField(Field field) {
        Annotation[] fieldAnnotations = field.getAnnotations();
        List<Annotation> nebulaAnnotations = new ArrayList<>();

        for (Annotation fieldAnnotation : fieldAnnotations) {
            if (isNebulaAnnotation(fieldAnnotation)) {
                nebulaAnnotations.add(fieldAnnotation);
            }
        }

        return nebulaAnnotations;
    }

    private boolean isNebulaAnnotation(Annotation fieldAnnotation) {
        return fieldAnnotation.annotationType().isAnnotationPresent(NebulaAnnotation.class);
    }
}
