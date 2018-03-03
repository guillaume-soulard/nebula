package com.nebula.object.generator.model;

import com.nebula.core.Model;
import com.nebula.object.User;
import com.nebula.object.UserAddress;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassModelBuilderTest {

    @Test
    public void buildModelFrom_should_return_non_null_model_for_UserAddress() throws Exception {

        // GIVEN
        ClassModelBuilder builder = new ClassModelBuilder();

        // WHEN
        Model model = builder.buildModelFrom(UserAddress.class);

        // THEN
        assertThat(model).isNotNull();
    }


    @Test
    public void buildModelFrom_should_return_non_null_model_for_User() throws Exception {

        // GIVEN
        ClassModelBuilder builder = new ClassModelBuilder();

        // WHEN
        Model model = builder.buildModelFrom(User.class);

        // THEN
        assertThat(model).isNotNull();
    }
}