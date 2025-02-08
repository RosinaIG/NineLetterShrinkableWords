package com.geowealth.ninelettershrinkablewords.dictionary;

import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.geowealth.ninelettershrinkablewords.exception.DictionaryBuilderException;

public class DictionaryBuilderTest {
    @Test
    public void testBuild_whenUrlIsMalformed_throwsDictionaryBuilderException() {
        String fileUrl = "htp:/wrong/url";
        byte numberOfHeaderLinesInFile = 2;

        assertThrows(DictionaryBuilderException.class, () -> DictionaryBuilder.build(fileUrl, numberOfHeaderLinesInFile));
    }

    @Test
    public void testBuild_whenFileDoesNotExist_throwsDictionaryBuilderException() {
        String fileUrl = "https://raw.githubusercontent.com/RosinaIG/NineLetterShrinkableWords/refs/heads/main/non_existent_file.txt";
        byte numberOfHeaderLinesInFile = 2;

        assertThrows(DictionaryBuilderException.class, () -> DictionaryBuilder.build(fileUrl, numberOfHeaderLinesInFile));
    }

    @Test
    public void testBuild_withValidFile_createsExpectedDictionary() {
        String fileUrl = "https://raw.githubusercontent.com/RosinaIG/NineLetterShrinkableWords/refs/heads/main/scrabble_words_mini.txt";
        byte numberOfHeaderLinesInFile = 2;

        Dictionary dictionary = DictionaryBuilder.build(fileUrl, numberOfHeaderLinesInFile);

        Set<String> expectedDictionaryWords = Set.of("AA", "AAH", "AAHED");
        Set<String> actualDictionaryWords = dictionary.getWords();

        assertEquals(expectedDictionaryWords, actualDictionaryWords);
    }
}
