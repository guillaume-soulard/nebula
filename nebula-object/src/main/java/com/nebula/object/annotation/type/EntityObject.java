package com.nebula.object.annotation.type;

import com.nebula.object.annotation.NebulaAnnotation;
import com.nebula.object.annotation.NebulaTypeAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NebulaAnnotation
@NebulaTypeAnnotation
public @interface EntityObject {
    long amount();
}
