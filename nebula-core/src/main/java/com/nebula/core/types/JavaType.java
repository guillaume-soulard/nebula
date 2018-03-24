package com.nebula.core.types;

import com.nebula.core.GeneratedObject;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaType {

    public static final JavaType NUMBER = new JavaType(JavaTypeName.NUMBER);
    public static final JavaType DATE = new JavaType(JavaTypeName.DATE);
    public static final JavaType BOOLEAN = new JavaType(JavaTypeName.BOOLEAN);
    public static final JavaType STRING = new JavaType(JavaTypeName.STRING);

    private final JavaType subType;
    private final String name;

    public JavaType(JavaTypeName name) {
        this.name = name.name();
        this.subType = null;
    }

    public JavaType(String name) {
        this.name = name;
        this.subType = null;
    }

    public JavaType(JavaTypeName name, JavaType subType) {
        this.name = name.name();
        this.subType = subType;
    }

    public JavaType(String name, JavaType subType) {
        this.name = name;
        this.subType = subType;
    }

    public String getName() {
        return name;
    }

    public JavaType getSubType() {
        return subType;
    }

    public static Set<JavaType> getJavaTypesFor(List<GeneratedObject> generatedObjects) {

        Set<JavaType> javaTypes = new HashSet<>();

        for (GeneratedObject item : generatedObjects) {

            if (item.getObject() instanceof Boolean) {
                javaTypes.add(JavaType.BOOLEAN);
            }

            if (item.getObject() instanceof String) {
                javaTypes.add(JavaType.STRING);
            }

            if (item.getObject() instanceof BigDecimal) {
                javaTypes.add(JavaType.NUMBER);
            }

            if (item.getObject() instanceof DateTime) {
                javaTypes.add(JavaType.DATE);
            }
        }
        return javaTypes;
    }
}
