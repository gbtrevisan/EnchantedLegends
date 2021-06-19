package com.unicamp.mc322.enchantedlegends.game.card;

public class CardException extends RuntimeException {

    public CardException(String s) {
        super(s);
    }

    public CardException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardException(Throwable cause) {
        super(cause);
    }

    public CardException() {
    }
}
