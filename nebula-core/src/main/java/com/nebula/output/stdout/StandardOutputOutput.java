package com.nebula.output.stdout;

import com.nebula.output.Output;

public class StandardOutputOutput implements Output {

    public void write(String... formattedObjects) {
        for (String formattedObject : formattedObjects) {
            System.out.print(formattedObject);
        }
        System.out.flush();
    }
}
