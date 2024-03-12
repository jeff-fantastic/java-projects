package com.jeff.wordCount;

/*

    WORD COUNTER
    Contains functions for counting characters, words, and paragraphs.

 */

import java.util.Arrays;

public class WordCounter {

    public static int getCharacterCount(String str) {
        return str.trim().length();
    }

    public static int getWordCount(String str) {
        String[] words = str.trim().split("\\s+");
        return str.trim().isEmpty() ? 0 : words.length;
    }

    public static int getParagraphCount(String str) {
        String[] paragraphs = str.trim().split("\\t+");
        return str.trim().isEmpty() ? 0 : paragraphs.length;
    }

}
