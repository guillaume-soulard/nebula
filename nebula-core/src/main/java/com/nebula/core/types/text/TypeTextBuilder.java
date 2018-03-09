package com.nebula.core.types.text;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.text.dictionary.EnglishDictionary;
import com.nebula.core.types.text.dictionary.LoremIpsumDictionary;
import com.nebula.core.types.text.dictionary.NebulaDictionary;

import java.io.IOException;
import java.util.List;

public class TypeTextBuilder implements RandomTypeBuilder {

    private NebulaDictionary dictionary;
    private TextGenerationConfiguration textGenerationConfiguration = new TextGenerationConfiguration();

    public TypeTextBuilder() {

        try {
            dictionary = new LoremIpsumDictionary();
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    @Override
    public Type build(Model model) {
        return new TextType(dictionary, textGenerationConfiguration);
    }

    public TypeTextBuilder usingDictionary(List<String> words) {
        this.dictionary = new NebulaDictionary(words);
        return this;
    }

    public TypeTextBuilder usingEnglishDictionary() {
        try {
            this.dictionary = new EnglishDictionary();
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
        return this;
    }

    public TypeTextBuilder withMinWords(int minWords) {
        this.textGenerationConfiguration.setMinWords(minWords);
        return this;
    }


    public TypeTextBuilder withMaxWords(int maxWords) {
        this.textGenerationConfiguration.setMaxWords(maxWords);
        return this;
    }


    public TypeTextBuilder withMinSentences(int minSentences) {
        this.textGenerationConfiguration.setMinSentences(minSentences);
        return this;
    }


    public TypeTextBuilder withMaxSentences(int maxSentences) {
        this.textGenerationConfiguration.setMaxSentences(maxSentences);
        return this;
    }
}
