package com.geowealth.ninelettershrinkablewords.extractor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.geowealth.ninelettershrinkablewords.dictionary.Dictionary;

public class ShrinkableWordsDictionaryExtractor {
    private static final byte MIN_WORD_LENGTH = 1;
    private static final Map<String, Boolean> IS_WORD_IN_DICTIONARY_CACHE = new HashMap<>();

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

        if (IS_WORD_IN_DICTIONARY_CACHE.containsKey(word)) {
            return IS_WORD_IN_DICTIONARY_CACHE.get(word);
        }

        for (int i = 0; i < wordLength; i++) {
            String shrunkWord = shrinkWord(word, i);

            if (isValidWord(shrunkWord, dictionary) && isShrinkableWord(shrunkWord, dictionary)) {
                IS_WORD_IN_DICTIONARY_CACHE.put(shrunkWord, true);
                return true;
            }
        }

        IS_WORD_IN_DICTIONARY_CACHE.put(word, false);
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
