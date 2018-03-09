package com.nebula.core.types.text.dictionary;

import java.io.IOException;

public class EnglishDictionary extends FileBasedDictionary {
    public EnglishDictionary() throws IOException {
        super("english.dictionary");
    }
}
