package com.unicamp.mc322.enchantedlegends.game.deck;

public class DeckException extends IllegalArgumentException {
    public DeckException() {
    }

    public DeckException(String s) {
        super(s);
    }

    public DeckException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeckException(Throwable cause) {
        super(cause);
    }
}
