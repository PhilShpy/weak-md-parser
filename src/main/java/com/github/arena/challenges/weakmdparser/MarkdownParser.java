package com.github.arena.challenges.weakmdparser;

import com.github.arena.challenges.weakmdparser.parsers.active.ActiveElementParser;
import com.github.arena.challenges.weakmdparser.parsers.active.impl.ActiveListParser;
import com.github.arena.challenges.weakmdparser.parsers.singleline.SingleLineParser;
import com.github.arena.challenges.weakmdparser.parsers.singleline.impl.HeaderParser;
import com.github.arena.challenges.weakmdparser.parsers.singleline.impl.ListItemParser;
import com.github.arena.challenges.weakmdparser.parsers.singleline.impl.ParagraphParser;
import com.github.arena.challenges.weakmdparser.textformatters.MarkdownTextFormatToHtmlConverter;
import com.github.arena.challenges.weakmdparser.textformatters.MarkdownToHtmlFormatPair;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Weak Markdown Parser:
 * I need to refactor MarkdownParser class, so for now I decided to use this kind of Set initialisation.
 * But also added methods which add items to these sets.
 * The order of adding items to these sets plays big role and defines the order of Markdown to html parsing.
 */
public class MarkdownParser {
    private final Set<SingleLineParser> singleLineParsers = new LinkedHashSet<>() { {
        MarkdownTextFormatToHtmlConverter converter = new MarkdownTextFormatToHtmlConverter(
                new MarkdownToHtmlFormatPair(
                        "__(.+)__",
                        "<strong>$1</strong>"
                ),
                new MarkdownToHtmlFormatPair(
                        "_(.+)_",
                        "<em>$1</em>"
                )
        );
        add(new HeaderParser());
        add(new ListItemParser(converter));
        add(new ParagraphParser(converter));
    }
    };

    private final Set<ActiveElementParser> activeElementParsers = new LinkedHashSet<>() { {
        add(new ActiveListParser());
    }};

    public void addSingleLineParser(SingleLineParser singleLineParser) {
        singleLineParsers.add(singleLineParser);
    }

    public void addActiveElementParser(ActiveElementParser activeElementParser) {
        activeElementParsers.add(activeElementParser);
    }

    public String parse(String markdownText) {
        String[] lines = extractLines(markdownText);
        return parseLines(lines);
    }

    private String parseLines(String[] markdownLines) {
        for (int i = 0; i < markdownLines.length; i++) {
            markdownLines[i] = parseSingleLine(markdownLines[i]);
        }

        closeRestActiveTags(markdownLines);
        return joinLinesWithLineSeparator(markdownLines);
    }

    private void closeRestActiveTags(String[] markdownLines) {
        markdownLines[markdownLines.length - 1] = addClosingTagsIfNeeded(markdownLines[markdownLines.length - 1]);
    }

    private String addClosingTagsIfNeeded(String lastLine) {
        for (ActiveElementParser activeElementParser : activeElementParsers) {
            if (activeElementParser.elementIsActive()) {
                lastLine = activeElementParser.closeLine(lastLine);
            }
        }

        return lastLine;
    }

    private String joinLinesWithLineSeparator(String[] lines) {
        return String.join("", lines);
    }

    private String parseSingleLine(String line) {
        Optional<String> lineOptional = Optional.empty();
        for (SingleLineParser singleLineParser : singleLineParsers) {
            lineOptional = singleLineParser.parse(line);
            if (lineOptional.isPresent()) {
                break;
            }
        }

        // it's better to use orElse* methods, but in case of refactoring there shouldn't be any auxiliary functionality
        return parseActiveLine(lineOptional.get());
    }

    private String parseActiveLine(String line) {
        Optional<String> lineOptional = Optional.empty();
        for (ActiveElementParser activeElementParser : activeElementParsers) {
            lineOptional = activeElementParser.parse(line);
        }

        return lineOptional.get();
    }

    private String[] extractLines(String markdown) {
        return markdown.split("\n");
    }
}
