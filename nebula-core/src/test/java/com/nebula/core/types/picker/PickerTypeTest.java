package com.nebula.core.types.picker;

import com.nebula.core.Model;
import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PickerTypeTest {

    @Test
    public void generateObject_should_return_item1() throws Exception {

        // GIVEN
        List<GeneratedObject> generatedObjects = new ArrayList<>();
        GeneratedObject item1 = new GeneratedObject("Item 1");
        generatedObjects.add(item1);
        GeneratedObject item2 = new GeneratedObject("Item 2");
        generatedObjects.add(item2);
        GeneratedObject item3 = new GeneratedObject("Item 3");
        generatedObjects.add(item3);
        Type picker  = new PickerType(generatedObjects);
        picker.init(getContext(0L));

        // WHEN
        GeneratedObject result = picker.generateObject(0L);

        // THEN
        assertThat(result).isEqualTo(item3);
    }

    @Test
    public void getMinRange_should_return_0_when_list_is_empty() throws Exception {

        // GIVEN
        Type picker  = new PickerType(new ArrayList<>());
        picker.init(getContext(0L));

        // WHEN
        Long result = picker.getMinRange();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    public void getMinRange_should_return_0_when_list_is_not_empty() throws Exception {

        // GIVEN
        List<GeneratedObject> generatedObjects = new ArrayList<>();
        generatedObjects.add(new GeneratedObject("Item 1"));
        Type picker  = new PickerType(generatedObjects);
        picker.init(getContext(0L));

        // WHEN
        Long result = picker.getMinRange();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    public void getMaxRange_should_return_0_when_list_is_not_empty() throws Exception {

        // GIVEN
        List<GeneratedObject> generatedObjects = new ArrayList<>();
        Type picker  = new PickerType(generatedObjects);
        picker.init(getContext(0L));

        // WHEN
        Long result = picker.getMaxRange();

        // THEN
        assertThat(result).isEqualTo(-1L);
    }

    @Test
    public void getMaxRange_should_return_1_when_list_size_is_1() throws Exception {

        // GIVEN
        List<GeneratedObject> generatedObjects = new ArrayList<>();
        generatedObjects.add(new GeneratedObject("Item 1"));
        Type picker  = new PickerType(generatedObjects);
        picker.init(getContext(0L));

        // WHEN
        Long result = picker.getMaxRange();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    public void newPickerType_should_throw_exception_when_null_list_is_passed() throws Exception {

        // GIVEN;
        Exception exception = null;

        // WHEN
        try {
            new PickerType(null);
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception)
                .isInstanceOf(NebulaException.class)
                .hasMessage("generatedObjects is null");
    }

    private GenerationContext getContext(long index) {
        NebulaRandom nebulaRandom = new NebulaRandom(0L);
        return new GenerationContext(nebulaRandom, mock(Model.class), index);
    }
}
