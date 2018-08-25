package com.nebula.core.types.script;

import com.nebula.core.*;
import com.nebula.core.types.GenerationContext;
import org.joda.time.DateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class ScriptTypeTest {

    @Test
    @DisplayName("generateObject should return 0")
    void generateObject_should_return_0() {

        // GIVEN
        ScriptType scriptType = new ScriptType("return nebula.number(0)");
        Model model = ModelBuilder.newEmptyModel()
                .build();
        GenerationContext context = new GenerationContext(null, model, 0L, 1, 10);
        scriptType.init(context);

        // WHEN
        GeneratedObject result = scriptType.generateObject(Collections.emptyList(), 0L);

        // THEN
        assertThat(result.getObject()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("generateObject should return 10")
    void generateObject_should_return_10() {

        // GIVEN
        ScriptType scriptType = new ScriptType("return nebula.number(10)");
        Model model = ModelBuilder.newEmptyModel()
                .build();
        GenerationContext context = new GenerationContext(null, model, 0L, 1, 10);
        scriptType.init(context);

        // WHEN
        GeneratedObject result = scriptType.generateObject(Collections.emptyList(), 0L);

        // THEN
        assertThat(result.getObject()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    @DisplayName("generateObject should return test string")
    void generateObject_should_return_test_string() {

        // GIVEN
        ScriptType scriptType = new ScriptType("return nebula.string('test')");
        Model model = ModelBuilder.newEmptyModel()
                .build();
        GenerationContext context = new GenerationContext(null, model, 0L, 1, 10);
        scriptType.init(context);

        // WHEN
        GeneratedObject result = scriptType.generateObject(Collections.emptyList(), 0L);

        // THEN
        assertThat(result.getObject()).isEqualTo("test");
    }

    @Test
    @DisplayName("generateObject should return true string")
    void generateObject_should_return_true_string() {

        // GIVEN
        ScriptType scriptType = new ScriptType("return nebula.boolTrue()");
        Model model = ModelBuilder.newEmptyModel()
                .build();
        GenerationContext context = new GenerationContext(null, model, 0L, 1, 10);
        scriptType.init(context);

        // WHEN
        GeneratedObject result = scriptType.generateObject(Collections.emptyList(), 0L);

        // THEN
        assertThat(result.getObject()).isEqualTo(true);
    }

    @Test
    @DisplayName("generateObject should return a date")
    void generateObject_should_return_a_date() {

        // GIVEN
        ScriptType scriptType = new ScriptType("return nebula.date('01/02/2018 03:05:06', 'dd/MM/yyyy hh:mm:ss')");
        Model model = ModelBuilder.newEmptyModel()
                .build();
        GenerationContext context = new GenerationContext(null, model, 0L, 1, 10);
        scriptType.init(context);

        // WHEN
        GeneratedObject result = scriptType.generateObject(Collections.emptyList(), 0L);

        // THEN
        assertThat(result.getObject()).isEqualTo(new DateTime(2018, 2, 1, 3, 5, 6));
    }

    @Test
    @DisplayName("generateObject should return null")
    void generateObject_should_return_null() {

        // GIVEN
        ScriptType scriptType = new ScriptType("return nebula.nil()");
        Model model = ModelBuilder.newEmptyModel()
                .build();
        GenerationContext context = new GenerationContext(null, model, 0L, 1, 10);
        scriptType.init(context);

        // WHEN
        GeneratedObject result = scriptType.generateObject(Collections.emptyList(), 0L);

        // THEN
        assertThat(result.getObject()).isNull();
    }

    @Test
    @DisplayName("generateObject should throw exception when raw type is returned")
    void generateObject_should_throw_exception_when_raw_type_is_returned() {

        // GIVEN
        ScriptType scriptType = new ScriptType("return 0");
        Model model = ModelBuilder.newEmptyModel()
                .build();
        GenerationContext context = new GenerationContext(null, model, 0L, 1, 10);
        scriptType.init(context);
        Exception exception = null;

        // WHEN
        try {
            scriptType.generateObject(Collections.emptyList(), 0L);
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception)
                .isInstanceOf(NebulaException.class)
                .hasMessage("Wrong return type for script. Use nebula.number(<number>), nebula.date(<dateString>, <format>) nebula.boolTrue() nebula.boolFalse() nebula.string(<string>) nebula.nil() instead");
    }

    @Test
    @DisplayName("generateObject should return One with condition")
    void generateObject_should_return_One_with_condition() {

        // GIVEN
        ScriptType scriptType = new ScriptType("return self.amount == 1 ? nebula.string('One') : nebula.string('Other')");
        Model model = ModelBuilder.newEmptyModel()
                .build();
        GenerationContext context = new GenerationContext(null, model, 0L, 1, 10);
        scriptType.init(context);

        // WHEN
        GeneratedObject result = scriptType.generateObject(Collections.singletonList(new GeneratedProperty("amount", new GeneratedObject(BigDecimal.ONE), null)), 0L);

        // THEN
        assertThat(result.getObject()).isEqualTo("One");
    }
}