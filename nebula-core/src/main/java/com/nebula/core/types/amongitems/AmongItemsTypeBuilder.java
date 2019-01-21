package com.nebula.core.types.amongitems;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.core.types.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AmongItemsTypeBuilder implements RandomTypeBuilder {

    private final List<GeneratedObject> generatedObjects = new ArrayList<>();

    public AmongItemsTypeBuilder(Collection<Object> objects) {
        for (Object object : objects) {
            generatedObjects.add(GeneratedObject.of(object));
        }
    }

    @Override
    public Type build(Model model) {
        return new AmongItemsType(generatedObjects);
    }
}
