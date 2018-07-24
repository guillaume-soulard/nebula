package com.nebula.object.generator;

import com.nebula.object.ObjectGenerator;
import com.nebula.object.User;
import com.nebula.object.generator.model.ClassModelBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LazyListTest {

    @Test
    void generateLazyListOf_should_return_a_list_with_1000000_items() {

        // GIVEN
        ClassModelBuilder builder = new ClassModelBuilder();
        ObjectGenerator objectGenerator = new ModelBasedObjectGenerator(builder.buildModelFrom(User.class));

        // WHEN
        List<User> users = objectGenerator.generateLazyListOf(1000000, User.class);

        // THEN
        assertThat(users).hasSize(1000000);
    }

    @Test
    void get_should_return_non_null_user() {

        // GIVEN
        ClassModelBuilder builder = new ClassModelBuilder();
        ObjectGenerator objectGenerator = new ModelBasedObjectGenerator(builder.buildModelFrom(User.class));
        List<User> users = objectGenerator.generateLazyListOf(1000000, User.class);

        // WHEN
        User user = users.get(999999);

        // THEN
        assertThat(user).isNotNull();
    }

    @Test
    void get_should_throw_an_exception() {

        // GIVEN
        ClassModelBuilder builder = new ClassModelBuilder();
        ObjectGenerator objectGenerator = new ModelBasedObjectGenerator(builder.buildModelFrom(User.class));
        List<User> users = objectGenerator.generateLazyListOf(1000000, User.class);

        // WHEN
        catchException(users).get(1000000);

        // THEN
        assertThat((Exception) caughtException()).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void for_should_iterate_over_list() {

        // GIVEN
        ClassModelBuilder builder = new ClassModelBuilder();
        ObjectGenerator objectGenerator = new ModelBasedObjectGenerator(builder.buildModelFrom(User.class));
        List<User> users = objectGenerator.generateLazyListOf(10, User.class);

        // WHEN
        for (User user : users) {
            assertThat(user).isNotNull();
        }


        // THEN
    }

    @Test
    void contains_should_return_false() {

        // GIVEN
        ClassModelBuilder builder = new ClassModelBuilder();
        ObjectGenerator objectGenerator = new ModelBasedObjectGenerator(builder.buildModelFrom(User.class));
        List<User> users = objectGenerator.generateLazyListOf(10, User.class);

        // WHEN
        boolean result = users.contains(null);

        // THEN
        assertThat(result).isFalse();
    }
}
