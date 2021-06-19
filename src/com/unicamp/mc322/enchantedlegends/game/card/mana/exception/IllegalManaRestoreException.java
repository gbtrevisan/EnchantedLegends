package com.unicamp.mc322.enchantedlegends.game.card.mana.exception;

public class IllegalManaRestoreException extends IllegalArgumentException {
    public IllegalManaRestoreException() {
        super();
    }

    public IllegalManaRestoreException(String message) {
        super(message);
    }

    public IllegalManaRestoreException(Throwable cause) {
        super(cause);
    }

    public IllegalManaRestoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
