package com.nebula.core.types.picker;

import com.nebula.core.Model;
import com.nebula.core.GeneratedObject;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PickerTypeBuilder implements TypeBuilder {

    private List<GeneratedObject> generatedObjects = new ArrayList<>();

    @Override
    public Type build(Model model) {
        return new PickerType(generatedObjects);
    }

    public PickerTypeBuilder addItem(String item) {
        generatedObjects.add(new GeneratedObject(item));
        return this;
    }

    public PickerTypeBuilder addItem(DateTime item) {
        generatedObjects.add(new GeneratedObject(item));
        return this;
    }

    public PickerTypeBuilder addItem(Boolean item) {
        generatedObjects.add(new GeneratedObject(item));
        return this;
    }

    public PickerTypeBuilder addItem(BigDecimal item) {
        generatedObjects.add(new GeneratedObject(item));
        return this;
    }
}
