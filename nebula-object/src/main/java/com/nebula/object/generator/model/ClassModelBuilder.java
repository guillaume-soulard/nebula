package com.nebula.object.generator.model;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.type.*;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.nebula.core.generators.NebulaGenerators.random;

public class ClassModelBuilder {

    private final List<ValueTypeGenerator> basicValueTypeGenerators;
    private final List<ValueTypeGenerator> valueTypeGenerators;

    public ClassModelBuilder() {
        basicValueTypeGenerators = new ArrayList<>();

        basicValueTypeGenerators.add(new BigDecimalValueTypeGenerator());
        basicValueTypeGenerators.add(new BooleanValueTypeGenerator());
        basicValueTypeGenerators.add(new DateValueTypeGenerator());
        basicValueTypeGenerators.add(new DoubleValueTypeGenerator());
        basicValueTypeGenerators.add(new IntegerValueTypeGenerator());
        basicValueTypeGenerators.add(new StringValueTypeGenerator());
        basicValueTypeGenerators.add(new FloatValueTypeGenerator());
        basicValueTypeGenerators.add(new LongValueTypeGenerator());
        basicValueTypeGenerators.add(new ShortValueTypeGenerator());
        basicValueTypeGenerators.add(new CharValueTypeGenerator());
        basicValueTypeGenerators.add(new ByteValueTypeGenerator());

        valueTypeGenerators = new ArrayList<>();

        valueTypeGenerators.addAll(basicValueTypeGenerators);
        valueTypeGenerators.add(new ListValueTypeGenerator(basicValueTypeGenerators));
        valueTypeGenerators.add(new ArrayValueTypeGenerator());

        // the last field setter to test
        valueTypeGenerators.add(new ObjectValueTypeGenerator());
    }

    public Model buildModelFrom(Class<?> clazz) {

        Model model = new ModelBuilder()
                .build();

        buildObjectEntity(clazz, model);

        return model;
    }

    private void buildObjectEntity(Class<?> clazz, Model model) {

        if (entityNotExists(clazz, model)) {
            Entity entity = model.newEntity(clazz.getCanonicalName());

            if (isBasicType(clazz)) {
                entity.addProperty("_val", random(), getNebulaPropertyType(clazz, Collections.emptyList()));
            } else {

                for (Field field : FieldUtils.getAllFieldsList(clazz)) {

                    List<Class<?>> genericTypes = new ArrayList<>();

                    if (field.getType().isArray()) {
                        buildObjectEntity(field.getType().getComponentType(), model);
                    } else if (Collection.class.isAssignableFrom(field.getType())) {
                        try {
                            Class<?> genericType = Class.forName(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName());
                            genericTypes.add(genericType);
                            buildObjectEntity(genericType, model);
                        } catch (ClassNotFoundException e) {
                            throw new NebulaException(e.getMessage());
                        }
                    } else {
                        buildObjectEntity(field.getType(), model);
                    }

                    entity.addProperty(field.getName(), random(), getNebulaPropertyType(field.getType(), genericTypes));
                }

            }

            model.addEntity(entity);
        }
    }

    private boolean entityNotExists(Class<?> clazz, Model model) {
        for (Entity entity : model.getEntities()) {
            if (entity.getName().equals(clazz.getCanonicalName())) {
                return false;
            }
        }

        return true;
    }

    private <T> boolean isBasicType(Class<T> clazz) {
        for (ValueTypeGenerator generator : basicValueTypeGenerators) {
            if (generator.match(clazz)) {
                return true;
            }
        }

        return false;
    }

    private RandomTypeBuilder getNebulaPropertyType(Class<?> type, List<Class<?>> genericTypes) {

        for (ValueTypeGenerator valueTypeGenerator : valueTypeGenerators) {
            if (valueTypeGenerator.match(type)) {
                return valueTypeGenerator.getNebulaTypeFor(type, genericTypes);
            }
        }

        throw new NebulaException("Type " + type.toGenericString() + " is not supported");
    }
}
