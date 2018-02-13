package com.nebula.core.types.amongitems;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AmongItemsTypeBuilder implements RandomTypeBuilder {

    private List<GeneratedObject> generatedObjects = new ArrayList<>();

    public AmongItemsTypeBuilder(Collection<Object> objects) {
        for (Object object : objects) {
            generatedObjects.add(new GeneratedObject(object));
        }
    }

    @Override
    public Type build(Model model) {
        return new AmongItemsType(generatedObjects);
    }
}
