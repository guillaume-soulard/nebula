package com.nebula.object.junit;

import com.nebula.core.ModelBuilder;
import com.nebula.object.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(NebulaJunitRunner.class)
public class NebulaJunitRunnerTest {

    @Generate
    private String string;

    @Generate
    private User user;

    @Test
    public void should_pass() throws Exception {

        assertThat(string).isNotNull();
        assertThat(user).isNotNull();
        assertThat(user.getComments()).isNotNull();
        assertThat(user.getDepartments()).isNotNull();
        assertThat(user.getDayOfBirth()).isNotNull();
        assertThat(user.getAddress()).isNotNull();
        assertThat(user.getCounter()).isNotNull();
        assertThat(user.getName()).isNotNull();
    }

    @Model
    public com.nebula.core.Model setUpModel() {
        com.nebula.core.Model model = new ModelBuilder()
                .build();

        return model;
    }
}