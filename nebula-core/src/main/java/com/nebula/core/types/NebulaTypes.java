package com.nebula.core.types;

import com.nebula.core.types.bool.BooleanTypeBuilder;
import com.nebula.core.types.constant.ConstantTypeBuilder;
import com.nebula.core.types.custom.CustomTypeBuilder;
import com.nebula.core.types.date.DateTimeTypeBuilderChooser;
import com.nebula.core.types.entity.EntityTypeBuilder;
import com.nebula.core.types.list.ListTypeBuilder;
import com.nebula.core.types.number.NumberTypeBuilderChooser;
import com.nebula.core.types.amongitems.AmongItemsTypeBuilder;
import com.nebula.core.types.string.StringTypeBuilder;
import com.nebula.core.types.text.TypeTextBuilder;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Arrays;

public final class NebulaTypes {

	public static NumberTypeBuilderChooser number() {
		return new NumberTypeBuilderChooser();
	}

	public static DateTimeTypeBuilderChooser dateTime() {
		return new DateTimeTypeBuilderChooser();
	}

	public static StringTypeBuilder string() {
		return new StringTypeBuilder();
	}

	public static BooleanTypeBuilder bool() {
		return new BooleanTypeBuilder();
	}

	public static ListTypeBuilder list() {
		return new ListTypeBuilder();
	}

	public static ConstantTypeBuilder constant(String value) {
		return new ConstantTypeBuilder(value);
	}

	public static ConstantTypeBuilder constant(DateTime value) {
		return new ConstantTypeBuilder(value);
	}

	public static ConstantTypeBuilder constant(BigDecimal value) {
		return new ConstantTypeBuilder(value);
	}

	public static ConstantTypeBuilder constant(Boolean value) {
		return new ConstantTypeBuilder(value);
	}

	public static EntityTypeBuilder entity(String entityName) {
		return new EntityTypeBuilder().withName(entityName);
	}

	public static AmongItemsTypeBuilder amongItems(String... items) { return new AmongItemsTypeBuilder(Arrays.asList(items)); }

	public static AmongItemsTypeBuilder amongItems(DateTime... items) { return new AmongItemsTypeBuilder(Arrays.asList(items)); }

	public static AmongItemsTypeBuilder amongItems(BigDecimal... items) { return new AmongItemsTypeBuilder(Arrays.asList(items)); }

	public static AmongItemsTypeBuilder amongItems(Boolean... items) { return new AmongItemsTypeBuilder(Arrays.asList(items)); }

	public static CustomTypeBuilder custom(Type type) { return new CustomTypeBuilder(type); }

	public static TypeTextBuilder text() { return new TypeTextBuilder(); }
}
