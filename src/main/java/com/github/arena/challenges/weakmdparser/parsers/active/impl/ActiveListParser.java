package com.github.arena.challenges.weakmdparser.parsers.active.impl;

import com.github.arena.challenges.weakmdparser.parsers.active.ActiveElementParser;

import java.util.Optional;

/**
 * This class manages UL wrapping.
 */
public class ActiveListParser implements ActiveElementParser {
    private boolean isActive = false;

    @Override
    public Optional<String> parse(String markdownLine) {
        if (lineMatchesOnlyListItem(markdownLine) && !elementIsActive()) {
            isActive = true;
            markdownLine = "<ul>" + markdownLine;
        } else if (lineNotMatchListItem(markdownLine) && elementIsActive()) {
            isActive = false;
            markdownLine = "</ul>" + markdownLine;
        }

        return Optional.of(markdownLine);
    }

    @Override
    public boolean elementIsActive() {
        return isActive;
    }

    @Override
    public String closeLine(String line) {
        return line + "</ul>";
    }

    private boolean lineMatchesOnlyListItem(String markdownLine) {
        return markdownLine.matches("(<li>).*") &&
                !markdownLine.matches("(<h).*") &&
                !markdownLine.matches("(<p>).*");
    }

    private boolean lineNotMatchListItem(String markdownLine) {
        return !markdownLine.matches("(<li>).*");
    }
}
