package com.nebula.core.types.text;

public class TextGenerationConfiguration {

    private int minWordsPerSentence = 1;
    private int maxWordsPerSentence = 10;
    private int maxSentences = 1;
    private int minSentences = 1;

    public int getMinWordsPerSentence() {
        return minWordsPerSentence;
    }

    public TextGenerationConfiguration setMinWordsPerSentence(int minWordsPerSentence) {
        this.minWordsPerSentence = minWordsPerSentence;
        return this;
    }

    public int getMaxWordsPerSentence() {
        return maxWordsPerSentence;
    }

    public TextGenerationConfiguration setMaxWordsPerSentence(int maxWordsPerSentence) {
        this.maxWordsPerSentence = maxWordsPerSentence;
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
