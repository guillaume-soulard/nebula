package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class NewModelTest {

	@Test
	public void newModel_should_build_model() throws Exception {

		// GIVEN
		Model model = null;

		// WHEN
		model = Nebula.newModel();

		// THEN
		assertThat(model).isNotNull();
	}

	@Test
	public void newModel_should_build_model_with_emppty_list_of_entity() throws Exception {

		// GIVEN
		Model model = null;

		// WHEN
		model = Nebula.newModel();

		// THEN
		assertThat(model.getEntities()).isEmpty();
	}
}
