package com.github.arena.challenges.weakmdparser.parsers.singleline.impl;

import com.github.arena.challenges.weakmdparser.parsers.singleline.SingleLineParser;
import com.github.arena.challenges.weakmdparser.textformatters.MarkdownTextFormatToHtmlConverter;

import java.util.Optional;

public class ParagraphParser implements SingleLineParser {
    private final MarkdownTextFormatToHtmlConverter converter;

    public ParagraphParser(MarkdownTextFormatToHtmlConverter converter) {
        this.converter = converter;
    }

    @Override
    public Optional<String> parse(String markdownLine) {
        String htmlTextContent = convertMarkdownTextToHtml(markdownLine);
        return Optional.of(wrapMarkdownTextWithHtmlParagraphTag(htmlTextContent));
    }

    private String wrapMarkdownTextWithHtmlParagraphTag(String markdownText) {
        return String.format("<p>%s</p>", markdownText);
    }

    private String convertMarkdownTextToHtml(String markdownListItemText) {
        return converter.convert(markdownListItemText);
    }
}
