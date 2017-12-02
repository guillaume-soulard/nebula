package com.nebula.core.types.entity;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.PropertyBuilder;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EntityTypeTest {

	private static final String ENTITY_NAME = "test";

	@Test
	public void new_EntityType_should_throw_exception_when_entity_is_null() {

		// GIVEN
		Exception exception = null;

		// WHEN
		try {
			new EntityType(null);
		} catch (Exception e) {
			exception = e;
		}

		// THEN
		assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("entityName is null");
	}

	@Test
	public void generateObject_should_return_a_non_null_object() {

		// GIVEN
		EntityType type = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(type, 1l);

		// WHEN
		GeneratedObject result = type.generateObject(0l);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateObject_should_return_a_GeneratedObject_with_a_list_of_properties() {

		EntityType type = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(type, 1l);

		// WHEN
		GeneratedObject result = type.generateObject(0l);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("object", null).hasNoNullFieldsOrPropertiesExcept("object");
	}

	@Test
	public void init_should_set_context() {

		EntityType type = new EntityType(ENTITY_NAME);
		GenerationContext context = initEntityTypeWithEntityAmount(type, 1l);

		// THEN
		assertThat(type).hasFieldOrPropertyWithValue("context", context);
	}

	@Test
	public void getMinRange_should_return_0l() {

		EntityType entityType = new EntityType(ENTITY_NAME);

		// WHEN
		Long result = entityType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_0l() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		Model model = mock(Model.class);
		when(model.getEntityByName(ENTITY_NAME)).thenReturn(entityWithAmount(1l));
		long entityIndex = 0L;
		entityType.init(new GenerationContext(nebulaRandom, model, entityIndex));

		// WHEN
		Long result = entityType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_1l() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 2l);

		// WHEN
		Long result = entityType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1l);
	}

	@Test
	public void generateObject_should_generate_same_entity_for_same_index() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		long index = 0l;
		initEntityTypeWithEntityAmount(entityType, 1l);
		GeneratedObject firstResult = entityType.generateObject(index);

		// WHEN
		GeneratedObject result = entityType.generateObject(index);

		// THEN
		assertThat(result).isEqualTo(firstResult);
	}

	@Test
	public void generateObject_should_generate_different_entities_for_two_different_indexes() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 2l);
		GeneratedObject firstResult = entityType.generateObject(0l);

		// WHEN
		GeneratedObject result = entityType.generateObject(1l);

		// THEN
		assertThat(result).isNotEqualTo(firstResult);
	}

	@Test
	public void generateObject_should_throw_exception_when_index_is_negative() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 1l);

		// WHEN
		catchException(entityType).generateObject(-1l);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void generateObject_should_throw_exception_when_index_is_greater_than_the_maximum_index() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 1l);

		// WHEN
		catchException(entityType).generateObject(1l);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void generateObject_should_throw_exception_when_entity_name_is_unknown() {

		EntityType entityType = new EntityType("unknown entity");
		initEntityTypeWithEntityAmount(entityType, 1l);

		// WHEN
		catchException(entityType).generateObject(1l);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("entity 'unknown entity' is not defined in model");
	}

	@Test
	public void getMaxRange_should_throw_exception_when_entity_name_is_unknown() {

		EntityType entityType = new EntityType("unknown entity");
		initEntityTypeWithEntityAmount(entityType, 1l);

		// WHEN
		catchException(entityType).getMaxRange();

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("entity 'unknown entity' is not defined in model");
	}

	private Entity entityWithAmount(long amount) {
		return new Entity(new ModelBuilder().build(), ENTITY_NAME, amount, new PropertyBuilder());
	}

	private GenerationContext initEntityTypeWithEntityAmount(EntityType type, long entityAmount) {
		Model model = mock(Model.class);
		when(model.getEntityByName(ENTITY_NAME)).thenReturn(entityWithAmount(entityAmount));
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex);
		type.init(context);
		return context;
	}
}
