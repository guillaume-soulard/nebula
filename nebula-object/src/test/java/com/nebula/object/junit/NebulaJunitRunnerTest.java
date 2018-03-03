package com.nebula.object.junit;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.object.User;
import com.nebula.object.generator.model.ClassModelBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(NebulaJunitRunner.class)
public class NebulaJunitRunnerTest {

    @Generate
    private String string;

    @Generate(usingModel = "users")
    private List<User> users;

    @Generate(usingModel = "users")
    private User user;

    @Test
    public void should_pass() throws Exception {

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
        return new ModelBuilder(model).withSeed("users").build();
    }
}