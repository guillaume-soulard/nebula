package com.nebula.core.types.text;

import com.nebula.core.GeneratedObject;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.Type;
import com.nebula.core.types.text.dictionary.NebulaDictionary;

public class TextType implements Type {

    private GenerationContext context;
    private final NebulaDictionary dictionary;
    private final TextGenerationConfiguration textGenerationConfiguration;

    TextType(NebulaDictionary dictionary, TextGenerationConfiguration textGenerationConfiguration) {
        this.dictionary = dictionary;
        this.textGenerationConfiguration = textGenerationConfiguration;
    }

    @Override
    public void init(GenerationContext context) {

        this.context = context;
    }

    @Override
    public GeneratedObject generateObject(Long objectIndex) {
        NebulaRandom nebulaRandom = new NebulaRandom(context.getNebulaRandom().getSeed() + objectIndex);
        return new GeneratedObject(dictionary.getText(nebulaRandom, textGenerationConfiguration));
    }

    @Override
    public Long getMinRange() {
        return 0L;
    }

    @Override
    public Long getMaxRange() {
        return 0L;
    }

    @Override
    public JavaType getJavaType() {
        return JavaType.STRING;
    }
}
