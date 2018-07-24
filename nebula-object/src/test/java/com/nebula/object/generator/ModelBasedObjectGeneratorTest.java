package com.nebula.object.generator;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.object.Category;
import com.nebula.object.User;
import com.nebula.object.UserAddress;
import com.nebula.object.generator.model.ClassModelBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.types.NebulaTypes.string;
import static org.assertj.core.api.Assertions.assertThat;

class ModelBasedObjectGeneratorTest {

    @Test
    void generate_should_return_non_null_object() {

        // GIVEN
        Model model = ModelBuilder.newModel()
                .build();
        Entity userAddressEntity = model.newEntity(UserAddress.class.getCanonicalName());
        userAddressEntity.addProperty("address", random(), string());

        ModelBasedObjectGenerator generator = new ModelBasedObjectGenerator(model);

        // WHEN
        UserAddress result = generator.generateNext(UserAddress.class);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getAddress()).isNotNull();
    }

    @Test
    void generate_should_return_non_null_User_object() {

        // GIVEN
        Model model = new ClassModelBuilder().buildModelFrom(User.class);
        ModelBasedObjectGenerator generator = new ModelBasedObjectGenerator(model);

        // WHEN
        User result = generator.generateNext(User.class);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    void generateListOf_should_return_a_list_of_User_object() {

        // GIVEN
        Model model = new ClassModelBuilder().buildModelFrom(User.class);
        ModelBasedObjectGenerator generator = new ModelBasedObjectGenerator(model);

        // WHEN
        List<User> result = generator.generateListOf(10, User.class);

        // THEN
        assertThat(result).hasSize(10).doesNotContainNull();
    }

    @Test
    void generate_should_generate_a_recursive_object() {

        // GIVEN
        Model model = new ClassModelBuilder().buildModelFrom(Category.class);
        ModelBasedObjectGenerator generator = new ModelBasedObjectGenerator(model);

        // WHEN
        Category result = generator.generateNext(Category.class);

        // THEN
        assertThat(getRecursiveCountOnField(result, Category::getParent, 1)).isEqualTo(10);
    }

    private <T> long getRecursiveCountOnField(T object, Function<T, T> recursiveField, long depth) {

        T subObject = recursiveField.apply(object);

        if (subObject != null) {

            depth = getRecursiveCountOnField(subObject, recursiveField, depth) + 1;
        }

        return depth;
    }
}
