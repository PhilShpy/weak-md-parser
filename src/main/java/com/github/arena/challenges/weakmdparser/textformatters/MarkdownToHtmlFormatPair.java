package com.github.arena.challenges.weakmdparser.textformatters;

public class MarkdownToHtmlFormatPair {
    private final String markdownTextFormatPatternToReplace;
    private final String correspondingHtmlTagReplacement;

    public MarkdownToHtmlFormatPair(String markdownTextFormatPatternToReplace, String correspondingHtmlTagReplacement) {
        this.markdownTextFormatPatternToReplace = markdownTextFormatPatternToReplace;
        this.correspondingHtmlTagReplacement = correspondingHtmlTagReplacement;
    }

    public String getMarkdownTextFormatPatternToReplace() {
        return markdownTextFormatPatternToReplace;
    }

    public String getCorrespondingHtmlTagReplacement() {
        return correspondingHtmlTagReplacement;
    }
}
