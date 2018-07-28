package com.nebula.core;

import com.nebula.core.types.NebulaTypes;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.nebula.core.generators.NebulaGenerators.sequence;

public class StaticEntityBuilder {

    private Entity entity;
    private List<StaticEntityRecord> staticEntityRecords = new ArrayList<>();
    private StaticEntityRecord currentRecord;

    public StaticEntityBuilder(Entity entity) {
        this.entity = entity;
    }

    public StaticEntityBuilder newRecord() {

        if (currentRecord == null) {

            currentRecord = new StaticEntityRecord();
        } else {

            staticEntityRecords.add(currentRecord);
            currentRecord = new StaticEntityRecord();
        }

        return this;
    }

    public StaticEntityBuilder addProperty(String name, String value) {

        addPropertyToCurrentRecord(name, value);
        return this;
    }

    public StaticEntityBuilder addProperty(String name, BigDecimal value) {

        addPropertyToCurrentRecord(name, value);
        return this;
    }

    public StaticEntityBuilder addProperty(String name, DateTime value) {

        addPropertyToCurrentRecord(name, value);
        return this;
    }

    public StaticEntityBuilder addProperty(String name, Boolean value) {

        addPropertyToCurrentRecord(name, value);
        return this;
    }

    private void addPropertyToCurrentRecord(String name, Object value) {
        if (currentRecord == null) {

            throw new NebulaException("Use newRecord before using addProperty");
        }

        currentRecord.addProperty(name, value);
    }

    public Entity buildEntity() {

        staticEntityRecords.add(currentRecord);
        currentRecord = null;

        List<String> allDistinctProperties = staticEntityRecords
                .stream()
                .flatMap(r -> r.getStaticEntityProperties().stream())
                .map(StaticEntityProperty::getPropertyName)
                .distinct()
                .collect(Collectors.toList());

        Map<String, List<Object>> valuesByProperty = allDistinctProperties
                .stream()
                .collect(Collectors.toMap(Function.identity(), s -> new ArrayList<>() ));

        staticEntityRecords
                .forEach(record -> record.getStaticEntityProperties().forEach(property -> {
                    valuesByProperty.get(property.getPropertyName()).add(property.getPropertyValue());
                }));

        valuesByProperty.forEach((propertyName, objects) -> {

            if (!objects.isEmpty()) {

                if (objects.get(0) instanceof String) {

                    entity.addProperty(propertyName, sequence(), NebulaTypes.amongItems(objects.stream().map(o -> (String) o).collect(Collectors.toList()).toArray(new String[]{})));
                } else if (objects.get(0) instanceof BigDecimal) {

                    entity.addProperty(propertyName, sequence(), NebulaTypes.amongItems(objects.stream().map(o -> (BigDecimal) o).collect(Collectors.toList()).toArray(new BigDecimal[]{})));
                } else if (objects.get(0) instanceof Boolean) {

                    entity.addProperty(propertyName, sequence(), NebulaTypes.amongItems(objects.stream().map(o -> (Boolean) o).collect(Collectors.toList()).toArray(new Boolean[]{})));
                } else if (objects.get(0) instanceof DateTime) {

                    entity.addProperty(propertyName, sequence(), NebulaTypes.amongItems(objects.stream().map(o -> (DateTime) o).collect(Collectors.toList()).toArray(new DateTime[]{})));
                } else {

                    throw new NebulaException("Unable to dertermine the type of " + objects.get(0));
                }
            }
        });

        entity.setAmount(staticEntityRecords.size());

        return entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
