package com.nebula.object.annotation.generator;

import com.nebula.object.annotation.NebulaAnnotation;
import com.nebula.object.annotation.NebulaGeneratorAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NebulaAnnotation
@NebulaGeneratorAnnotation
public @interface Random {
}
