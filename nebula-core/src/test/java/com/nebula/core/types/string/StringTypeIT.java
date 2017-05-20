package com.nebula.core.types.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.Nebula;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;

public class StringTypeIT {

	private static final long ONE_SECOND = 1000;

	@Test
	public void generateEntityObject_should_take_less_than_1_second_to_generate_100000_strings() {

		// GIVEN
		Model model = Nebula.newModel();
		Entity entity = createEntityInModel(model);
		DateTime start = DateTime.now();

		// WHEN
		for (int i = 1; i <= 1000; i++) {
			model.generateEntityObject(entity, 0l, 0l);
		}

		// THEN
		assertThat(elapsedTime(start)).isLessThanOrEqualTo(ONE_SECOND);
	}

	@Test
	public void generateObject_should_take_less_than_1_second_to_generate_100000_strings() {

		// GIVEN
		StringType stringType = new StringType(StringGenerator.newStringGenerator("[A-Z]{1}[a-z]{10,25}"));
		GenerationContext context = new GenerationContext(new NebulaRandom(0l), new Model());
		stringType.init(context);
		DateTime start = DateTime.now();

		// WHEN
		for (int i = 1; i <= 1000; i++) {
			stringType.generateObject(0l);
		}

		// THEN
		assertThat(elapsedTime(start)).isLessThanOrEqualTo(ONE_SECOND);
	}

	private Entity createEntityInModel(Model model) {
		Entity entity = Nebula.newEntity("entity", 1);
		entity.addProperty("property", NebulaGenerators.random(),
				NebulaGenerationTypes.string().withPattern("[A-Z]{1}[a-z]{10,25}"));
		model.addEntity(entity);
		return entity;
	}

	private long elapsedTime(DateTime start) {
		return DateTime.now().getMillis() - start.getMillis();
	}
}
