package com.unicamp.mc322.enchantedlegends.game.card.unit.trait.exception;

public class TraitException extends RuntimeException {
    public TraitException() {
    }

    public TraitException(String message) {
        super(message);
    }

    public TraitException(String message, Throwable cause) {
        super(message, cause);
    }

    public TraitException(Throwable cause) {
        super(cause);
    }
}
