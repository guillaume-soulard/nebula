package com.nebula.core.types.text.dictionary;

import java.io.IOException;

public class LoremIpsumDictionary extends FileBasedDictionary {
    public LoremIpsumDictionary() throws IOException {
        super("lorem-ipsum.dictionary");
    }
}
