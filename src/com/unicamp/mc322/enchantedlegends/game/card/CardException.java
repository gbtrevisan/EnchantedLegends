package com.unicamp.mc322.enchantedlegends.game.card;

public class CardException extends RuntimeException {
    public CardException() {
    }

    public CardException(String message) {
        super(message);
    }

    public CardException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardException(Throwable cause) {
        super(cause);
    }
}
