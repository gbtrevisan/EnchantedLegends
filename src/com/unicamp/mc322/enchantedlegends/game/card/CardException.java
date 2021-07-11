package com.unicamp.mc322.enchantedlegends.game.card;

public class CardException extends IllegalArgumentException {
    public CardException() {
    }

    public CardException(String s) {
        super(s);
    }

    public CardException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardException(Throwable cause) {
        super(cause);
    }
}
