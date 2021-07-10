package com.unicamp.mc322.enchantedlegends.game.loader.cards;

public class GameCardsException extends IllegalArgumentException {
    public GameCardsException() {
        super();
    }

    public GameCardsException(String s) {
        super(s);
    }

    public GameCardsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameCardsException(Throwable cause) {
        super(cause);
    }
}
