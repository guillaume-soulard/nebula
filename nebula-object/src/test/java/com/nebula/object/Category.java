package com.nebula.object;

public class Category {
    private String name;
    private Category parent;

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public Category getParent() {
        return parent;
    }

    public Category setParent(Category parent) {
        this.parent = parent;
        return this;
    }
}
