package com.unicamp.mc322.enchantedlegends.game.card;

public class CardException extends IllegalArgumentException {
    public CardException() {
        super();
    }

    public CardException(String message) {
        super(message);
    }

    public CardException(Throwable cause) {
        super(cause);
    }

    public CardException(String message, Throwable cause) {
        super(message, cause);
    }
}
