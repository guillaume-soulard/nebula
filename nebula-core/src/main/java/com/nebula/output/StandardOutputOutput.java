package com.nebula.output;

public class StandardOutputOutput {

    public void write(String... formattedObjects) {
        for (String formattedObject : formattedObjects) {
            System.out.print(formattedObject);
        }
        System.out.flush();
    }
}
