package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.joda.time.DateTime;
import org.junit.Test;

import com.nebula.core.types.date.DateTimeTypeIntervals;

public class ModelIT {

	@Test
	public void generate_should_return_1000000_result_with_correct_values() throws NebulaException {

		// GIVEN
		long seed = 10l;
		Model model = Nebula.newModel();
		int entityAmount = 1000000;
		Entity entity = Nebula.newEntity("test", entityAmount);
		entity.addProperty("integer", NebulaTypes.integer().withMin(0).withMax(100), NebulaGenerators.random());
		entity.addProperty("decimal", NebulaTypes.decimal().withPrecision(10).withMin(0).withMax(1),
				NebulaGenerators.random());
		entity.addProperty(
				"dateTime", NebulaTypes.dateTime().withInterval(DateTimeTypeIntervals.DAY)
						.withMin(new DateTime(2017, 1, 1, 0, 0)).withMax(new DateTime(2017, 1, 31, 0, 0)),
				NebulaGenerators.random());
		model.addEntity(entity);

		// WHEN
		Map<Entity, List<GeneratedObject>> result = model.generate(seed);

		Predicate<GeneratedObject> predicate = new Predicate<GeneratedObject>() {

			@Override
			public boolean test(GeneratedObject t) {
				assertThat(t.getGeneratedProperties()).extracting("propertyName").containsExactly("integer", "decimal",
						"dateTime");

				assertThat(
						(Long) getPropertyByName(t.getGeneratedProperties(), "integer").getPropertyValue().getObject())
								.isBetween(0l, 100l);
				assertThat((Double) getPropertyByName(t.getGeneratedProperties(), "decimal").getPropertyValue()
						.getObject()).isBetween(0d, 1d);
				assertThat((DateTime) getPropertyByName(t.getGeneratedProperties(), "dateTime").getPropertyValue()
						.getObject()).isBetween(new DateTime(2017, 1, 1, 0, 0), new DateTime(2017, 1, 31, 0, 0));
				return true;
			}

			private GeneratedProperty getPropertyByName(List<GeneratedProperty> generatedProperties,
					String propertyName) {
				for (GeneratedProperty generatedProperty : generatedProperties) {
					if (generatedProperty.getPropertyName().equals(propertyName)) {
						return generatedProperty;
					}
				}
				return null;
			}
		};
		// THEN
		assertThat(result.get(entity)).hasSize(entityAmount).allMatch(predicate);
	}
}
