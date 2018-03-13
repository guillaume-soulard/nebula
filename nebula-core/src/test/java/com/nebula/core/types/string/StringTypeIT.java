package com.nebula.core.types.string;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTypeIT {

	private static final long ONE_SECOND = 1000;

	@Test
	public void generateEntityObject_should_take_less_than_1_second_to_generate_100000_strings() {

		// GIVEN
		Model model = ModelBuilder.newModel().build();
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
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(new NebulaRandom(0l), ModelBuilder.newModel().build(), entityIndex);
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
		Entity entity = ModelBuilder.newModel().build().newEntity("entity", 1);
		entity.addProperty("property", NebulaGenerators.random(),
				NebulaTypes.string().withPattern("[A-Z]{1}[a-z]{10,25}"));
		return entity;
	}

	private long elapsedTime(DateTime start) {
		return DateTime.now().getMillis() - start.getMillis();
	}
}
