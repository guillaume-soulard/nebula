package com.nebula.core.output;

import com.nebula.core.GeneratedObject;

public class OutputParameter {

    private final String formattedObject;
    private final GeneratedObject originalObject;

    public OutputParameter(String formattedObject, GeneratedObject originalObject) {
        this.formattedObject = formattedObject;
        this.originalObject = originalObject;
    }

    public String getFormattedObject() {
        return formattedObject;
    }

    public GeneratedObject getOriginalObject() {
        return originalObject;
    }
}
