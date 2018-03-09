package com.nebula.core.types.text;

public class TextGenerationConfiguration {

    private int minWords = 1;
    private int maxWords = 10;
    private int maxSentences = 1;
    private int minSentences = 1;

    public int getMinWords() {
        return minWords;
    }

    public TextGenerationConfiguration setMinWords(int minWords) {
        this.minWords = minWords;
        return this;
    }

    public int getMaxWords() {
        return maxWords;
    }

    public TextGenerationConfiguration setMaxWords(int maxWords) {
        this.maxWords = maxWords;
        return this;
    }

    public int getMaxSentences() {
        return maxSentences;
    }

    public TextGenerationConfiguration setMaxSentences(int maxSentences) {
        this.maxSentences = maxSentences;
        return this;
    }

    public int getMinSentences() {
        return minSentences;
    }

    public TextGenerationConfiguration setMinSentences(int minSentences) {
        this.minSentences = minSentences;
        return this;
    }
}
