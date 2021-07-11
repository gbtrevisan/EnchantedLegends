package com.unicamp.mc322.enchantedlegends.game.player.io.options;

public class OptionsException extends IllegalArgumentException {
    public OptionsException() {
        super();
    }

    public OptionsException(String s) {
        super(s);
    }

    public OptionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptionsException(Throwable cause) {
        super(cause);
    }
}
