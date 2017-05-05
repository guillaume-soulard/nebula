package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.joda.time.DateTime;
import org.junit.Test;

import com.nebula.core.types.date.DateTimeTypeIntervals;

public class ModelGenerationIT {

	private static final String DATE_TIME_PROPERTY_NAME = "dateTime";
	private static final String DECIMAL_PROPERTY_NAME = "decimal";
	private static final String INTEGER_PROPERTY_NAME = "integer";
	private static final String ID_PROPERTY_NAME = "_id";

	@Test
	public void generate_should_generate_1000000_entities_with_correct_properties_values() throws NebulaException {

		// GIVEN
		long seed = 10l;
		Model model = Nebula.newModel();
		int entityAmount = 1000000;
		Entity entity = Nebula.newEntity("test", entityAmount);
		entity.addProperty(INTEGER_PROPERTY_NAME, NebulaTypes.integer().withMin(0).withMax(100),
				NebulaGenerators.random());
		entity.addProperty(DECIMAL_PROPERTY_NAME, NebulaTypes.decimal().withPrecision(10).withMin(0).withMax(1),
				NebulaGenerators.random());
		entity.addProperty(
				DATE_TIME_PROPERTY_NAME, NebulaTypes.dateTime().withInterval(DateTimeTypeIntervals.DAY)
						.withMin(new DateTime(2017, 1, 1, 0, 0)).withMax(new DateTime(2017, 1, 31, 0, 0)),
				NebulaGenerators.random());
		model.addEntity(entity);

		// WHEN
		Map<Entity, List<GeneratedObject>> result = model.generateAll(seed);

		Predicate<GeneratedObject> predicate = new Predicate<GeneratedObject>() {

			private List<String> propertyNames = Arrays.asList(ID_PROPERTY_NAME, INTEGER_PROPERTY_NAME,
					DECIMAL_PROPERTY_NAME, DATE_TIME_PROPERTY_NAME);

			@Override
			public boolean test(GeneratedObject t) {

				assertThat(t.getGeneratedProperties()).extracting("propertyName").containsOnlyElementsOf(propertyNames);
				assertThat((Long) getPropertyByName(t.getGeneratedProperties(), INTEGER_PROPERTY_NAME)
						.getPropertyValue().getObject()).isBetween(0l, 100l);
				assertThat((Double) getPropertyByName(t.getGeneratedProperties(), DECIMAL_PROPERTY_NAME)
						.getPropertyValue().getObject()).isBetween(0d, 1d);
				assertThat((DateTime) getPropertyByName(t.getGeneratedProperties(), DATE_TIME_PROPERTY_NAME)
						.getPropertyValue().getObject()).isBetween(new DateTime(2017, 1, 1, 0, 0),
								new DateTime(2017, 1, 31, 0, 0));
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
