package com.unicamp.mc322.enchantedlegends.game.card.trait;

public class ElusiveDefenseException extends TraitException {
    public ElusiveDefenseException() {
    }

    public ElusiveDefenseException(String message) {
        super(message);
    }

    public ElusiveDefenseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElusiveDefenseException(Throwable cause) {
        super(cause);
    }
}
