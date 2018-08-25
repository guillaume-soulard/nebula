package com.nebula.object.junit;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.object.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(NebulaJunitRunner.class)
public class NebulaJunitRunnerTest {

    @Generate
    private String string;

    @Generate(usingModel = "users")
    private List<User> users;

    @Generate(usingModel = "users")
    private User user;

    @Test
    @DisplayName("should pass")
    void should_pass() {

        assertThat(users).hasSize(10);
        assertThat(string).isNotNull();
        assertThat(user).isNotNull();
        assertThat(user.getComments()).isNotNull();
        assertThat(user.getDepartments()).isNotNull();
        assertThat(user.getDayOfBirth()).isNotNull();
        assertThat(user.getAddress()).isNotNull();
        assertThat(user.getCounter()).isNotNull();
        assertThat(user.getName()).isNotNull();
    }

    @GenerationModel(name = "users")
    public Model setUpModel(Model model) {
        return ModelBuilder.newModelFrom(model).withSeed("users").build();
    }
}