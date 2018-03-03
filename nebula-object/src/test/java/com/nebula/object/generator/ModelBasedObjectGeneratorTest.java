package com.nebula.object.generator;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.object.User;
import com.nebula.object.UserAddress;
import com.nebula.object.generator.model.ClassModelBuilder;
import org.junit.Test;

import java.util.List;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.types.NebulaTypes.string;
import static org.assertj.core.api.Assertions.assertThat;

public class ModelBasedObjectGeneratorTest {

    @Test
    public void generate_should_return_non_null_object() throws Exception {

        // GIVEN
        Model model = new ModelBuilder()
                .build();
        Entity userAddressEntity = model.newEntity(UserAddress.class.getCanonicalName());
        userAddressEntity.addProperty("address", random(), string());
        model.addEntity(userAddressEntity);

        ModelBasedObjectGenerator generator = new ModelBasedObjectGenerator(model);

        // WHEN
        UserAddress result = generator.generateNext(UserAddress.class);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getAddress()).isNotNull();
    }

    @Test
    public void generate_should_return_non_null_User_object() throws Exception {

        // GIVEN
        Model model = new ClassModelBuilder().buildModelFrom(User.class);
        ModelBasedObjectGenerator generator = new ModelBasedObjectGenerator(model);

        // WHEN
        User result = generator.generateNext(User.class);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    public void generateListOf_should_return_a_list_of_User_object() throws Exception {

        // GIVEN
        Model model = new ClassModelBuilder().buildModelFrom(User.class);
        ModelBasedObjectGenerator generator = new ModelBasedObjectGenerator(model);

        // WHEN
        List<User> result = generator.generateListOf(10, User.class);

        // THEN
        assertThat(result).hasSize(10).doesNotContainNull();
    }
}
