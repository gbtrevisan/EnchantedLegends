package com.unicamp.mc322.enchantedlegends.game.card.exception;

public class SpellCreationException extends RuntimeException {
    public SpellCreationException() {
        super();
    }

    public SpellCreationException(String message) {
        super(message);
    }

    public SpellCreationException(Throwable cause) {
        super(cause);
    }

    public SpellCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
