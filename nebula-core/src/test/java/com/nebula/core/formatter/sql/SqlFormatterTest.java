package com.nebula.core.formatter.sql;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.ModelBuilder;
import com.nebula.core.formatter.Formatter;
import com.nebula.core.formatter.ValueFormatter;
import com.nebula.core.types.Type;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SqlFormatterTest {

    private Entity entity;

    @BeforeEach
    void setUp() {
        String tableName = "users";
        entity = mock(Entity.class);
        when(entity.getName()).thenReturn(tableName);
    }

    @Test
    @DisplayName("formatGeneratedObject should format given generated object as sql insert")
    void formatGeneratedObject_should_format_given_generated_object_as_sql_insert() {

        // GIVEN
        ValueFormatter valueFormatter = new ValueFormatter("dd/MM/YYYY", ',', ' ');
        List<String> propertiesToExclude = new ArrayList<>();
        Formatter formatter = new SqlFormatter(valueFormatter, propertiesToExclude);
        List<GeneratedProperty> properties = new ArrayList<>();
        properties.add(new GeneratedProperty("id", new GeneratedObject(BigDecimal.ONE), mock(Type.class)));
        properties.add(new GeneratedProperty("firstName", new GeneratedObject("durant"), mock(Type.class)));
        properties.add(new GeneratedProperty("lastName", new GeneratedObject("jean"), mock(Type.class)));
        properties.add(new GeneratedProperty("dayOfBirth", new GeneratedObject(new DateTime(2017, 1, 1, 0, 0)), mock(Type.class)));
        GeneratedObject generatedObject = new GeneratedObject(properties);
        formatter.formatHeader(entity);

        // WHEN
        String result = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("INSERT INTO users (id,firstName,lastName,dayOfBirth) VALUES (1,'durant','jean','01/01/2017');");
    }

    @Test
    @DisplayName("formatHeader should return delete from")
    void formatHeader_should_return_delete_from() {

        // GIVEN
        ValueFormatter valueFormatter = new ValueFormatter("dd/MM/YYYY", ',', ' ');
        List<String> propertiesToExclude = new ArrayList<>();
        Formatter formatter = new SqlFormatter(valueFormatter, propertiesToExclude);
        Entity entity = ModelBuilder.newEmptyModel().build().newEntity("users");

        // WHEN
        String result = formatter.formatHeader(entity);

        // THEN
        assertThat(result).isEqualTo("DELETE FROM users;");
    }

    @Test
    @DisplayName("formatFooter should return commit")
    void formatFooter_should_return_commit() {

        // GIVEN
        ValueFormatter valueFormatter = new ValueFormatter("dd/MM/YYYY", ',', ' ');
        List<String> propertiesToExclude = new ArrayList<>();
        Formatter formatter = new SqlFormatter(valueFormatter, propertiesToExclude);
        Entity entity = ModelBuilder.newEmptyModel().build().newEntity("users");

        // WHEN
        String result = formatter.formatFooter(entity);

        // THEN
        assertThat(result).isEqualTo("COMMIT;");
    }
}
