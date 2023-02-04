package com.github.arena.challenges.weakmdparser.parsers.singleline.impl;

import com.github.arena.challenges.weakmdparser.parsers.singleline.SingleLineParser;

import java.util.Optional;

public class HeaderParser implements SingleLineParser {
    private static final String MARKDOWN_HEADER_PATTERN = "^(#{1,6}\\s*)(.*$)";
    private static final String ABSTRACT_HTML_HEADER_FORMAT = "<h%d>$2</h%d>";

    public Optional<String> parse(String markdownLine) {
        int sizeOfHeader = countSizeOfHeader(markdownLine);
        if (sizeOfHeaderIsValid(sizeOfHeader)) {
            return Optional.of(replaceValidMarkdownHeaderToHtmlHeader(markdownLine, sizeOfHeader));
        } else {
            return Optional.empty();
        }
    }

    private String replaceValidMarkdownHeaderToHtmlHeader(String markdownText, int htmlHeaderSize) {
        String htmlHeaderFormat = prepareHtmlHeaderFormat(htmlHeaderSize);
        return markdownText.replaceFirst(MARKDOWN_HEADER_PATTERN, htmlHeaderFormat);
    }

    private String prepareHtmlHeaderFormat(int headerSize) {
        return String.format(ABSTRACT_HTML_HEADER_FORMAT, headerSize ,headerSize);
    }

    private int countSizeOfHeader(String markdownText) {
        int count = 0;
        for (int i = 0; i < markdownText.length() && markdownText.charAt(i) == '#'; i++) {
            count++;
        }

        return count;
    }

    private boolean sizeOfHeaderIsValid(int sizeOfHeader) {
        return sizeOfHeader > 0 && sizeOfHeader < 7;
    }
}
