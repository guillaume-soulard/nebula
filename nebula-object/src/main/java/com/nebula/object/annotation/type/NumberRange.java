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
public @interface NumberRange {

    int precision() default 0;

    double min() default (double) Integer.MIN_VALUE;

    double max() default (double) Integer.MAX_VALUE;
}
