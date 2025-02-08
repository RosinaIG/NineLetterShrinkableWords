package com.geowealth.ninelettershrinkablewords.extractor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.geowealth.ninelettershrinkablewords.dictionary.Dictionary;

/**
 * Memoization is another possible approach available in the earlier versions
 * of this file with execution time between 350 and 450 ms.
 * Current solution offers execution time between 250 and 300 ms.
 */
public class ShrinkableWordsDictionaryExtractor {
    private static final byte MIN_WORD_LENGTH = 1;

    private byte wordLength;
    private Set<String> validOneLetterWords;

    public ShrinkableWordsDictionaryExtractor(byte wordLength, Set<String> validOneLetterWords) {
        this.wordLength = wordLength;
        this.validOneLetterWords = validOneLetterWords;
    }

    public Set<String> extractFrom(Dictionary dictionary) {
        return dictionary.getWords().stream()
                .filter(word -> hasWantedLength(word) && isShrinkableWord(word, dictionary))
                .collect(Collectors.toSet());
    }

    private boolean hasWantedLength(String word) {
        return word.length() == wordLength;
    }

    private boolean isShrinkableWord(String word, Dictionary dictionary) {
        int wordLength = word.length();

        if (wordLength == MIN_WORD_LENGTH) {
            return true;
        }

        for (int i = 0; i < wordLength; i++) {
            String shrunkWord = shrinkWord(word, i);

            if (isValidWord(shrunkWord, dictionary) && isShrinkableWord(shrunkWord, dictionary)) {
                return true;
            }
        }

        return false;
    }

    private String shrinkWord(String word, int indexToSkip) {
        StringBuilder stringBuilder = new StringBuilder(word);
        return stringBuilder.deleteCharAt(indexToSkip).toString();
    }

    private boolean isValidWord(String word, Dictionary dictionary) {
        return dictionary.contains(word) || validOneLetterWords.contains(word);
    }
}
