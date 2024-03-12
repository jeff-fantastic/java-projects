package com.jeff.wordCount;

/*

    WORD POPUP
    Shows the statistics in a popup format based on
    provided String.

 */

import javax.swing.*;

public class WordPopup extends JPopupMenu {

    public WordPopup(String text) {
        // Evaluate text
        int chars = WordCounter.getCharacterCount(text);
        int words = WordCounter.getWordCount(text);
        int paragraphs = WordCounter.getParagraphCount(text);

        // Create popup
        JMenuItem charCount = new JMenuItem("Characters - " + chars);
        JMenuItem wordCount = new JMenuItem("Words - " + words);
        JMenuItem paraCount = new JMenuItem("Paragraphs - " + paragraphs);
        this.add(charCount);
        this.add(wordCount);
        this.add(paraCount);
    }
}
