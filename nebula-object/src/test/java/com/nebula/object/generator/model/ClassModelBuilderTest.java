package com.nebula.object.generator.model;

import com.nebula.core.Model;
import com.nebula.object.User;
import com.nebula.object.UserAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClassModelBuilderTest {

    @Test
    @DisplayName("buildModelFrom should return non null model for UserAddress")
    void buildModelFrom_should_return_non_null_model_for_UserAddress() {

        // GIVEN
        ClassModelBuilder builder = new ClassModelBuilder();

        // WHEN
        Model model = builder.buildModelFrom(UserAddress.class);

        // THEN
        assertThat(model).isNotNull();
    }


    @Test
    @DisplayName("buildModelFrom should return non null model for User")
    void buildModelFrom_should_return_non_null_model_for_User() {

        // GIVEN
        ClassModelBuilder builder = new ClassModelBuilder();

        // WHEN
        Model model = builder.buildModelFrom(User.class);

        // THEN
        assertThat(model).isNotNull();
    }
}