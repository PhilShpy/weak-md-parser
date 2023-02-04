package com.github.arena.challenges.weakmdparser.parsers;

import java.util.Optional;

public interface Parser {
    Optional<String> parse(String markdownLine);
}
