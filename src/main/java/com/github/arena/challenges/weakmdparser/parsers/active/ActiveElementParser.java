package com.github.arena.challenges.weakmdparser.parsers.active;

import com.github.arena.challenges.weakmdparser.parsers.Parser;

/**
 * Interface which provides functions for managing wrapping active elements (which must be wrapped).
 * For example: <ul><li></li><li></li></ul> - here li elements are wrapped with ul.
 */
public interface ActiveElementParser extends Parser {
    boolean elementIsActive();
    String closeLine(String line);
}
