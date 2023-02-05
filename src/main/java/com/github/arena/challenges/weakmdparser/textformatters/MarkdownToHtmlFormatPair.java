package com.github.arena.challenges.weakmdparser.textformatters;

/**
 * Pair which defines the correspondence between Markdown text format and html text format.
 * For example:
 * __.*__ => <strong>$1</strong>
 */
public class MarkdownToHtmlFormatPair {
    private final String markdownTextFormatPatternToReplace;
    private final String correspondingHtmlTagReplacement;

    public MarkdownToHtmlFormatPair(String markdownTextRegexToReplace, String correspondingHtmlTagReplacement) {
        this.markdownTextFormatPatternToReplace = markdownTextRegexToReplace;
        this.correspondingHtmlTagReplacement = correspondingHtmlTagReplacement;
    }

    public String getMarkdownTextFormatPatternToReplace() {
        return markdownTextFormatPatternToReplace;
    }

    public String getCorrespondingHtmlTagReplacement() {
        return correspondingHtmlTagReplacement;
    }
}
