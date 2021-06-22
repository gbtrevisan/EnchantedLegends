package com.unicamp.mc322.enchantedlegends.game.card.mana;

public class ManaException extends IllegalArgumentException {
    public ManaException() {
    }

    public ManaException(String s) {
        super(s);
    }

    public ManaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManaException(Throwable cause) {
        super(cause);
    }
}
