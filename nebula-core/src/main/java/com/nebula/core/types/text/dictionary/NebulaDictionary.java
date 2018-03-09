package com.nebula.core.types.text.dictionary;

import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.text.TextGenerationConfiguration;

import java.util.List;

public class NebulaDictionary {

    private List<String> words;

    public NebulaDictionary(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public String getText(NebulaRandom nebulaRandom, TextGenerationConfiguration configuration) {
        StringBuilder textBuilder = new StringBuilder();

        boolean firstSentence = true;

        long numberOfSentencesToGenerate = nebulaRandom.randomBetween(configuration.getMinSentences(), configuration.getMaxSentences());

        for (int sentence = 1; sentence <= numberOfSentencesToGenerate; sentence++) {
            boolean firstWordOfSentence = true;

            if (firstSentence) {
                firstSentence = false;
            } else {
                textBuilder.append(" ");
            }

            long numberOfWordsToGenerate = nebulaRandom.randomBetween(configuration.getMinWords(), configuration.getMaxWords());

            for (int wordsCount = 1; wordsCount <= numberOfWordsToGenerate; wordsCount++) {

                String wordToAdd = words.get(Math.toIntExact(nebulaRandom.randomBetween(0L, words.size() - 1)));

                if (firstWordOfSentence) {
                    firstWordOfSentence = false;
                    wordToAdd = Character.toUpperCase(wordToAdd.charAt(0)) + wordToAdd.substring(1);
                } else {
                    textBuilder.append(" ");
                }

                textBuilder.append(wordToAdd);

                if (wordsCount == numberOfWordsToGenerate) {
                    textBuilder.append(".");
                }
            }
        }

        return textBuilder.toString();
    }
}
