package com.github.arena.challenges.weakmdparser.textformatters;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class MarkdownTextFormatToHtmlConverter {
    private final Set<MarkdownToHtmlFormatPair> markdownToHtmlFormatPairs;

    public MarkdownTextFormatToHtmlConverter(MarkdownToHtmlFormatPair... correspondences) {
        markdownToHtmlFormatPairs = new LinkedHashSet<>();
        markdownToHtmlFormatPairs.addAll(Arrays.asList(correspondences));
    }

    public String convert(String markdownText) {
        for (MarkdownToHtmlFormatPair correspondence : markdownToHtmlFormatPairs) {
            String markdownTextFormatPatternToReplace = correspondence.getMarkdownTextFormatPatternToReplace();
            String correspondingHtmlTagReplacement = correspondence.getCorrespondingHtmlTagReplacement();
            markdownText
                    = markdownText.replaceAll(markdownTextFormatPatternToReplace, correspondingHtmlTagReplacement);
        }

        return markdownText;
    }
}
