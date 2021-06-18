package com.unicamp.mc322.enchantedlegends.game.card.exception;

public class CardCreationException extends CardException {
    public CardCreationException() {
        super();
    }

    public CardCreationException(String message) {
        super(message);
    }

    public CardCreationException(Throwable cause) {
        super(cause);
    }

    public CardCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
