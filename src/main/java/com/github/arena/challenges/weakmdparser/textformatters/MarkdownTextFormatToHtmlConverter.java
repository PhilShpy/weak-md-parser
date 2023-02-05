package com.github.arena.challenges.weakmdparser.textformatters;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Converts the Markdown content's text to the html content's text.
 * Gets Markdown-To-Html pairs correspondences, and depending on their order (!)
 * For example:
 * We push two correspondences in this order:
 *
 * __.*__ => <strong>$1</strong>
 * _.*_ => <em>$1</em>
 *
 * So, after pushing the text to convert method
 * '__Bold__ and _Italic_' => <strong>Bold</strong> and <em>Italic</em>
 *
 * But if we push these correspondences in other order we'll get:
 * '__Bold__ and _Italic_' => <em>_Bold__ and _Italic</em>
 */
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
