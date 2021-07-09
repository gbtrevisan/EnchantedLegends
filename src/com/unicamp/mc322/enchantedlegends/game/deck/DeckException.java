package com.unicamp.mc322.enchantedlegends.game.deck;

import com.unicamp.mc322.enchantedlegends.game.cards.GameCardException;

public class DeckException extends GameCardException {
    public DeckException() {
        super();
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
