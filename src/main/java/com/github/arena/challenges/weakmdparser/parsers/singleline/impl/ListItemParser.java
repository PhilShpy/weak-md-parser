package com.github.arena.challenges.weakmdparser.parsers.singleline.impl;

import com.github.arena.challenges.weakmdparser.parsers.singleline.SingleLineParser;
import com.github.arena.challenges.weakmdparser.textformatters.MarkdownTextFormatToHtmlConverter;

import java.util.Optional;

public class ListItemParser implements SingleLineParser {
    private final MarkdownTextFormatToHtmlConverter converter;

    public ListItemParser(MarkdownTextFormatToHtmlConverter converter) {
        this.converter = converter;
    }

    public Optional<String> parse(String markdownLine) {
        if (markdownLine.startsWith("*"))
            return Optional.of(parseMdListItem(markdownLine));
        return Optional.empty();
    }

    public String parseMdListItem(String markdownListItem) {
        String listItemHtmlContent = extractListItemHtmlContentFromMdListItem(markdownListItem);
        return String.format("<li>%s</li>", listItemHtmlContent);
    }

    private String extractListItemHtmlContentFromMdListItem(String markdownListItem) {
        String listItemMdContent = extractMdContentFromMdListItem(markdownListItem);
        return convertMarkdownTextContentToHtmlContent(listItemMdContent);
    }

    private String extractMdContentFromMdListItem(String markdownListItem) {
        return markdownListItem.substring(2);
    }

    private String convertMarkdownTextContentToHtmlContent(String markdownListItemContent) {
        return converter.convert(markdownListItemContent);
    }
}
