package com.geowealth.ninelettershrinkablewords.extractor;

import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.geowealth.ninelettershrinkablewords.dictionary.Dictionary;

public class ShrinkableWordsDictionaryExtractorTest {

    private static final Set<String> VALID_ONE_LETTER_WORDS = Set.of("A", "I");

    private ShrinkableWordsDictionaryExtractor shrinkableWordsExtractor;

    @Test
    public void testExtractFrom_whenDictionaryContainsNineLetterShrinkableWords_returnsOnlyNineLetterShrinkableWords() {
        byte wordLength = 9;
        shrinkableWordsExtractor = new ShrinkableWordsDictionaryExtractor(wordLength, VALID_ONE_LETTER_WORDS);

        Set<String> dictionaryWords = Set.of("STARTLING", "STARTING", "STARING", "STRING", "STING", "SING", "SIN", "IN",
                                            "TRAPPINGS", "TRAPPING", "TAPPING", "TAPING", "APING", "PING", "PIN");
        Dictionary dictionary = new Dictionary(dictionaryWords);

        Set<String> expectedShrinkableWords = Set.of("STARTLING", "TRAPPINGS");
        Set<String> actualShrinkableWords = shrinkableWordsExtractor.extractFrom(dictionary);

        assertEquals(expectedShrinkableWords, actualShrinkableWords);
    }

    @Test
    public void testExtractFrom_whenDictionaryIsEmpty_returnsEmptySet() {
        byte wordLength = 9;
        shrinkableWordsExtractor = new ShrinkableWordsDictionaryExtractor(wordLength, VALID_ONE_LETTER_WORDS);

        Dictionary dictionary = new Dictionary(Set.of());

        int expectedSizeOfShrinkableWords = 0;
        int actualSizeOfShrinkableWords = shrinkableWordsExtractor.extractFrom(dictionary).size();

        assertEquals(expectedSizeOfShrinkableWords, actualSizeOfShrinkableWords);
    }

    @Test
    public void testExtractFrom_withAnotherWordLength_returnsOnlyShrinkableWordsWithWantedLength() {
        byte wordLength = 7;
        shrinkableWordsExtractor = new ShrinkableWordsDictionaryExtractor(wordLength, VALID_ONE_LETTER_WORDS);

        Set<String> dictionaryWords = Set.of("STARTLING", "STARTING", "STARING", "STRING", "STING", "SING", "SIN", "IN",
                                            "TRAPPINGS", "TRAPPING", "TAPPING", "TAPING", "APING", "PING", "PIN");
        Dictionary dictionary = new Dictionary(dictionaryWords);

        Set<String> expectedShrinkableWords = Set.of("STARING", "TAPPING");
        Set<String> actualShrinkableWords = shrinkableWordsExtractor.extractFrom(dictionary);

        assertEquals(expectedShrinkableWords, actualShrinkableWords);
    }
}
