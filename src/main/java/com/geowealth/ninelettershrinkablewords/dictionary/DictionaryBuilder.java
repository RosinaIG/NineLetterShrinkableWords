package com.geowealth.ninelettershrinkablewords.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

import com.geowealth.ninelettershrinkablewords.exception.DictionaryBuilderException;

public class DictionaryBuilder {

    public static Dictionary build(String fileUrlString, byte numberOfHeaderLinesInFile) {
        URL fileUrl = getUrl(fileUrlString);
        Set<String> lines = readLinesFromFileAtUrl(fileUrl, numberOfHeaderLinesInFile);
        return new Dictionary(lines);
    }

    private static URL getUrl(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException exception) {
            throw new DictionaryBuilderException("Dictionary build failed due to exception during URL conversion.", exception);
        }
    }

    private static Set<String> readLinesFromFileAtUrl(URL fileUrl, short numberOfHeaderLinesInFile) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileUrl.openConnection().getInputStream()))) {
            return reader.lines().skip(numberOfHeaderLinesInFile).collect(Collectors.toSet());
        } catch (IOException exception) {
            throw new DictionaryBuilderException("Dictionary build failed due to exception during file reading.", exception);
        }
    }
}
