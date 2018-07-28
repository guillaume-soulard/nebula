package com.nebula.core.types.string;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.NebulaTypes;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringTypeIT {

	private static final long ONE_SECOND = 1000;

	@Test
	void generateEntityObject_should_take_less_than_1_second_to_generate_100000_strings() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		Entity entity = createEntityInModel();
		DateTime start = DateTime.now();

		// WHEN
		for (int i = 1; i <= 1000; i++) {
			model.generateEntityObject(entity, 0L, 0L);
		}

		// THEN
		assertThat(elapsedTime(start)).isLessThanOrEqualTo(ONE_SECOND);
	}

	@Test
	void generateObject_should_take_less_than_1_second_to_generate_100000_strings() {

		// GIVEN
		StringType stringType = new StringType(StringGenerator.newStringGenerator("[A-Z]{1}[a-z]{10,25}"));
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(new NebulaRandom(0L), ModelBuilder.newEmptyModel().build(), entityIndex, 1, 10);
		stringType.init(context);
		DateTime start = DateTime.now();

		// WHEN
		for (int i = 1; i <= 1000; i++) {
			stringType.generateObject(0L);
		}

		// THEN
		assertThat(elapsedTime(start)).isLessThanOrEqualTo(ONE_SECOND);
	}

	private Entity createEntityInModel() {
		Entity entity = ModelBuilder.newEmptyModel().build().newEntity("entity", 1);
		entity.addProperty("property", NebulaGenerators.random(),
				NebulaTypes.string().withPattern("[A-Z]{1}[a-z]{10,25}"));
		return entity;
	}

	private long elapsedTime(DateTime start) {
		return DateTime.now().getMillis() - start.getMillis();
	}
}
