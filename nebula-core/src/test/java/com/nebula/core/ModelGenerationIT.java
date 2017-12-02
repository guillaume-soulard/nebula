package com.nebula.core;

import com.nebula.core.types.date.DateTimeTypeIntervals;
import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.nebula.core.types.NebulaTypes.*;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.generators.NebulaGenerators.sequence;
import static org.assertj.core.api.Assertions.assertThat;

public class ModelGenerationIT {

	private static final String ENTITY_PROPERTY_NAME = "entity";
	private static final int ANOTHER_ENTITY_AMOUNT = 10;
	private static final String ANOTHER_ENTITY_NAME = "another entity";
	private static final int TEST_ENTITY_AMOUNT = 1000;
	private static final String ENTITY_TEST_NAME = "test";
	private static final String STRING_PATTERN = "[A-Z]{1}[a-z]{24}";
	private static final DateTime MAX_DATE = new DateTime(2017, 1, 31, 0, 0);
	private static final DateTime MIN_DATE = new DateTime(2017, 1, 1, 0, 0);
	private static final String STRING_PROPERTY_NAME = "string";
	private static final String LIST_PROPERTY_NAME = "list";
	private static final String CONSTANT_PROPERTY_NAME = "constant";
	private static final String BOOLEAN_PROPERTY_NAME = "boolean";
	private static final String DATE_TIME_PROPERTY_NAME = "dateTime";
	private static final String INTEGER_PROPERTY_NAME = "number";
	private static final String ID_PROPERTY_NAME = "_id";

	@Test
	public void generate_should_generate_1000_entities_with_correct_properties_values() {

		// GIVEN
		long seed = 10l;
		Model model = new ModelBuilder().build();
		Entity testEntity = buildTestEntity(model);
		Entity anotherEntity = buildAnotherEntity(model);
		model.addEntity(testEntity);
		model.addEntity(anotherEntity);

		// WHEN
		Map<Entity, List<GeneratedObject>> result = model.generateEntitiesObjectsAll(seed);

		// THEN
		List<GeneratedObject> generatedTestEntity = result.get(testEntity);
		List<GeneratedObject> generatedAnotherEntity = result.get(anotherEntity);
		assertThat(generatedTestEntity).hasSize(TEST_ENTITY_AMOUNT).allMatch(expected(generatedAnotherEntity));
		assertThat(generatedAnotherEntity).hasSize(ANOTHER_ENTITY_AMOUNT);
	}

	@Test
	public void generateEntityObject_should_generate_two_properties_with_different_values_with_same_type() throws Exception {

		// GIVEN
		Model model = new ModelBuilder().build();
		Entity entity = model.newEntity("test", 1);
		entity.addProperty("first", random(), string());
		entity.addProperty("second", random(), string());
		model.addEntity(entity);

		// WHEN
		GeneratedObject generatedObject = model.generateEntityObject(entity, 0l, 0l);

		// THEN
		Object firstValue = generatedObject.getGeneratedPropertyValue("first").getObject();
		Object secondValue = generatedObject.getGeneratedPropertyValue("second").getObject();
		assertThat(firstValue).isNotEqualTo(secondValue);
	}

	private Predicate<GeneratedObject> expected(final List<GeneratedObject> generatedAnotherEntity) {
		return new Predicate<GeneratedObject>() {

			private List<String> propertyNames = Arrays.asList(ID_PROPERTY_NAME, INTEGER_PROPERTY_NAME,
					DATE_TIME_PROPERTY_NAME, BOOLEAN_PROPERTY_NAME, CONSTANT_PROPERTY_NAME, LIST_PROPERTY_NAME,
					STRING_PROPERTY_NAME, ENTITY_PROPERTY_NAME);

			@SuppressWarnings("unchecked")
			@Override
			public boolean test(GeneratedObject t) {

				assertThat(t.getGeneratedProperties()).extracting("propertyName").containsOnlyElementsOf(propertyNames);

				assertThat((BigDecimal) getPropertyByName(t.getGeneratedProperties(), INTEGER_PROPERTY_NAME)
						.getPropertyValue().getObject()).isBetween(BigDecimal.valueOf(0d), BigDecimal.valueOf(100d));

				assertThat((DateTime) getPropertyByName(t.getGeneratedProperties(), DATE_TIME_PROPERTY_NAME)
						.getPropertyValue().getObject()).isBetween(MIN_DATE, MAX_DATE);

				assertThat((Boolean) getPropertyByName(t.getGeneratedProperties(), BOOLEAN_PROPERTY_NAME)
						.getPropertyValue().getObject()).isIn(Boolean.TRUE, Boolean.FALSE);

				assertThat((String) getPropertyByName(t.getGeneratedProperties(), CONSTANT_PROPERTY_NAME)
						.getPropertyValue().getObject()).isEqualTo(ENTITY_TEST_NAME);

				assertThat((List<GeneratedObject>) getPropertyByName(t.getGeneratedProperties(), LIST_PROPERTY_NAME)
						.getPropertyValue().getObject()).contains(new GeneratedObject(Boolean.TRUE),
								new GeneratedObject(Boolean.FALSE));

				assertThat(getPropertyByName(t.getGeneratedProperties(), STRING_PROPERTY_NAME).getPropertyValue()
						.getObject()).asString().matches(STRING_PATTERN);

				assertThat(getPropertyByName(t.getGeneratedProperties(), ENTITY_PROPERTY_NAME).getPropertyValue())
						.isIn(generatedAnotherEntity);

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
	}

	private Entity buildTestEntity(Model model) {
		Entity entity = model.newEntity(ENTITY_TEST_NAME, 1000);
		entity.addProperty(INTEGER_PROPERTY_NAME, random(),
				number().range().withMin(BigDecimal.ZERO).withMax(BigDecimal.valueOf(100)));
		entity.addProperty(DATE_TIME_PROPERTY_NAME, random(),
				dateTime().range().withInterval(DateTimeTypeIntervals.DAY).withMin(MIN_DATE).withMax(MAX_DATE));
		entity.addProperty(BOOLEAN_PROPERTY_NAME, sequence().cycle(), bool());
		entity.addProperty(CONSTANT_PROPERTY_NAME, sequence().cycle(), constant(ENTITY_TEST_NAME));
		entity.addProperty(LIST_PROPERTY_NAME, sequence().cycle(),
				list().of(random(), bool()).withMinSize(100).withMaxSize(200));
		entity.addProperty(STRING_PROPERTY_NAME, sequence().cycle(), string().withPattern(STRING_PATTERN));
		entity.addProperty(ENTITY_PROPERTY_NAME, sequence().cycle(), entity(ANOTHER_ENTITY_NAME));
		return entity;
	}

	private Entity buildAnotherEntity(Model model) {
		return model.newEntity(ANOTHER_ENTITY_NAME, ANOTHER_ENTITY_AMOUNT);
	}
}
