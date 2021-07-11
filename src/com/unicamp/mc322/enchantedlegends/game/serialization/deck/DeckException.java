package com.unicamp.mc322.enchantedlegends.game.serialization.deck;

import com.unicamp.mc322.enchantedlegends.game.serialization.cards.GameCardsException;

public class DeckException extends GameCardsException {
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
