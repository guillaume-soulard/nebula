package com.nebula.core.types.amongitems;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AmongItemsTypeTest {

    @Test
    @DisplayName("generateObject should return item1")
    void generateObject_should_return_item1() {

        // GIVEN
        List<GeneratedObject> generatedObjects = new ArrayList<>();
        GeneratedObject item1 = new GeneratedObject("Item 1");
        generatedObjects.add(item1);
        GeneratedObject item2 = new GeneratedObject("Item 2");
        generatedObjects.add(item2);
        GeneratedObject item3 = new GeneratedObject("Item 3");
        generatedObjects.add(item3);
        Type picker  = new AmongItemsType(generatedObjects);
        picker.init(getContext());

        // WHEN
        GeneratedObject result = picker.generateObject(Collections.emptyList(), 0L);

        // THEN
        assertThat(result).isEqualTo(item1);
    }

    @Test
    @DisplayName("getMinRange should return 0 when list is empty")
    void getMinRange_should_return_0_when_list_is_empty() {

        // GIVEN
        Type picker  = new AmongItemsType(new ArrayList<>());
        picker.init(getContext());

        // WHEN
        Long result = picker.getMinRange();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    @DisplayName("getMinRange should return 0 when list is not empty")
    void getMinRange_should_return_0_when_list_is_not_empty() {

        // GIVEN
        List<GeneratedObject> generatedObjects = new ArrayList<>();
        generatedObjects.add(new GeneratedObject("Item 1"));
        Type picker  = new AmongItemsType(generatedObjects);
        picker.init(getContext());

        // WHEN
        Long result = picker.getMinRange();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    @DisplayName("getMaxRange should return 0 when list is not empty")
    void getMaxRange_should_return_0_when_list_is_not_empty() {

        // GIVEN
        List<GeneratedObject> generatedObjects = new ArrayList<>();
        Type picker  = new AmongItemsType(generatedObjects);
        picker.init(getContext());

        // WHEN
        Long result = picker.getMaxRange();

        // THEN
        assertThat(result).isEqualTo(-1L);
    }

    @Test
    @DisplayName("getMaxRange should return 1 when list size is 1")
    void getMaxRange_should_return_1_when_list_size_is_1() {

        // GIVEN
        List<GeneratedObject> generatedObjects = new ArrayList<>();
        generatedObjects.add(new GeneratedObject("Item 1"));
        Type picker  = new AmongItemsType(generatedObjects);
        picker.init(getContext());

        // WHEN
        Long result = picker.getMaxRange();

        // THEN
        assertThat(result).isEqualTo(0L);
    }

    @Test
    @DisplayName("newPickerType should throw exception when null list is passed")
    void newPickerType_should_throw_exception_when_null_list_is_passed() {

        // GIVEN;
        Exception exception = null;

        // WHEN
        try {
            new AmongItemsType(null);
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception)
                .isInstanceOf(NebulaException.class)
                .hasMessage("generatedObjects is null");
    }

    private GenerationContext getContext() {
        NebulaRandom nebulaRandom = new NebulaRandom(0L);
        return new GenerationContext(nebulaRandom, mock(Model.class), 0L, 1, 10);
    }
}
