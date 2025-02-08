package com.geowealth.ninelettershrinkablewords.dictionary;

import java.util.Collections;
import java.util.Set;

public class Dictionary {

    private final Set<String> words;

    public Dictionary(Set<String> words) {
        this.words = words;
    }

    public boolean contains(String word) {
        return words.contains(word);
    }

    public Set<String> getWords() {
        return Collections.unmodifiableSet(words);
    }
}
