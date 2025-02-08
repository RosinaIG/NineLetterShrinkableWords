package com.geowealth.ninelettershrinkablewords;

import java.util.Set;

import com.geowealth.ninelettershrinkablewords.dictionary.DictionaryBuilder;
import com.geowealth.ninelettershrinkablewords.extractor.ShrinkableWordsDictionaryExtractor;
import com.geowealth.ninelettershrinkablewords.dictionary.Dictionary;

public class NineLetterShrinkableWordsApplication {

    public static void main(String[] args) {
        String fileUrl = "https://raw.githubusercontent.com/RosinaIG/NineLetterShrinkableWords/refs/heads/main/scrabble_words.txt";
        byte numberOfHeaderLinesInFile = 2;
        Dictionary dictionary = DictionaryBuilder.build(fileUrl, numberOfHeaderLinesInFile);

        byte wordLength = 9;
        Set<String> validOneLetterWords = Set.of("A", "I");
        ShrinkableWordsDictionaryExtractor shrinkableWordsExtractor = new ShrinkableWordsDictionaryExtractor(wordLength, validOneLetterWords);

        long startTime = System.currentTimeMillis();

        Set<String> shrinkableWords = shrinkableWordsExtractor.extractFrom(dictionary);

        long endTime = System.currentTimeMillis();

        System.out.println(String.format("Execution time: %s ms", endTime - startTime));
        System.out.println(String.format("Total number of words in result set: %s", shrinkableWords.size()));
        System.out.println(String.format("Words in result set: %s", shrinkableWords));
    }
}
