package com.nebula.object.generator;

import com.nebula.object.Category;
import com.nebula.object.User;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleObjectGeneratorTest {

    private ObjectGeneratorBuilder defaultObjectBuilder = new ObjectGeneratorBuilder();

    @Test
    public void generate_should_return_non_null_object() throws Exception {

        // GIVEN
        SimpleObjectGenerator objectGenerator = new SimpleObjectGenerator(defaultObjectBuilder);

        // WHEN
        User result = objectGenerator.generate(User.class);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    public void generate_should_return_user_with_filled_fields() throws Exception {

        // GIVEN
        SimpleObjectGenerator objectGenerator = new SimpleObjectGenerator(defaultObjectBuilder);

        // WHEN
        User result = objectGenerator.generate(User.class);

        // THEN
        assertThat(result).hasFieldOrPropertyWithValue("name", "string")
                .hasFieldOrPropertyWithValue("counter", 0)
                .hasFieldOrPropertyWithValue("isPresent", false);
        assertThat(new DateTime(result.getDayOfBirth()).withMillisOfSecond(0)).isEqualTo(DateTime.now().withMillisOfSecond(0));
    }

    @Test
    public void generate_should_return_user_sub_object_filled() throws Exception {

        // GIVEN
        SimpleObjectGenerator objectGenerator = new SimpleObjectGenerator(defaultObjectBuilder);

        // WHEN
        User result = objectGenerator.generate(User.class);

        // THEN
        assertThat(result.getAddress()).isNotNull().hasFieldOrPropertyWithValue("address", "string");
    }

    @Test
    public void generate_should_return_user_filled_with_arrays_of_departments() throws Exception {

        // GIVEN
        SimpleObjectGenerator objectGenerator = new SimpleObjectGenerator(defaultObjectBuilder);

        // WHEN
        User result = objectGenerator.generate(User.class);

        // THEN
        assertThat(result.getDepartments()).isNotNull().hasSize(10).doesNotContainNull();
    }

    @Test
    public void generate_should_return_user_filled_list_of_string() throws Exception {

        // GIVEN
        SimpleObjectGenerator objectGenerator = new SimpleObjectGenerator(defaultObjectBuilder);

        // WHEN
        User result = objectGenerator.generate(User.class);

        // THEN
        assertThat(result.getComments()).isNotNull().hasSize(10).containsOnly("string");
    }


    @Test
    public void generateListOf_should_return_a_list_of_10_string() throws Exception {

        // GIVEN
        SimpleObjectGenerator objectGenerator = new SimpleObjectGenerator(defaultObjectBuilder);

        // WHEN
        List<String> result = objectGenerator.generateListOf(10, String.class);

        // THEN
        assertThat(result).isNotNull().hasSize(10).containsOnly("string");
    }

    @Test
    public void generateListOf_should_return_a_list_of_1_string() throws Exception {

        // GIVEN
        SimpleObjectGenerator objectGenerator = new SimpleObjectGenerator(defaultObjectBuilder);

        // WHEN
        List<String> result = objectGenerator.generateListOf(1, String.class);

        // THEN
        assertThat(result).isNotNull().hasSize(1).containsOnly("string");
    }

    @Test
    public void generate_should_return_recursive_object_with_deep_10() throws Exception {

        // GIVEN
        SimpleObjectGenerator objectGenerator = new SimpleObjectGenerator(new ObjectGeneratorBuilder().setDefaultMaxDepth(5));

        // WHEN
        Category result = objectGenerator.generate(Category.class);

        // THEN
        assertThat(result).isNotNull();
    }
}
