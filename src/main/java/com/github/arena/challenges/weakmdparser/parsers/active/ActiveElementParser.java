package com.github.arena.challenges.weakmdparser.parsers.active;

import com.github.arena.challenges.weakmdparser.parsers.Parser;

public interface ActiveElementParser extends Parser {
    boolean elementIsActive();
    String closeLine(String line);
}
