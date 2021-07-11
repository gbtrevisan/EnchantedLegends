package com.unicamp.mc322.enchantedlegends.game.card.mana.exception;

public class InsufficientManaException extends Exception {
    public InsufficientManaException() {
        super();
    }

    public InsufficientManaException(String message) {
        super(message);
    }

    public InsufficientManaException(Throwable cause) {
        super(cause);
    }

    public InsufficientManaException(String message, Throwable cause) {
        super(message, cause);
    }
}
