package com.nebula.core.types;

public class JavaType {

    public static JavaType NUMBER = new JavaType(JavaTypeName.NUMBER);
    public static JavaType DATE = new JavaType(JavaTypeName.DATE);
    public static JavaType BOOLEAN = new JavaType(JavaTypeName.BOOLEAN);
    public static JavaType STRING = new JavaType(JavaTypeName.STRING);

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
}
