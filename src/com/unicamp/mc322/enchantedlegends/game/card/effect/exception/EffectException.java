package com.unicamp.mc322.enchantedlegends.game.card.effect.exception;

public class EffectException extends IllegalArgumentException {
    public EffectException() {
        super();
    }

    public EffectException(String s) {
        super(s);
    }

    public EffectException(Throwable cause) {
        super(cause);
    }

    public EffectException(String message, Throwable cause) {
        super(message, cause);
    }
}
